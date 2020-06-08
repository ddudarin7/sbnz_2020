import {Component, OnInit} from '@angular/core';
import {PatientService} from '../../core/services/patient.service';
import {Patient} from '../../shared/model/patient';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicineService} from '../../core/services/medicine.service';
import {Medicine} from '../../shared/model/medicine';
import {IngredientService} from '../../core/services/ingredient.service';
import {Ingredient} from '../../shared/model/ingredient';
import {ToastrService} from 'ngx-toastr';
import {DiagnoseService} from '../../core/services/diagnose.service';
import {Diagnose} from '../../shared/model/diagnose';
import {VaccinationService} from '../../core/services/vaccination.service';
import {Vaccination} from '../../shared/model/vaccination';
import {Owner} from '../../shared/model/owner';
import {OwnerService} from '../../core/services/owner.service';
import {Address} from '../../shared/model/address';

@Component({
  selector: 'app-patient-info',
  templateUrl: './patient-info.component.html',
  styleUrls: ['./patient-info.component.css']
})

export class PatientInfoComponent implements OnInit {

  patient: Patient;

  allBreeds = [	'MIXEDBREED', 'HUSKY', 'DOBERMAN', 'PUG', 'LABRADOR', 'ROTTWEILER', 'PITBULL'];
  selectedBreed: string[];

  allMedicines: Medicine[];
  selectedMedicines: Medicine[] = [];

  allIngredients: Ingredient[];
  selectedIngredients: Ingredient[] = [];

  dropdownSettings = {};
  singleDropSettings = {};
  ownerDropDownSettings = {};

  patientDiagnoses: Diagnose[];
  noDiagnoses = true;
  noVaccinations = true;

  allOwners: Owner[] = [];
  selectedOwner: Owner[];

  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute,
              private medicineService: MedicineService, private ingredientService: IngredientService,
              private router: Router, private toastr: ToastrService, private  diagnoseService: DiagnoseService,
              private vaccinationService: VaccinationService, private ownerService: OwnerService) {
  }

  ngOnInit(): void {
    this.loadPatient(this.activatedRoute.snapshot.params.recordNumber);
    this.loadMedicines();
    this.loadIngredients();
    this.loadOwners();
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'Select All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
    this.singleDropSettings = {
      singleSelection: true,
      allowSearchFilter: true
    };
    this.ownerDropDownSettings = {
      singleSelection: true,
      allowSearchFilter: true,
      idField: 'id',
      textField: 'firstAndLastName',
      selectAllText: 'Select All',
      unSelectAllText: 'Select All'
    };
  }

  loadPatient(recordNumber: string): void {
    this.patientService.getPatientByRecordNumber(recordNumber).then(
      res => {
        this.patient = res;
        for (const med of this.patient.medicineAllergies){
          this.selectedMedicines.push(med);
        }
        for (const ing of this.patient.ingredientAllergies){
          this.selectedIngredients.push(ing);
        }
        this.selectedBreed = Array.of(this.patient.breed);
        if (this.patient.vaccinations.length > 0){
          this.noVaccinations = false;
        }
        this.patient.owner.firstAndLastName = this.patient.owner.firstName + ' ' + this.patient.owner.lastName;
        this.selectedOwner = Array.of(this.patient.owner);
        this.loadDiagnoses();
      }
    );
  }

  loadMedicines(): void {
    this.medicineService.getMedicines().then(
      res => {
        this.allMedicines = res;
      }
    );
  }

  loadIngredients(): void {
    this.ingredientService.getIngredients().then(
      res => {
        this.allIngredients = res;
      }
    );
  }

  loadDiagnoses(): void {
    this.diagnoseService.getByPatientId(this.patient.id).then(
      res => {
        this.patientDiagnoses = res;
        if (this.patientDiagnoses.length > 0){
          this.noDiagnoses = false;
        }
      }
    );
  }

  submitChanges(): void {

    if (!confirm('Are you sure you want to save changes on patient?')){
      return;
    }

    this.patient.breed = this.selectedBreed[0];
    this.patient.medicineAllergies = [];
    for (const selMed of this.selectedMedicines){
      for (const med of this.allMedicines){
        if (selMed.id === med.id){
          this.patient.medicineAllergies.push(med);
          break;
        }
      }
    }
    this.patient.ingredientAllergies = this.selectedIngredients;

    if (this.selectedOwner.length === 0){
      this.toastr.error('Owner not selected!');
      return;
    }

    for (const o of this.allOwners){
      if (o.id === this.selectedOwner[0].id){
        this.patient.owner = o;
        break;
      }
    }

    this.patientService.update(this.patient).then(
      res => {
        this.toastr.success('Patient successfully updated.');
        location.reload();
      }
    ).catch(
      err => {
        this.toastr.error('Patient update failed');
      }
    );
  }

  delete(): void {

    if (!confirm('Are you sure you want to delete a patient?')){
      return;
    }

    this.patientService.delete(this.patient.id).then(
      res => {
        this.toastr.success('Patient successfully deleted.');
        this.router.navigate(['vet/home']);
      }
    ).catch(
      err => {
        this.toastr.error('Patient not deleted.');
      }
    );
  }

  diagnose(): void {
    this.diagnoseService.setPatientInFocus(this.patient.recordNumber);
    this.router.navigate(['vet/home/diagnose']);
  }

  navigateToDiagnose(diagnoseId: number){
    this.router.navigate(['vet/home/diagnoses/' + diagnoseId]);
  }

  addVaccination(): void {
    this.vaccinationService.setPatientInFocus(this.patient.recordNumber);
    this.router.navigate(['vet/home/add-vaccination']);
  }

  removeVaccination(vaccinationId): void {
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
  }

  loadOwners(): void{
    this.ownerService.getOwners().then(
      res => {
        res.unshift(new Owner(null, 'NEW', 'OWNER', null, new Address(null,
          null, null)));
        for (const o of res){
          o.firstAndLastName = o.firstName + ' ' + o.lastName;
        }
        this.allOwners = res;
      }
    );
  }

  loadOwnerFields(): void{
    if (this.selectedOwner.length === 0){
      return;
    }

    for (const o of this.allOwners){
      if (o.id === this.selectedOwner[0].id){
        this.patient.owner = o;
        break;
      }
    }
  }

}
