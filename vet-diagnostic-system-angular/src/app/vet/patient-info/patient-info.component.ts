import {Component, OnInit} from '@angular/core';
import {PatientService} from '../../core/services/patient.service';
import {Patient} from '../../shared/model/patient';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicineService} from '../../core/services/medicine.service';
import {Medicine} from '../../shared/model/medicine';
import {IngredientService} from '../../core/services/ingredient.service';
import {Ingredient} from '../../shared/model/ingredient';
import {ToastrService} from 'ngx-toastr';

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
  breedSettings = {};

  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute,
              private medicineService: MedicineService, private ingredientService: IngredientService,
              private router: Router, private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.loadPatient(this.activatedRoute.snapshot.params.recordNumber);
    this.loadMedicines();
    this.loadIngredients();
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'Select All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
    this.breedSettings = {
      singleSelection: true,
      allowSearchFilter: true
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

}
