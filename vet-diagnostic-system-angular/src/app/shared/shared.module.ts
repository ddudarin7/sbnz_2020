import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SymptomPipe } from './pipes/symptom.pipe';
import { LowerCasePipe } from './pipes/lower-case.pipe';



@NgModule({
  declarations: [SymptomPipe, LowerCasePipe],
  imports: [
    CommonModule
  ],
  exports:[
    SymptomPipe,
    LowerCasePipe
  ]
})
export class SharedModule { }
