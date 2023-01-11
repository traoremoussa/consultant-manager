import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthenticationRequest } from '../models/authentication-request-model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};



@Injectable({
  providedIn: 'root'
})

export class AuthService {

  authenticated = false;

  constructor(private http: HttpClient
   ) { }

    getToken() {/*
      let token =  sessionStorage.getItem('authenticationToken')  as string;
      return token.length >0? token:'';*/
      return '';
     }

     login(authenticationRequest:AuthenticationRequest): Observable<any> {
    //  sessionStorage.setItem('user',"");
      return this.http.post(
        environment.apiUrl + '/v1/auth/authenticate',
        authenticationRequest,httpOptions

      );

    }

}
