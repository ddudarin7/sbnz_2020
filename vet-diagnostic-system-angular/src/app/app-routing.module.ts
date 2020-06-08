import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/security/auth-guard';
import { VetGuard } from './core/security/vet-guard';
import { AdminGuard } from './core/security/admin-guard';


const routes: Routes = [
  {path:'',loadChildren:'./log-in/log-in.module#LogInModule',canActivate:[AuthGuard]},
  {path:'vet',loadChildren:'./vet/vet.module#VetModule',canActivate:[AuthGuard,VetGuard]},
  {path:'admin',loadChildren:'./admin/admin.module#AdminModule',canActivate:[AuthGuard,AdminGuard]}];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
