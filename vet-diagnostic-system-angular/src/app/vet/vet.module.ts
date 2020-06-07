import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { AddPatientFormComponent } from './add-patient-form/add-patient-form.component';
import { ShowAllPatientsComponent } from './show-all-patients/show-all-patients.component';
import { PatientInfoComponent } from './patient-info/patient-info.component';
import { FormsModule } from '@angular/forms';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { DialogModule } from 'primeng/dialog';
import { SearchModule } from './search/search.module';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { ToastrModule } from 'ngx-toastr';
import { DiagnoseComponent } from './diagnose/diagnose.component';
import { DiagnoseInfoComponent } from './diagnose-info/diagnose-info.component';
import { AddVaccinationFormComponent } from './vaccinations/add-vaccination-form/add-vaccination-form.component';
import { ReportsModule } from './reports/reports.module';
import {ToastModule} from 'primeng/toast';

const routes: Routes = [
  {path: 'home', component: HomePageComponent, children: [
    {path: '', component: ShowAllPatientsComponent},
    {path: 'add-patient', component: AddPatientFormComponent},
    {path: 'search', loadChildren: './search/search.module#SearchModule'},
    {path: 'patient-info/:recordNumber', component: PatientInfoComponent},
    {path: 'diagnose', component: DiagnoseComponent},
    {path: 'diagnoses/:id', component: DiagnoseInfoComponent},
    {path: 'add-vaccination', component: AddVaccinationFormComponent},
    {path: 'reports', loadChildren:'./reports/reports.module#ReportsModule'}]
  }
  ];


@NgModule({
  declarations: [
    HomePageComponent,
    AddPatientFormComponent,
    ShowAllPatientsComponent,
    PatientInfoComponent,
    DiagnoseComponent,
    DiagnoseInfoComponent,
    AddVaccinationFormComponent],
  imports: [
    CommonModule,
    DialogModule,
    FormsModule,
    SearchModule,
    ReportsModule,
    RouterModule.forChild(routes),
    NgMultiSelectDropDownModule.forRoot(),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ToastModule,
    ToastrModule.forRoot()
  ],
  exports: [RouterModule]
})
export class VetModule { }
