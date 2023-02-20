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
import { LangueComponent } from './components/cv/cv-child/langue/langue.component';
import { FormationComponent } from './components/cv/cv-child/formation/formation.component';
import { ExperienceComponent } from './components/cv/cv-child/experience/experience.component';
import { ProjetPersonnelComponent } from './components/cv/cv-child/projet-personnel/projet-personnel.component';
import { CompentenceFonctionnelleComponent } from './components/cv/cv-child/compentence-fonctionnelle/compentence-fonctionnelle.component';



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
    CompetenceTechComponent,
    LangueComponent,
    FormationComponent,
    ExperienceComponent,
    ProjetPersonnelComponent,
    CompentenceFonctionnelleComponent
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
