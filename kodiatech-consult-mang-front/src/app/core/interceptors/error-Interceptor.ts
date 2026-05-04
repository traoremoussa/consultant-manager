import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, filter, switchMap, take } from 'rxjs/operators';


import { AuthService } from 'src/app/features/services/auth.service';
import { ConsultantEndPointURI } from '../constantes/consultant-end-point-uri';
import { StorageService } from 'src/app/features/services/storage.service';
import { TokenRefreshResponse } from 'src/app/features/models/token-refresh-reponse-model';
import { ToastService } from 'src/app/shared/services/ToastService';
//@see https://stackoverflow.com/questions/72159944/angular-is-not-receiving-the-status-code-set-by-httpservletresponse-senderro-m

const TOKEN_HEADER_KEY = 'Authorization';  // for Spring Boot back-end
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(private authService: AuthService,
    private storageService: StorageService,
    private toastService: ToastService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authReq = request;
    /*
          alert(` ERROR: ${authReq.url}- ${!authReq.url.includes(ConsultantEndPointURI.AUTH.LOGIN)}`)

          const token = this.storageService.getRefreshToken();
         // alert(` TOKEN test:${token}`)
    */

    const accessToken = this.storageService.getToken();

    // ✅ Ajouter token à toutes les requêtes (sauf login)
    if (accessToken && !request.url.includes(ConsultantEndPointURI.AUTH.LOGIN)) {
      authReq = this.addTokenHeader(request, accessToken);
    }

    //
    return next.handle(authReq).pipe(catchError((err: HttpErrorResponse) => {

      console.error('HTTP Error:', err);

      let errorMsg = '';
      if (err.error instanceof ErrorEvent) {
        console.log('This is client side error');
        errorMsg = `Error: ${err.error.message}`;
      } else {
        console.log('This is server side error');
        errorMsg = `Error Code: ${err.status},  Message: ${err.message}`;
      }
      console.log(errorMsg);


      // ✅ gestion refresh token
      //🔴 401 Unauthorized
      if (err instanceof HttpErrorResponse && !authReq.url.includes(ConsultantEndPointURI.AUTH.LOGIN)
        && err.status === 401) {

        this.toastService.error(` REFRESH: ${err.status}`);
        this.toastService.warn('Session expirée');

        return this.handle401Error(authReq, next);
      }
      //🔴 403 Forbidden

      if (err.status === 403) {
        return this.handle403Error(err);
      }
      //0 Server Unavailable
      if (err.status === 0) {
        console.error('Serveur indisponible');

        this.toastService.error(
          'Serveur indisponible. Veuillez réessayer plus tard.'
        );
        return throwError(() => err);
      }
      //🔴 500 Internal Server Error
      if (err.status === 500) {
        console.error('Erreur interne du serveur');
        this.toastService.error(
          'Une erreur est survenue côté serveur. Veuillez réessayer plus tard.'
        );

        return throwError(() => err);
      }
      // 🔴 autres erreurs
      this.toastService.error('Erreur serveur');

      return throwError(() => err);
    }));
  }


  /**
   *  ici j'ai envi de mettre toutes interception
   *
   * Methode pour Refresh en cas que le token de connexion expire
   *
   * à lire
   * https://github.com/bezkoder/angular-12-jwt-refresh-token/blob/master/src/app/_helpers/auth.interceptor.ts
   * https://www.bezkoder.com/angular-12-refresh-token/
   * @param request
   * @param next
   * @returns
   */
  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {

    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      const refreshToken = this.storageService.getRefreshToken();

      if (refreshToken)
        // appel service de refresh
        return this.authService.refreshToken(refreshToken).pipe(
          switchMap((token: TokenRefreshResponse) => {

            this.isRefreshing = false;

            this.storageService.saveToken(token.accessToken);
            this.refreshTokenSubject.next(token.accessToken);

            return next.handle(this.addTokenHeader(request, token.accessToken));
          }),
          catchError((err) => {
            this.isRefreshing = false;
            this.storageService.signOut();
            this.authService.logout();
            return throwError(() => err);
          })
        );
    }

    return this.refreshTokenSubject.pipe(
      filter(token => token !== null),
      take(1),
      switchMap(token =>
        next.handle(this.addTokenHeader(request, token!)))
    );
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    /* for Spring Boot back-end */
    return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });

    /* for Node.js Express back-end */
    //  return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, token) });
  }

  private handle403Error(err: HttpErrorResponse): Observable<never> {
    console.warn('Accès refusé (403)');

    // 👉 UX simple
    this.toastService.error('Accès interdit');

    // 👉 logout
    this.storageService.signOut();
    this.authService.logout();

    return throwError(() => err);
  }

}
