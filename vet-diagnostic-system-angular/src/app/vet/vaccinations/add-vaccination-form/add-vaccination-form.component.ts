import { Component, OnInit } from '@angular/core';
import {Vaccination} from '../../../shared/model/vaccination';
import {VaccineService} from '../../../core/services/vaccine.service';
import {Vaccine} from '../../../shared/model/vaccine';
import {PatientService} from '../../../core/services/patient.service';
import {ToastrService} from 'ngx-toastr';
import {VaccinationService} from '../../../core/services/vaccination.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-vaccination-form',
  templateUrl: './add-vaccination-form.component.html',
  styleUrls: ['./add-vaccination-form.component.css']
})
export class AddVaccinationFormComponent implements OnInit {

  vaccination = new Vaccination(null, null, new Date());
  patient;
  patientLoaded = false;
  patientRecordNumber = '';
  allVaccines: Vaccine[] = [];
  selectedVaccine: Vaccine[] = [];
  dropDownSettings = {};

  constructor(private vaccineService: VaccineService, private patientService: PatientService,
              private toastr: ToastrService, private vaccinationService: VaccinationService,
              private router: Router) { }

  ngOnInit(): void {
    this.vaccinationService.patientInFocus.subscribe(rec => this.patientRecordNumber = rec);
    this.dropDownSettings = {
      singleSelection: true,
      allowSearchFilter: true,
      idField: 'description',
      textField: 'name'
    };
    this.loadVaccines();
  }

  loadVaccines(): void{
    this.vaccineService.getVaccines().then(
      res => {
        this.allVaccines = res;
      }
    );
  }

  loadPatient(): void{
    this.patientService.getPatientByRecordNumber(this.patientRecordNumber).then(
      res => {
        this.patient = res;
        this.patientLoaded = true;
      }
    ).catch(
      err => {
        this.toastr.error('Patient with input record number does not exist.');
      }
    );
  }

  confirmVaccination(): void{

    if (this.selectedVaccine.length === 0){
      this.toastr.error('Vaccine not selected!');
      return;
    }

    for (const v of this.allVaccines){
      if (v.name === this.selectedVaccine[0].name){
        this.vaccination.vaccine = v;
        break;
      }
    }

    this.vaccinationService.add(this.vaccination).then(
      vaccinationAddResponse => {
        this.patientService.getPatientByRecordNumber(this.patientRecordNumber).then(
          patientGetResponse => {
            patientGetResponse.vaccinations.push(vaccinationAddResponse);
            this.patientService.update(patientGetResponse).then(
              patientUpdateResponse => {
                this.toastr.success('Vaccination successfully added.');
                this.router.navigate(['vet/home/patient-info/' + patientUpdateResponse.recordNumber]);
              }
            ).catch(
              patientUpdateError => {
                this.toastr.error('Adding of vaccination failed.');
                this.vaccinationService.delete(vaccinationAddResponse.id).then(
                  deleteResponse2 => {
                    return;
                  }
                );
              }
            );
          }
        ).catch(
          patientGetError => {
            this.toastr.error('Patient with input record number does not exist.');
            this.vaccinationService.delete(vaccinationAddResponse.id).then(
              deleteResponse => {
                return;
              }
            );
          }
        );
      }
    ).catch(
      vaccinationAddError => {
        this.toastr.error('Adding of vaccination failed.');
        return;
      }
    );
  }

  delete(vaccinationId: number): void{
    if (!confirm('Are you sure you want to delete vaccination?')){
      return;
    }

    const pv: Vaccination[] = [];


    for (const v of this.patient.vaccinations){
      if (v.id !== vaccinationId){
        pv.push(v);
      }
    }
    this.patient.vaccinations = pv;

    this.patientService.update(this.patient).then(
      res => {
        this.patient = res;
        this.toastr.success('Vaccination successfully removed.');
      }
    );
  }

}
