import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchHomeComponent } from './search-home/search-home.component';
import { RouterModule, Routes } from '@angular/router';
import {AccordionModule} from 'primeng/accordion';
import {MultiSelectModule} from 'primeng/multiselect';
import {TableModule} from 'primeng/table';
import {ToastModule} from 'primeng/toast';
import {PanelModule} from 'primeng/panel';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { ShowDiseasesComponent } from './show-diseases/show-diseases.component';
import { ShowDiseaseComponent } from './show-disease/show-disease.component';


const routes: Routes = [
  {path:'',component:SearchHomeComponent },
  {path:'show/diseases',component:ShowDiseasesComponent},
  {path:'show/disease/:name',component:ShowDiseaseComponent}
];

@NgModule({
  declarations: [
    SearchHomeComponent, 
    ShowDiseasesComponent, 
    ShowDiseaseComponent],
  imports: [
    CommonModule,
    AccordionModule,
    MultiSelectModule,
    PanelModule,
    ToastModule,
    TableModule,
    FormsModule,
    SharedModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class SearchModule { }
