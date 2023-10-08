import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, filter, switchMap, take } from 'rxjs/operators';


import { AuthService } from 'src/app/features/services/auth.service';
import { ConsultantEndPointURI } from '../constantes/consultant-end-point-uri';
import { StorageService } from 'src/app/features/services/storage.service';
import { TokenRefreshResponse } from 'src/app/features/models/token-refresh-reponse-model';
//@see https://stackoverflow.com/questions/72159944/angular-is-not-receiving-the-status-code-set-by-httpservletresponse-senderro-m

const TOKEN_HEADER_KEY = 'Authorization';  // for Spring Boot back-end
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

    constructor(private authService: AuthService,
      private storageService: StorageService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      let authReq = request;
      alert(` ERROR: ${authReq.url}- ${!authReq.url.includes(ConsultantEndPointURI.ENDPOINT_LOGIN_USER)}`)
      const token = this.storageService.getRefreshToken();
     // alert(` TOKEN test:${token}`)
        return next.handle(request).pipe(catchError((err :HttpErrorResponse)=> {

          let errorMsg = '';
          if (err.error instanceof ErrorEvent) {
             console.log('This is client side error');
             errorMsg = `Error: ${err.error.message}`;
          } else {
             console.log('This is server side error');
             errorMsg = `Error Code: ${err.status},  Message: ${err.message}`;
          }
          console.log(errorMsg);








          if (err instanceof HttpErrorResponse && !authReq.url.includes(ConsultantEndPointURI.ENDPOINT_LOGIN_USER)
           && err.status === 401) {
            alert(` REFRESH: ${err.status}`);
            return this.handle401Error(authReq, next);
          }
            if (err.status === 401) {

              alert(` ERROR-deconnection: ${err.status}`)
                // auto logout if 401 response returned from api
                this.authService.logout();
                location.reload();
            }

            const error = err.error.message || err.statusText;
            return throwError(()=> error);
        }))
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

        const token = this.storageService.getRefreshToken();

        if (token)
         // appel service de refresh
          return this.authService.refreshToken(token).pipe(
            switchMap((token: TokenRefreshResponse) => {
              this.isRefreshing = false;

              this.storageService.saveToken(token.accessToken);
              this.refreshTokenSubject.next(token.accessToken);

              return next.handle(this.addTokenHeader(request, token.accessToken));
            }),
            catchError((err) => {
              this.isRefreshing = false;

              this.storageService.signOut();
              return throwError(()=>err);
            })
          );
      }

      return this.refreshTokenSubject.pipe(
        filter(token => token !== null),
        take(1),
        switchMap((token) => next.handle(this.addTokenHeader(request, token)))
      );
    }

    private addTokenHeader(request: HttpRequest<any>, token: string) {
      /* for Spring Boot back-end */
      // return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });

      /* for Node.js Express back-end */
      return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, token) });
    }

}
