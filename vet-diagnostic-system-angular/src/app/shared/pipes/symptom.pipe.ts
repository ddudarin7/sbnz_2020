import { Pipe, PipeTransform } from '@angular/core';
import { Symptom } from '../model/symptom';

@Pipe({
  name: 'symptomPipe'
})
export class SymptomPipe implements PipeTransform {

  transform(symptoms: Symptom[]): any {
    let keys = [];
    if(!symptoms){
      return keys;
    }
    symptoms.forEach(function(item){
      keys.push(item.name.split("_").join(" ").toLowerCase());
    });
    return keys;
  }

}