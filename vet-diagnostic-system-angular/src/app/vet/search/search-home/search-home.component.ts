import { Component, OnInit } from '@angular/core';
import { Symptom } from 'src/app/shared/model/symptom';
import {SymptomService} from '../../../core/services/symptom.service';
import {SelectItem} from 'primeng/api';

@Component({
  selector: 'app-search-home',
  templateUrl: './search-home.component.html',
  styleUrls: ['./search-home.component.css']
})
export class SearchHomeComponent implements OnInit {

  selectSymptoms:SelectItem[];
  symptoms:Symptom[];
  selectedSymptoms:Symptom[];

  constructor(private symptomService:SymptomService) { 
    this.selectSymptoms=[];
  }

  ngOnInit(): void {
    this.loadAllSymptoms();
  }

  loadAllSymptoms(){
    this.symptomService.getAllSymptoms().then(
      res=>{
        this.symptoms=res;
        this.symptoms.forEach(s => {
          this.selectSymptoms.push({label:s.name.split("_").join(" ").toLowerCase(),value:s});
        });
        console.log(this.symptoms);
      }
    );
  }

}
