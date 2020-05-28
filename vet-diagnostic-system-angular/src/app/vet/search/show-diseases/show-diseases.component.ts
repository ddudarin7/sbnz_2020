import { Component, OnInit } from '@angular/core';
import {DiseaseService} from '../../../core/services/disease.service';
import {Disease} from '../../../shared/model/disease';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-show-diseases',
  templateUrl: './show-diseases.component.html',
  styleUrls: ['./show-diseases.component.css']
})
export class ShowDiseasesComponent implements OnInit {

  diseases:Disease[];

  constructor(private diseaseService:DiseaseService,private route: ActivatedRoute,
    private router: Router) { 
  }

  ngOnInit(): void {
    this.diseases=this.diseaseService.sharedData;
  }

  diseaseInfo(name:string){
    this.router.navigate(['/vet/home/search/show/disease/'+name]);
  }

}
