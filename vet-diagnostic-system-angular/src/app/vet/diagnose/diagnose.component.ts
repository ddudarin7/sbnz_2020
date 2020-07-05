import { Component, OnInit } from '@angular/core';
import {Symptom} from '../../shared/model/symptom';
import {SymptomService} from '../../core/services/symptom.service';
import {DiagnoseService} from '../../core/services/diagnose.service';
import {Diagnose} from '../../shared/model/diagnose';
import {ToastrService} from 'ngx-toastr';
import {Disease} from '../../shared/model/disease';
import {Router} from '@angular/router';
import { DiagnoseResult } from 'src/app/shared/model/diagnose-result';

@Component({
  selector: 'app-diagnose',
  templateUrl: './diagnose.component.html',
  styleUrls: ['./diagnose.component.css']
})
export class DiagnoseComponent implements OnInit {

  patientRecordNumber = '';

  allSymptoms: Symptom[] = [];
  selectedSymptoms = [];

  dropdownSettings = {};

  chosenTherapies = [];

  diagnoseNotMade = true;

  error:number;

  diseases:Disease[];

  diagnose = new Diagnose(null, new Disease(null, '', '', [],
    [], []), null, null, [], [],
    null, null, [], null);

  diagnoses:Diagnose[];

  constructor(private symptomService: SymptomService, private diagnoseService: DiagnoseService,
              private toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.error=1;
    this.diagnoseService.patientInFocus.subscribe(rec => this.patientRecordNumber = rec);
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'Select All',
      itemsShowLimit: 5,
      allowSearchFilter: true
    };
    this.loadSymptoms();
  }

  loadSymptoms(): void {
    this.symptomService.getAllSymptoms().then(
      res => {
        this.allSymptoms = res;
      }
    );
  }

  makeDiagnose(): void {
    this.diagnoseService.diagnose(this.patientRecordNumber, this.selectedSymptoms).then(
      res => {
        this.diagnoses = res;
        if(this.diagnoses.length!=1){
          this.diagnoseNotMade = true;
          this.error=this.diagnoses.length;
        }else{
          this.diagnoseNotMade=false;
          this.diagnose=this.diagnoses[0];
        }
        
      }
    ).catch(
      err => {
        this.toastr.error('Diagnose failed.');
      }
    );
  }

  confirmDiagnose(): void{
    this.diagnoseService.confirmDiagnose(this.diagnose).then(
      res => {
        this.diagnose = res;
        this.toastr.success('Diagnose confirmed.');
        this.router.navigate(['vet/home/diagnoses/' + this.diagnose.id]);
      }
    ).catch(
      err => {
        this.toastr.error('Diagnose not confirmed.');
      }
    );
  }

  confirmDiagnoseDiseaseSelect(name:string): void{
    this.diagnoseNotMade=false;

    for(let d of this.diagnoses){
      if(name==d.disease.name){
        this.diagnose=d;
      }
    }

    this.diagnoseNotMade=false;
  }

  removeTherapy(therapyId: number): void{
    this.chosenTherapies = [];
    for (const therapy of this.diagnose.therapies){
      if (therapy.id !== therapyId){
        this.chosenTherapies.push(therapy);
      }
    }
    this.diagnose.therapies = this.chosenTherapies;
  }

  diseaseInfo(name:string){
    this.router.navigate(['/vet/home/search/show/disease/'+name]);
  }

}
