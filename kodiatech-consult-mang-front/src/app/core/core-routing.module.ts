import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { BoardUserComponent } from './components/board-user/board-user.component';

const routes: Routes = [
  { path: 'user', component: BoardUserComponent },
  { path: '**',  component: PageNotFoundComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
