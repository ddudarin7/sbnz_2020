import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ReportsHomeComponent } from './reports-home/reports-home.component';
import { ReportsChronicDiseasesComponent } from './reports-chronic-diseases/reports-chronic-diseases.component';
import {SelectButtonModule} from 'primeng/selectbutton';
import {TableModule} from 'primeng/table';

const routes: Routes = [
  {path:'',component:ReportsHomeComponent,children:[
    {path:'chronic-diseases', component:ReportsChronicDiseasesComponent}
  ]}];


@NgModule({
  declarations: [
    ReportsHomeComponent, 
    ReportsChronicDiseasesComponent
  ],
  imports: [
    CommonModule,
    TableModule,
    SelectButtonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ReportsModule { }
