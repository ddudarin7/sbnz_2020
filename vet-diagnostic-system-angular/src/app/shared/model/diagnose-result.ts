import { Disease } from './disease';
import { Patient } from './patient';
import { Vet } from './vet';
import { Symptom } from './symptom';
import { Therapy } from './therapy';

export class DiagnoseResult {
    public id: number;
    public diseases: Disease[];
    public patient: Patient;
    public vet: Vet;
    public specificSymptomsMatched: Symptom[];
    public nonSpecificSymptomsMatched: Symptom[];
    public specificSymptomsMatchedNum: number;
    public nonSpecificSymptomsMatchedNum: number;
    public therapies: Therapy[];
    public date: Date;

    constructor(id: number, diseases: Disease[], patient: Patient, vet: Vet, specificSymptomsMatched: Symptom[],
                nonSpecificSymptomsMatched: Symptom[], specificSymptomsMatchedNum: number,
                nonSpecificSymptomsMatchedNum: number, therapies: Therapy[], date: Date){
        this.id = id;
        this.diseases = diseases;
        this.patient = patient;
        this.vet = vet;
        this.specificSymptomsMatched = specificSymptomsMatched;
        this.nonSpecificSymptomsMatched = nonSpecificSymptomsMatched;
        this.specificSymptomsMatchedNum = specificSymptomsMatchedNum;
        this.nonSpecificSymptomsMatchedNum = nonSpecificSymptomsMatchedNum;
        this.therapies = therapies;
        this.date = date;
    }
}
