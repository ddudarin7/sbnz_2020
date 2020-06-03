import { Patient } from './patient';
import { Disease } from './disease';

export class ReportChronicDiseaseDTO {

    public p: Patient;
    public d: Disease;
    
    constructor(p: Patient,d: Disease) {
        this.p=p;
        this.d=d;
    }
  }
  