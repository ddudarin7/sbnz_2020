import { Component, OnInit } from '@angular/core';

import {Ingredient} from '../../shared/model/ingredient';
import {Disease} from '../../shared/model/disease';
import {ActivatedRoute, Router} from '@angular/router';
import {IngredientService} from '../../core/services/ingredient.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-create-new-disease',
  templateUrl: './create-new-disease.component.html',
  styleUrls: ['./create-new-disease.component.css']
})
export class CreateNewDiseaseComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
