import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ConsultantEndPointURI } from '../models/consultant-end-point-uri';
import { Consultant } from '../models/consultant-model';

@Injectable({
  providedIn: 'root'
})
export class ConsultantService {

  constructor(private http: HttpClient) { }

  getConsultant(id:string):Observable<Consultant[]>{
    return this.http.get<Consultant[]>(`${environment.apiUrl}/${ConsultantEndPointURI.ENDPOINT_CONSULTANT}/id`);
  }
}
