import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Symptom} from '../../shared/model/symptom';
import {Diagnose} from '../../shared/model/diagnose';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiagnoseService {

  readonly url: string = 'http://localhost:8081/api/diagnoses';

  private source = new BehaviorSubject('');
  patientInFocus = this.source.asObservable();

  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json', 'Content-Type': 'application/json'});

  public setPatientInFocus(patientRecordNumber: string){
    this.source.next(patientRecordNumber);
  }

  public diagnose(patientRecordNumber: string, symptoms: Symptom[]): Promise<Diagnose> {
    return this.http.post<Diagnose>('http://localhost:8081/api/diagnose/' + patientRecordNumber, symptoms,
      {headers: this.headers, withCredentials: true}).toPromise().then(res => res as Diagnose);
  }

  public confirmDiagnose(diagnose: Diagnose): Promise<Diagnose> {
    return this.http.post<Diagnose>(this.url, diagnose, {headers: this.headers, withCredentials: true})
      .toPromise().then(res => res as Diagnose);
  }

  public getDiagnoseById(diagnoseId: number): Promise<Diagnose> {
    const h = new HttpHeaders({Accept: 'application/json'});
    return this.http.get<Diagnose>(`${this.url}/${diagnoseId}`, {headers: h, withCredentials: true})
      .toPromise().then(res => res as Diagnose);
  }

  public delete(diagnoseId: number): Promise<void> {
    return this.http.delete<void>(`${this.url}/${diagnoseId}`, {withCredentials: true}).toPromise<void>();
  }

  public getByPatientId(patientId: number): Promise<Diagnose[]> {
    const h = new HttpHeaders({Accept: 'application/json'});
    return this.http.get<Diagnose[]>(`${this.url}/patient/${patientId}`, {headers: h, withCredentials: true})
      .toPromise().then(res => res as Diagnose[]);
  }

}
