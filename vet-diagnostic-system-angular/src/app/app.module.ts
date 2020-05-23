import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogInModule } from './log-in/log-in.module';
import { CoreModule } from './core/core.module';
import { VetModule } from './vet/vet.module';
import { PatientModule} from './patient/patient.module';
import { PatientService } from './core/services/patient.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    LogInModule,
    VetModule,
    FormsModule,
    CoreModule,
    AppRoutingModule,
    PatientModule
  ],
  providers: [
    PatientService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
