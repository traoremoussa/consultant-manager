import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Observable, BehaviorSubject, switchMap, map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthenticationRequest } from '../models/authentication-request-model';
import { AuthenticationResponse } from '../models/authentication-response-model';
import { StorageService } from './storage.service';
import { ConsultantEndPointURI } from 'src/app/core/constantes/consultant-end-point-uri';
import { TokenRefreshResponse } from '../models/token-refresh-reponse-model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  //@Output() getauthReponse: EventEmitter<AuthenticationResponse> = new EventEmitter();
  /* person = { authenticationToken:"",badRequest:"",id: "", nom: '' };
  private getauthReponse$ = new BehaviorSubject<AuthenticationResponse>({...this.person, nom:""});*/

  public getauthReponse$: BehaviorSubject<string> = new BehaviorSubject<string>(
    ''
  );

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {}

  login(authenticationRequest: AuthenticationRequest) {
    //  sessionStorage.setItem('user',"");
    return this.http
      .post<AuthenticationResponse>(
        environment.apiUrl + ConsultantEndPointURI.ENDPOINT_LOGIN_USER,
        authenticationRequest,
        httpOptions
      )
      .pipe(
        map((reponseData: AuthenticationResponse) => {
          //le retour
          this.setauthReponse(reponseData.nom);
          console.log(
            '-------------get----------->' + this.getauthReponse$.value
          );

          this.storageService.saveToken(reponseData.authenticationToken);
          this.storageService.saveRefreshToken(reponseData.refreshToken);
          this.storageService.saveUser(reponseData);
        })
      );
  }


  /*
  logout() {
    this.storageService.remove();
  }

  private getToken() {
    let token = sessionStorage.getItem('authenticationToken') as string;
    //return token.length >0? token:''.
    return token;
  }
*/
  public getUser() {
    return this.storageService.getUser();
  }

  //retour
  private setauthReponse(loading: string) {
    //appliquer
    this.getauthReponse$.next(loading);
  }

  //----- 23/08/2023
  logout() {
    this.storageService.signOut();
  }
  refreshToken(token: string) {
    return this.http.post<TokenRefreshResponse>(   environment.apiUrl + 'ENDPOINT_LOGIN_REFRESH', {
      refreshToken: token
    }, httpOptions);
  }

}
