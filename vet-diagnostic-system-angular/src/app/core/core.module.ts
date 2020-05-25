import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { LogInService } from './services/log-in.service';
import { VaccinationService } from './services/vaccination.service';
import { AuthGuard } from './security/auth-guard';
import {AdminGuard} from './security/admin-guard'
import {VetGuard} from './security/vet-guard';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers:[
    LogInService,
    VaccinationService,
    AuthGuard,
    AdminGuard,
    VetGuard
  ]
})
export class CoreModule { }
