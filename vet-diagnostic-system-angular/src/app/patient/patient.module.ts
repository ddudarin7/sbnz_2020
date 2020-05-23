import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientInfoComponent } from './patient-info/patient-info.component';
import { PatientsListComponent } from './patients-list/patients-list.component';


@NgModule({
  declarations: [PatientInfoComponent, PatientsListComponent],
  imports: [
    CommonModule
  ]
})
export class PatientModule { }
