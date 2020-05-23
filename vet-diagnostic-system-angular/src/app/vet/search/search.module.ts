import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchHomeComponent } from './search-home/search-home.component';
import { RouterModule, Routes } from '@angular/router';
import {AccordionModule} from 'primeng/accordion';
import {MultiSelectModule} from 'primeng/multiselect';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';


const routes: Routes = [
  {path:'',component:SearchHomeComponent }];

@NgModule({
  declarations: [SearchHomeComponent],
  imports: [
    CommonModule,
    AccordionModule,
    MultiSelectModule,
    FormsModule,
    SharedModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class SearchModule { }
