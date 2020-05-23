import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path:'',loadChildren:'./log-in/log-in.module#LogInModule'},
  {path:'vet',loadChildren:'./vet/vet.module#VetModule'},
  {path: 'vet/patients', loadChildren: './patient/patient.module#PatientModule'}];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
