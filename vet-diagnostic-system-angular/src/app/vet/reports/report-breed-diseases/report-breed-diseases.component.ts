import { Component, OnInit } from '@angular/core';
import {BreedDiseases, DiseaseCount} from '../../../shared/model/breed-diseases';
import {PatientService} from '../../../core/services/patient.service';

@Component({
  selector: 'app-report-breed-diseases',
  templateUrl: './report-breed-diseases.component.html',
  styleUrls: ['./report-breed-diseases.component.css']
})
export class ReportBreedDiseasesComponent implements OnInit {

  result: BreedDiseases;
  breed = 'MIXEDBREED';

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.patientService.getDiseasesForBreed(this.breed).then(
      res => {
        this.result = res;
      });
  }
  breedSelected(): void {
      this.patientService.getDiseasesForBreed(this.breed).then(
        res => {
      this.result = res;
  });
  }
}
