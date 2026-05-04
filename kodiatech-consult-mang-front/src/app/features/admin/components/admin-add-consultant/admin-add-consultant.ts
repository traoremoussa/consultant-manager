import { CoreModule } from './../../../../core/core.module';
import { Component, signal, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateConsultantUseCase } from '../../services/create-consultant-use-case';
import { CreateConsultant } from 'src/app/features/models/CreateConsultant';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/shared/material.module';
import { form,  required, email, submit, FormField} from '@angular/forms/signals';
import { LoaderService } from 'src/app/core/services/LoaderService';
import { firstValueFrom } from 'rxjs';
@Component({
  selector: 'app-admin-add-consultant',
  imports: [CommonModule,ReactiveFormsModule,MaterialModule,FormField],
  templateUrl: './admin-add-consultant.html',
  styleUrl: './admin-add-consultant.scss',

})
export class AdminAddConsultant {
   // 🟢 modèle source (signal)
  consultant = signal<CreateConsultant>({
    nom: '',
    prenom: '',
    email: '',
    telephone: ''
  });

  // 🟢 form basé sur signal
consultantForm = form(this.consultant, (path) => {
  return {
    nom: required(path.nom),
    prenom: required(path.prenom),
    email: [
      required(path.email),
      email(path.email)
       ],
  telephone: [
     required(path.telephone)
      ]
  };
});

  successMessage = signal('');
  errorMessage = signal('');

  constructor(private createConsultant: CreateConsultantUseCase) {}

onSubmit(event: Event) {
  event.preventDefault();

 submit(this.consultantForm, async () => {
      try {
        await firstValueFrom(
         this.createConsultant.execute(this.consultant()));

        this.successMessage.set(`Consultant ${this.consultant().nom} ajouté ✅`);
        this.errorMessage.set('');

        // reset
        this.consultant.set({
          nom: '',
          prenom: '',
          email: '',
          telephone: ''
        });

      } catch (err: any) {
         const message = err?.error || 'Erreur lors de la création ❌';
        this.errorMessage.set(message);
        this.successMessage.set('');
      }
    });

}


 form(consultant: WritableSignal<CreateConsultant>, arg1: (path: any) => void) {
  throw new Error('Function not implemented.');
}

}
