import { StepperOverviewComponent } from './components/stepper-overview/stepper-overview.component';
import { InscriptionComponent } from './components/inscription/inscription.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './components/guars/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'inscruption', component: InscriptionComponent },
  {path:   'stepper',    component:StepperOverviewComponent,canActivate: [AuthGuard]},
  {path: '', redirectTo:'/stepper', pathMatch:'full'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
