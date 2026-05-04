import { StepperOverviewComponent } from './components/stepper-overview/stepper-overview.component';
import { InscriptionComponent } from './components/inscription/inscription.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './components/guars/auth.guard';
import { AdminAddConsultant } from './admin/components/admin-add-consultant/admin-add-consultant';
import { ConsultantList } from './admin/components/consultant-list/consultant-list';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'inscription', component: InscriptionComponent, resolve: {} },
  { path: 'add-consultant', component: AdminAddConsultant, canActivate: [AuthGuard]},
  { path: 'list-consultant', component: ConsultantList},
  {
    path: 'stepper',
    component: StepperOverviewComponent,
    canActivate: [AuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FeaturesRoutingModule {}
