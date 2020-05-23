import { Component, OnInit } from '@angular/core';
import {PatientService} from '../../core/services/patient.service';
import {Patient} from '../../shared/model/patient';
import {Router} from '@angular/router';

@Component({
  selector: 'app-show-all-patients',
  templateUrl: './show-all-patients.component.html',
  styleUrls: ['./show-all-patients.component.css']
})
export class ShowAllPatientsComponent implements OnInit {

  patients: Patient[];

  constructor(private patientService: PatientService, private router: Router) { }

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(): void {
    this.patientService.getPatients().then(
      res => {
        this.patients = res;
      }
    );
  }

  patientInfoClicked(recordNumber: string): void {
    this.router.navigate(['vet/home/patient-info/' + recordNumber]);
  }

}
