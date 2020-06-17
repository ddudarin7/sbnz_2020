import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Patient} from '../../shared/model/patient';
import { Disease } from 'src/app/shared/model/disease';
import { ReportChronicDiseaseDTO } from 'src/app/shared/model/report-chronic-disease-dto';
import { ReportWeakImmunityDTO } from 'src/app/shared/model/report-weak-immunity-dto';

@Injectable()
export class PatientService{

  readonly url: string = 'http://localhost:8081/api/patients';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getPatients(): Promise<Patient[]> {
    return this.http.get<Patient[]>(this.url, {headers: this.headers, withCredentials: true}).toPromise().then(
      res => res as Patient[]);
  }

  public getPatientByRecordNumber(recordNumber: string): Promise<Patient> {
    return this.http.get<Patient>(`${this.url}/record-number/${recordNumber}`, {headers: this.headers,
    withCredentials: true}).toPromise().then(
      res => res as Patient);
  }

  public update(patient: Patient): Promise<Patient> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.put<Patient>(this.url, patient, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Patient);
  }

  public delete(patientId: number): Promise<void> {
    return this.http.delete<void>(`${this.url}/${patientId}`, {withCredentials: true}).toPromise<void>();
  }

  public add(patient: Patient): Promise<Patient> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.post<Patient>(this.url, patient, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Patient);
  }

  public chronicDiseases():Promise<ReportChronicDiseaseDTO[]>{
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.get<ReportChronicDiseaseDTO[]>(this.url+"/report/chronic-diseases",{headers: h, withCredentials: true}).toPromise().then(res=>res as ReportChronicDiseaseDTO[]);
  }

  public weakImmunity():Promise<ReportWeakImmunityDTO[]>{
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.get<ReportWeakImmunityDTO[]>(this.url+"/report/weak-immunity",{headers: h, withCredentials: true}).toPromise().then(res=>res as ReportWeakImmunityDTO[]);
  }

}
