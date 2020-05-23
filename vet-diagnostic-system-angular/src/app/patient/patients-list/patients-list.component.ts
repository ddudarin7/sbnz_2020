import { Component, OnInit } from '@angular/core';
import {Patient} from '../../shared/model/patient';
import {PatientService} from '../../core/services/patient.service';

@Component({
  selector: 'app-patients-list',
  templateUrl: './patients-list.component.html',
  styleUrls: ['./patients-list.component.css']
})
export class PatientsListComponent implements OnInit {

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
