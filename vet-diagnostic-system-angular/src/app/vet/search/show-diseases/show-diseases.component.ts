import { Component, OnInit } from '@angular/core';
import {DiseaseService} from '../../../core/services/disease.service';
import {Disease} from '../../../shared/model/disease';


@Component({
  selector: 'app-show-diseases',
  templateUrl: './show-diseases.component.html',
  styleUrls: ['./show-diseases.component.css']
})
export class ShowDiseasesComponent implements OnInit {

  diseases:Disease[];

  constructor(private diseaseService:DiseaseService) { 
  }

  ngOnInit(): void {
    this.diseases=this.diseaseService.sharedData;
  }

}
