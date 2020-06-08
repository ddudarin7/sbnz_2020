import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { ShowAllVetsComponent } from './show-all-vets/show-all-vets.component';

import {FormsModule} from '@angular/forms';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {ToastrModule} from 'ngx-toastr';
import { CreateNewVetComponent } from './create-new-vet/create-new-vet.component';
import { ShowAllMedicationsComponent } from './show-all-medications/show-all-medications.component';
import { CreateNewMedicationComponent } from './create-new-medication/create-new-medication.component';
import { ShowAllDiseasesComponent } from './show-all-diseases/show-all-diseases.component';
import { CreateNewDiseaseComponent } from './create-new-disease/create-new-disease.component';
import { CreateNewAdminComponent } from './create-new-admin/create-new-admin.component';
import { ShowAllAdminsComponent } from './show-all-admins/show-all-admins.component';


const routes: Routes = [
  {path: 'home', component: HomePageComponent, children: [
    {path: '', component: ShowAllVetsComponent},
    {path: 'show-all-vets', component: ShowAllVetsComponent},
    {path: 'create-new-vet', component: CreateNewVetComponent},
    {path: 'show-all-admins', component: ShowAllAdminsComponent},
    {path: 'create-new-admin', component: CreateNewAdminComponent},
    {path: 'show-all-medications', component: ShowAllMedicationsComponent},
    {path: 'create-new-medication', component: CreateNewMedicationComponent},
    {path: 'show-all-diseases', component: ShowAllDiseasesComponent},
    {path: 'create-new-disease', component: CreateNewDiseaseComponent},]
  }
];

@NgModule({
  declarations: [
    HomePageComponent, 
    ShowAllVetsComponent, 
    CreateNewVetComponent, 
    ShowAllMedicationsComponent, 
    CreateNewMedicationComponent, 
    ShowAllDiseasesComponent, 
    CreateNewDiseaseComponent, 
    CreateNewAdminComponent, 
    ShowAllAdminsComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    NgMultiSelectDropDownModule.forRoot(),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ToastrModule.forRoot()
  ],
  exports: [RouterModule]
})
export class AdminModule { }
