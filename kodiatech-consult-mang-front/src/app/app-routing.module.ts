
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [//Lazy loading
{ path: '', redirectTo:'features/stepper', pathMatch:'full'},
{path:'features', loadChildren:()=> import('./features/features.module').then(m=>m.FeaturesModule)},
{path:'core', loadChildren:()=> import('./core/core.module').then(m=>m.CoreModule)},

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
