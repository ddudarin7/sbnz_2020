import { Component, OnInit } from '@angular/core';
import {Symptom} from '../../shared/model/symptom';
import {SymptomService} from '../../core/services/symptom.service';
import {DiagnoseService} from '../../core/services/diagnose.service';
import {Diagnose} from '../../shared/model/diagnose';
import {ToastrService} from 'ngx-toastr';
import {Disease} from '../../shared/model/disease';
import {Router} from '@angular/router';

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

  diagnose = new Diagnose(null, new Disease(null, '', '', [],
    [], []), null, null, [], [],
    null, null, [], null);

  constructor(private symptomService: SymptomService, private diagnoseService: DiagnoseService,
              private toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
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
        this.diagnose = res;
        this.diagnoseNotMade = false;
      }
    ).catch(
      err => {
        this.toastr.error('Diagnose failed.');
      }
    );
  }

  confirmDiagnose(): void{
    console.log(this.diagnose);
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

  removeTherapy(therapyId: number): void{
    this.chosenTherapies = [];
    for (const therapy of this.diagnose.therapies){
      if (therapy.id !== therapyId){
        this.chosenTherapies.push(therapy);
      }
    }
    this.diagnose.therapies = this.chosenTherapies;
  }

}
