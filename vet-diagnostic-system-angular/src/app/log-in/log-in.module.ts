import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogInComponent } from './log-in/log-in.component';
import { RouterModule, Routes } from '@angular/router';
import {FormsModule} from '@angular/forms';

const routes: Routes = [
  {path:'',children:[
    {path:'',component:LogInComponent},
    {path:'log-in',component:LogInComponent}
  ]}];

@NgModule({
  declarations: [LogInComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LogInModule { }
