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
import { ShowAllDiseasesComponent } from './show-all-diseases/show-all-diseases.component';
import { CreateNewDiseaseComponent } from './create-new-disease/create-new-disease.component';
import { CreateNewAdminComponent } from './create-new-admin/create-new-admin.component';
import { ShowAllAdminsComponent } from './show-all-admins/show-all-admins.component';
import { ShowAllMedicinesComponent } from './show-all-medicines/show-all-medicines.component';
import { CreateNewMedicineComponent } from './create-new-medicine/create-new-medicine.component';


const routes: Routes = [
  {path: 'home', component: HomePageComponent, children: [
    {path: '', component: ShowAllVetsComponent},
    {path: 'show-all-vets', component: ShowAllVetsComponent},
    {path: 'create-new-vet', component: CreateNewVetComponent},
    {path: 'show-all-admins', component: ShowAllAdminsComponent},
    {path: 'create-new-admin', component: CreateNewAdminComponent},
    {path: 'show-all-medicines', component: ShowAllMedicinesComponent},
    {path: 'create-new-medicine', component: CreateNewMedicineComponent},
    {path: 'show-all-diseases', component: ShowAllDiseasesComponent},
    {path: 'create-new-disease', component: CreateNewDiseaseComponent},]
  }
];

@NgModule({
  declarations: [
    HomePageComponent, 
    ShowAllVetsComponent, 
    CreateNewVetComponent, 
    ShowAllMedicinesComponent, 
    CreateNewMedicineComponent, 
    ShowAllDiseasesComponent, 
    CreateNewDiseaseComponent, 
    CreateNewAdminComponent, 
    ShowAllAdminsComponent, ShowAllMedicinesComponent, CreateNewMedicineComponent],
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
