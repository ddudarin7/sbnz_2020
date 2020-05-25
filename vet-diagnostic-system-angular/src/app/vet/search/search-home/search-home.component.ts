import { Component, OnInit } from '@angular/core';
import { Symptom } from 'src/app/shared/model/symptom';
import {SymptomService} from '../../../core/services/symptom.service';
import {SelectItem} from 'primeng/api';
import {MessageService} from 'primeng/api';
import {DiseaseService} from '../../../core/services/disease.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-home',
  templateUrl: './search-home.component.html',
  styleUrls: ['./search-home.component.css'],
  providers: [MessageService]
})
export class SearchHomeComponent implements OnInit {

  selectSymptoms:SelectItem[];
  symptoms:Symptom[];
  selectedSymptoms:Symptom[];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private symptomService:SymptomService,
    private diseaseService:DiseaseService,
    private messageService: MessageService) { 
    this.selectSymptoms=[];
    this.selectedSymptoms=[];
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

  search(){
    if(this.selectedSymptoms.length===0){
      this.messageService.add({severity:'error', summary:'Error!', detail:'You need to select one or more symptoms.',life:5000});
      return;
    }
    this.diseaseService.findAllwithSymptoms(this.selectedSymptoms).then(
      res=>{
        this.diseaseService.sharedData=res;
        this.router.navigate(['/vet/home/search/show/diseases']);
      },
      error=>{
        this.messageService.add({severity:'error', summary:'Error!', detail:'An unexpected error occurred.',life:5000});
      }
    );
  }

}
