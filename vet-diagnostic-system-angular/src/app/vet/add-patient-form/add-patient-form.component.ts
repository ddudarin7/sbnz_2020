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
import {OwnerService} from '../../core/services/owner.service';

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
  ownerDropDownSettings = {};

  dropdownSettings = {};
  breedSettings = {};

  allOwners: Owner[] = [];
  selectedOwner: Owner[];


  constructor(private patientService: PatientService, private activatedRoute: ActivatedRoute,
              private medicineService: MedicineService, private ingredientService: IngredientService,
              private router: Router, private toastr: ToastrService, private ownerService: OwnerService) {
  }

  ngOnInit(): void {
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
    this.breedSettings = {
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

  loadOwners(): void{
    this.ownerService.getOwners().then(
      res => {
        res.unshift(new Owner(null, 'NEW', 'OWNER', '', new Address('',
          '', '')));
        for (const o of res){
          o.firstAndLastName = o.firstName + ' ' + o.lastName;
        }
        this.allOwners = res;
        this.selectedOwner = Array.of(this.allOwners[0]);
        this.loadOwnerFields();
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
