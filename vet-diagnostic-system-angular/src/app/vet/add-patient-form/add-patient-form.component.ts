import { Component, OnInit } from '@angular/core';
import {Patient} from '../../shared/model/patient';
import {Medicine} from '../../shared/model/medicine';
import {Ingredient} from '../../shared/model/ingredient';
import {PatientService} from '../../core/services/patient.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicineService} from '../../core/services/medicine.service';
import {IngredientService} from '../../core/services/ingredient.service';
import {ToastrService} from 'ngx-toastr';
import {Owner} from '../../shared/model/owner';
import {Address} from '../../shared/model/address';

@Component({
  selector: 'app-add-patient-form',
  templateUrl: './add-patient-form.component.html',
  styleUrls: ['./add-patient-form.component.css']
})
export class AddPatientFormComponent implements OnInit {

  patient: Patient = new Patient(null, '', null, null, null,
    new Owner(null, '', '', '', new Address('', '', '')),
      [], [], []);

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

    if (this.selectedBreed !== undefined){
      this.patient.breed = this.selectedBreed[0];
    }
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
    this.patientService.add(this.patient).then(
      res => {
        this.toastr.success('Patient successfully added.');
        this.router.navigate(['vet/home/patient-info/' + res.recordNumber]);
      }
    ).catch(
      err => {
        this.toastr.error('Patient addition failed');
      }
    );
  }

}
