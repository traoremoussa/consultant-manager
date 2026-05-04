import { Component, inject } from '@angular/core';
import { CreateConsultantUseCase } from '../../services/create-consultant-use-case';
import { toSignal } from '@angular/core/rxjs-interop';
import { Consultant } from 'src/app/features/models/consultant-model';
import { MaterialModule } from 'src/app/shared/material.module';

@Component({
  selector: 'app-consultant-list',
  imports: [MaterialModule],
  templateUrl: './consultant-list.html',
  styleUrl: './consultant-list.scss',
})
export class ConsultantList {


  private createConsultant = inject(CreateConsultantUseCase);

  listConsultant = toSignal(
  this.createConsultant.getAll(),
  {
     initialValue: [] as Consultant[]
  }
);
}
