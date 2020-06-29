import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ReportsHomeComponent } from './reports-home/reports-home.component';
import { ReportsChronicDiseasesComponent } from './reports-chronic-diseases/reports-chronic-diseases.component';
import {SelectButtonModule} from 'primeng/selectbutton';
import {TableModule} from 'primeng/table';
import { ReportWeakImmunityComponent } from './report-weak-immunity/report-weak-immunity.component';
import { ReportBreedDiseasesComponent } from './report-breed-diseases/report-breed-diseases.component';
import {FormsModule} from '@angular/forms';

const routes: Routes = [
  {path: '', component: ReportsHomeComponent, children: [
    {path: 'chronic-diseases', component: ReportsChronicDiseasesComponent},
    {path: 'weak-immunity', component: ReportWeakImmunityComponent},
      {path: 'breed-diseases', component: ReportBreedDiseasesComponent}
  ]}];


@NgModule({
  declarations: [
    ReportsHomeComponent,
    ReportsChronicDiseasesComponent,
    ReportWeakImmunityComponent,
    ReportBreedDiseasesComponent
  ],
  imports: [
    CommonModule,
    TableModule,
    SelectButtonModule,
    RouterModule.forChild(routes),
    FormsModule
  ],
  exports: [RouterModule]
})
export class ReportsModule { }
