import { Component, OnInit } from '@angular/core';
import {PatientService} from '../../core/services/patient.service';
import {Patient} from '../../shared/model/patient';

@Component({
  selector: 'app-show-all-patients',
  templateUrl: './show-all-patients.component.html',
  styleUrls: ['./show-all-patients.component.css']
})
export class ShowAllPatientsComponent implements OnInit {

  patients: Patient[];

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(): void {
    this.patientService.getPatients().then(
      res => {
        this.patients = JSON.parse(JSON.stringify(res))._embedded.patients;
      }
    );
  }

}
