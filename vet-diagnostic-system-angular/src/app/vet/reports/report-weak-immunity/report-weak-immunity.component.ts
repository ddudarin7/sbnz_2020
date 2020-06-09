import { Component, OnInit } from '@angular/core';
import {PatientService} from '../../../core/services/patient.service';
import { Disease } from 'src/app/shared/model/disease';
import { ReportWeakImmunityDTO } from 'src/app/shared/model/report-weak-immunity-dto';

@Component({
  selector: 'app-report-weak-immunity',
  templateUrl: './report-weak-immunity.component.html',
  styleUrls: ['./report-weak-immunity.component.css']
})
export class ReportWeakImmunityComponent implements OnInit {

  public result:ReportWeakImmunityDTO[];

  constructor(private patientService:PatientService) { 
    
  }

  ngOnInit(): void {
    this.patientService.weakImmunity().then(
      res=>{
        this.result=res;
        console.log(res);
      }
    );
  }

}
