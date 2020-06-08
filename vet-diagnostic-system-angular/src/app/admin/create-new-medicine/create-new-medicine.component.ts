import { Component, OnInit } from '@angular/core';
import {Medicine} from '../../shared/model/medicine';
import {Ingredient} from '../../shared/model/ingredient';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicineService} from '../../core/services/medicine.service';
import {IngredientService} from '../../core/services/ingredient.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-create-new-medicine',
  templateUrl: './create-new-medicine.component.html',
  styleUrls: ['./create-new-medicine.component.css']
})
export class CreateNewMedicineComponent implements OnInit {

  medicine: Medicine = new Medicine(null, '', new Array<Ingredient>());

  newIngredient = '';
  newIngredients: Ingredient[];

  allIngredients: Ingredient[];
  selectedIngredients: Ingredient[] = [];

  allBreeds = [	'MIXEDBREED', 'HUSKY', 'DOBERMAN', 'PUG', 'LABRADOR', 'ROTTWEILER', 'PITBULL'];
  selectedBreed: string[];

  dropdownSettings = {};
  ingredientSettings = {};

  constructor(private medicineService: MedicineService, private activatedRoute: ActivatedRoute,
              private ingredientService: IngredientService,
              private router: Router, private toastr: ToastrService) {
  }

  ngOnInit(): void {
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
    this.ingredientSettings = {
      singleSelection: true,
      allowSearchFilter: true
    };
  }

  loadIngredients(): void {
    this.ingredientService.getIngredients().then(
      res => {
        this.allIngredients = res;
      }
    );
  }

  submitChanges(): void {
    for (const selIngr of this.selectedIngredients){
      this.medicine.ingredients.push(selIngr);
      break;
    }
    
    this.medicine.ingredients = this.selectedIngredients;

    for(const ingrName of this.newIngredient.trim().split(',')){
      this.medicine.ingredients.push(new Ingredient(null, ingrName))
    }

    this.medicineService.add(this.medicine).then(
      res => {
        this.toastr.success('Medicine successfully added.');
      }
    ).catch(
      err => {
        this.toastr.error('Medicine addition failed');
      }
    );
  }

}
