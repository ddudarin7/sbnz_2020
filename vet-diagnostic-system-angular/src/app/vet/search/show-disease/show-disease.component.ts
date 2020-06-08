import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DiseaseService} from '../../../core/services/disease.service';
import {Disease} from '../../../shared/model/disease';

@Component({
  selector: 'app-show-disease',
  templateUrl: './show-disease.component.html',
  styleUrls: ['./show-disease.component.css']
})
export class ShowDiseaseComponent implements OnInit {

  disease:Disease;

  constructor(private diseaseService:DiseaseService,private activatedRoute: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.diseaseService.getDiseaseByName(this.activatedRoute.snapshot.params.name).then(
      res=>{
        this.disease=res;
      }
    );
    console.log(this.activatedRoute.snapshot.params.name);
  }

}
