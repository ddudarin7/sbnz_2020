import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchHomeComponent } from './search-home/search-home.component';
import { RouterModule, Routes } from '@angular/router';
import {AccordionModule} from 'primeng/accordion';
import {MultiSelectModule} from 'primeng/multiselect';
import {TableModule} from 'primeng/table';
import {ToastModule} from 'primeng/toast';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { ShowDiseasesComponent } from './show-diseases/show-diseases.component';


const routes: Routes = [
  {path:'',component:SearchHomeComponent },
  {path:'show/diseases',component:ShowDiseasesComponent}
];

@NgModule({
  declarations: [
    SearchHomeComponent, 
    ShowDiseasesComponent],
  imports: [
    CommonModule,
    AccordionModule,
    MultiSelectModule,
    ToastModule,
    TableModule,
    FormsModule,
    SharedModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class SearchModule { }
