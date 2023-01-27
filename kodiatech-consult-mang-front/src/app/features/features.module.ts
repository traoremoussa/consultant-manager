import { AuthService } from './services/auth.service';
import { InscriptionComponent } from './components/inscription/inscription.component';
import { LoginComponent } from './components/login/login.component';
import { FeaturesRoutingModule } from './features-routing.module';
import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { CvComponent } from './components/cv/cv.component';
import { DossierCandidatureComponent } from './components/dossier-candidature/dossier-candidature.component';
import { SoumissionDossierComponent } from './components/soumission-dossier/soumission-dossier.component';
import { StepperOverviewComponent } from './components/stepper-overview/stepper-overview.component';
import { AuthGuard } from './components/guars/auth.guard';
import { ModalFormComponent } from './components/shared/modal-form/modal-form.component';
import { CvFormFieldComponent } from './components/shared/cv-form-field/cv-form-field.component';
import {  CompetenceTechComponent } from './components/cv/cv-child/competence-tech/competence-tech.component';



@NgModule({
  declarations: [
    LoginComponent,
    InscriptionComponent,
    HomeComponent,
    CvComponent,
    DossierCandidatureComponent,
    SoumissionDossierComponent,
    StepperOverviewComponent,
    ModalFormComponent,
    CvFormFieldComponent,
    CompetenceTechComponent
  ],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    SharedModule,
  ],
  //les componennt à exposer
  exports:[],

  //les service
  providers: [
     AuthService,
     AuthGuard
  ]
})
export class FeaturesModule { }
