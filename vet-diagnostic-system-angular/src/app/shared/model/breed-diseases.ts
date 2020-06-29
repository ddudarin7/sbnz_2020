import {Disease} from './disease';

export class BreedDiseases {
  public breed: string;
  public totalDiagnoses: number;
  public data: DiseaseCount[];


  constructor(breed: string, totalDiagnoses: number, data: DiseaseCount[]) {
    this.breed = breed;
    this.totalDiagnoses = totalDiagnoses;
    this.data = data;
  }
}

export class DiseaseCount{
  public disease: Disease
  public count: number;

  constructor(disease: Disease, count: number) {
    this.disease = disease;
    this.count = count;
  }
}
