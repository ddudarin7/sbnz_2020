import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SymptomPipe } from './pipes/symptom.pipe';



@NgModule({
  declarations: [SymptomPipe],
  imports: [
    CommonModule,
  ],
  exports:[SymptomPipe]
})
export class SharedModule { }
