import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ConsultantEndPointURI } from '../../core/constantes/consultant-end-point-uri';
import { Consultant } from '../models/consultant-model';

@Injectable({
  providedIn: 'root'
})
export class ConsultantService {

  constructor(private http: HttpClient) { }
/**
 * fill consultant by id
 * @param id
 * @returns
 */
 public getConsultant(id:string):Observable<Consultant>{
   // alert(` Ins: ${id}`)
    return this.http.get<Consultant>(`${environment.apiUrl}${ConsultantEndPointURI.ENDPOINT_CONSULTANT}${id}`)
    .pipe(catchError(this.handleError));
  }

   //handle any error encounted while sending http request
   private handleError(error: Response | any) {
    console.error("ApiService::handleError", error);
    return throwError(()=>error);
  }






  getUserBoard(): Observable<any> {
    return this.http.get(environment.apiUrl + 'user', { responseType: 'text' });
  }

}
