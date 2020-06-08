import { Component, OnInit } from '@angular/core';
import {PatientService} from '../../core/services/patient.service';
import {Patient} from '../../shared/model/patient';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-show-all-patients',
  templateUrl: './show-all-patients.component.html',
  styleUrls: ['./show-all-patients.component.css']
})
export class ShowAllPatientsComponent implements OnInit {

  patients: Patient[];

  constructor(private patientService: PatientService, private router: Router, private toastr: ToastrService) { }

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

  patientDeleteClicked(patientId: number): void {

    if (!confirm('Are you sure you want to delete a patient?')){
      return;
    }

    this.patientService.delete(patientId).then(
      res => {
        this.toastr.success('Patient successfully deleted.');
        location.reload();
      }
    ).catch(
      err => {
        this.toastr.error('Patient not deleted.');
      }
    );
  }

}
