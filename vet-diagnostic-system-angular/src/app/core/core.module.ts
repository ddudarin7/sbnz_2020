import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { LogInService } from './services/log-in.service';
import { VaccinationService } from './services/vaccination.service';
import { AuthGuard } from './security/auth-guard';
import {AdminGuard} from './security/admin-guard'
import {VetGuard} from './security/vet-guard';
import { MedicineService } from './services/medicine.service';
import { IngredientService } from './services/ingredient.service';
import { PatientService } from './services/patient.service';
import { DiseaseService } from './services/disease.service';
import {DiagnoseService} from './services/diagnose.service';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers:[
    PatientService,
    MedicineService,
    IngredientService,
    LogInService,
    VaccinationService,
    DiseaseService,
    AuthGuard,
    AdminGuard,
    VetGuard,
    DiagnoseService
  ]
})
export class CoreModule { }
