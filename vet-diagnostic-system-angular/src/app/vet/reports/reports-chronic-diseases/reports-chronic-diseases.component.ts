import { Component, OnInit } from '@angular/core';
import {PatientService} from '../../../core/services/patient.service';
import { Disease } from 'src/app/shared/model/disease';
import { ReportChronicDiseaseDTO } from 'src/app/shared/model/report-chronic-disease-dto';


@Component({
  selector: 'app-reports-chronic-diseases',
  templateUrl: './reports-chronic-diseases.component.html',
  styleUrls: ['./reports-chronic-diseases.component.css']
})
export class ReportsChronicDiseasesComponent implements OnInit {

  public result:ReportChronicDiseaseDTO[];

  constructor(private patientService:PatientService) { 
    
  }

  ngOnInit(): void {
    this.patientService.chronicDiseases().then(
      res=>{
        this.result=res;
        console.log(res);
      }
    );
  }

}
