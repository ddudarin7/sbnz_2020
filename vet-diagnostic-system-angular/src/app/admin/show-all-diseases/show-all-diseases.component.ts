import { Component, OnInit } from '@angular/core';
import {DiseaseService} from '../../core/services/disease.service';
import {Disease} from '../../shared/model/disease';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-show-all-diseases',
  templateUrl: './show-all-diseases.component.html',
  styleUrls: ['./show-all-diseases.component.css']
})
export class ShowAllDiseasesComponent implements OnInit {

  
  diseases: Disease[];

  constructor(private diseaseService: DiseaseService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadDiseases();
  }

  loadDiseases(): void {
    this.diseaseService.getAllDiseases().then(
      res => {
        this.diseases = res;
      }
    );
  }

  newDiseaseClicked(): void {
    this.router.navigate(['admin/home/create-new-disease']);
  }

  diseaseDeleteClicked(diseaseId: number): void {

    if (!confirm('Are you sure you want to delete a disease?')){
      return;
    }

    this.diseaseService.delete(diseaseId).then(
      res => {
        this.toastr.success('Disease successfully deleted.');
        location.reload();
      }
    ).catch(
      err => {
        this.toastr.error('Disease not deleted.');
      }
    );
  }

}
