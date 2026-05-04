import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { CreateConsultant } from '../../models/CreateConsultant';
import { ConsultantEndPointURI } from 'src/app/core/constantes/consultant-end-point-uri';
import { environment } from 'src/environments/environment';
import { catchError, Observable, of, tap, timeout } from 'rxjs';
import { Consultant } from '../../models/consultant-model';


@Injectable({
  providedIn: 'root',
})
export class ConsultantApiRepository {

    constructor(private http: HttpClient) {}


  private buildUrl(path: string): string {
    return `${environment.apiUrl}${path}`;
  }

  create(data: CreateConsultant) {
    console.log('Creating consultant with data:', data);

    return this.http.post(this.buildUrl(ConsultantEndPointURI.ADMIN.ADD_CONSULTANT), data)
    .pipe(
      tap(res => console.log('Consultant créé:', res))
    );
  }

  // READ - get all consultants
  getAll() : Observable<Consultant[]>{
    return this.http.get<Consultant[]>(
      this.buildUrl(ConsultantEndPointURI.ADMIN.CONSULTANTS)
    ).pipe(
    timeout(5000),
    catchError(err => {
      return of([]); // logique métier
    })
  );
  }

  // READ - get consultant by id
  getById(id: number | string) {
    return this.http.get(
      this.buildUrl(ConsultantEndPointURI.ADMIN.GET_CONSULTANT_BY_ID(id))
    );
  }

  // UPDATE
  update(id: number | string, data: any) {
    return this.http.put(
      this.buildUrl(ConsultantEndPointURI.ADMIN.GET_CONSULTANT_BY_ID(id)),
      data
    );
  }

  // DELETE
  delete(id: number | string) {
    return this.http.delete(
      this.buildUrl(ConsultantEndPointURI.ADMIN.GET_CONSULTANT_BY_ID(id))
    );
  }
}
