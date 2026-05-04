import { Injectable } from '@angular/core';
import { ConsultantApiRepository } from '../repository/consultant-api-repository';
import { Consultant } from '../../models/consultant-model';
import { CreateConsultant } from '../../models/CreateConsultant';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class CreateConsultantUseCase {
   constructor(private repo: ConsultantApiRepository) {}

  execute(data: CreateConsultant) {
    return this.repo.create(data);
  }
  getAll(): Observable<Consultant[]> {
    return this.repo.getAll();
  }
}
