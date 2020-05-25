import { Disease } from './disease';
import { Patient } from './patient';
import { Vet } from './vet';
import { Symptom } from './symptom';
import { Therapy } from './therapy';
import { PassThrough } from 'stream';

export class Diagnose {
    public id: number;
    public disease: Disease;
    public patient: Patient;
    public vet: Vet;
    public specificSymptomsMatched:Symptom[];
    public nonSpecificSymptomsMatched:Symptom[];
    public specificSymptomsMatchedNum:number;
    public nonSpecificSymptomsMatchedNum:number;
    public therapies:Therapy[];
    public date:Date;

    
    constructor(id: number, disease: Disease,patient: Patient,vet: Vet,specificSymptomsMatched:Symptom[],nonSpecificSymptomsMatched:Symptom[],
        specificSymptomsMatchedNum:number,nonSpecificSymptomsMatchedNum:number,therapies:Therapy[],date:Date){
        this.id=id;
        this.disease=disease;
        this.patient=patient;
        this.vet=vet;
        this.specificSymptomsMatched=specificSymptomsMatched;
        this.nonSpecificSymptomsMatched=nonSpecificSymptomsMatched;
        this.specificSymptomsMatchedNum=specificSymptomsMatchedNum;
        this.nonSpecificSymptomsMatchedNum=nonSpecificSymptomsMatchedNum;
        this.therapies=therapies;
        this.date=date;
    }
}