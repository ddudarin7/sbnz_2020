import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { AddPatientFormComponent } from './add-patient-form/add-patient-form.component';
import { ShowAllPatientsComponent } from './show-all-patients/show-all-patients.component';
import { SearchModule } from './search/search.module';


const routes: Routes = [
  {path:'home',component: HomePageComponent,children:[
    {path:'',component:ShowAllPatientsComponent},
    {path:'add-patient',component:AddPatientFormComponent},
    {path:'search',loadChildren:'./search/search.module#SearchModule'}
  ]}];


@NgModule({
  declarations: [
    HomePageComponent, 
    AddPatientFormComponent, 
    ShowAllPatientsComponent],
  imports: [
    CommonModule,
    SearchModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class VetModule { }
