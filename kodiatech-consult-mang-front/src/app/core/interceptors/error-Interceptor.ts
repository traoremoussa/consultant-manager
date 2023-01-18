import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


import { AuthService } from 'src/app/features/services/auth.service';
//@see https://stackoverflow.com/questions/72159944/angular-is-not-receiving-the-status-code-set-by-httpservletresponse-senderro-m
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
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






          alert(` ERROR: ${err.status}`)
            if (err.status === 401) {
              alert(` ERROR: ${err.status}`)
                // auto logout if 401 response returned from api
                this.authService.logout();
                location.reload();
            }

            const error = err.error.message || err.statusText;
            return throwError(()=> error);
        }))
    }
}
