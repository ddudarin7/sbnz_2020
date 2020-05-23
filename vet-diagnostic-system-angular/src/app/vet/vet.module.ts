import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { AddPatientFormComponent } from './add-patient-form/add-patient-form.component';
import { ShowAllPatientsComponent } from './show-all-patients/show-all-patients.component';
import { SearchComponent } from './search/search.component';
import { PatientInfoComponent } from './patient-info/patient-info.component';
import {FormsModule} from '@angular/forms';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import { SearchModule } from './search/search.module';

const routes: Routes = [
  {path:'home',component: HomePageComponent,children:[
    {path:'',component:ShowAllPatientsComponent},
    {path:'add-patient',component:AddPatientFormComponent},
    {path:'search',loadChildren:'./search/search.module#SearchModule'}, {path: 'patient-info/:recordNumber', component: PatientInfoComponent}]
  }
  ];


@NgModule({
  declarations: [
    HomePageComponent, 
    AddPatientFormComponent, 
    ShowAllPatientsComponent, 
    SearchComponent, PatientInfoComponent],
  imports: [
    CommonModule,
    FormsModule,
    SearchModule,
    RouterModule.forChild(routes),
    NgMultiSelectDropDownModule.forRoot()
  ],
  exports: [RouterModule]
})
export class VetModule { }
