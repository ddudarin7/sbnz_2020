import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogInModule } from './log-in/log-in.module';
import { CoreModule } from './core/core.module';
import { VetModule } from './vet/vet.module';
import { AdminModule } from './admin/admin.module';
import {PatientService} from './core/services/patient.service';
import {MedicineService} from './core/services/medicine.service';
import {IngredientService} from './core/services/ingredient.service';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    LogInModule,
    VetModule,
    AdminModule,
    FormsModule,
    CoreModule,
    AppRoutingModule,
    NgMultiSelectDropDownModule.forRoot()
  ],
  providers: [
    PatientService,
    MedicineService,
    IngredientService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
