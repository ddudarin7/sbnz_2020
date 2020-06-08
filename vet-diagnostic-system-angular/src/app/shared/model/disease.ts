import { Symptom } from './symptom';
import { Therapy } from './therapy';

export class Disease {

  id: number;
  name: string;
  diseaseCategory: string;
  specificSymptoms: Symptom[];
  nonSpecificSymptoms: Symptom[];
  therapies: Therapy[];

  constructor(id: number, name: string, diseaseCategory: string, specificSymptoms: Symptom[],
              nonSpecificSymptoms: Symptom[], therapies: Therapy[]) {
    this.id = id;
    this.name = name;
    this.diseaseCategory = diseaseCategory;
    this.specificSymptoms = specificSymptoms;
    this.nonSpecificSymptoms = nonSpecificSymptoms;
    this.therapies = therapies;
  }
}
