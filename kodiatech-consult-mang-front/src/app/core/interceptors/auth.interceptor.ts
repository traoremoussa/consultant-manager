import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "src/app/features/services/auth.service";
import { StorageService } from "src/app/features/services/storage.service";



 const TOKEN_HEADER_KEY = 'Authorization';  // for Spring Boot back-end
//const TOKEN_HEADER_KEY = 'x-access-token';    // for Node.js Express back-end


@Injectable()
export class AuthInterceptor implements HttpInterceptor {



  constructor(private authService: AuthService,private storageService: StorageService) {}
/**
 *
 *  vous retournez  next.handle()  en y passant la nouvelle
 *  requête – c'est ce qui permet à la requête de continuer son chemin.

 */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if(this.storageService.getToken()){
   // alert(` InTer: ${this.storageService.getToken()}`)
    const headers = new HttpHeaders()
      .append('Authorization', `Bearer ${this.storageService.getToken()}`);
      //TODO SUpprim? pas utiliser
      const httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      };
      req= req.clone({    headers });
    }
    return next.handle(req);

  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    /* for Spring Boot back-end */
    // return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });

    /* for Node.js Express back-end */
    return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, token) });

  }



}
