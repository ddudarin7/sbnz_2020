import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Patient} from '../../shared/model/patient';

@Injectable()
export class PatientService{

  readonly url: string = 'http://localhost:8081/api/patients';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getPatients(): Promise<Patient[]> {
    return this.http.get<Patient[]>(this.url, {headers: this.headers}).toPromise().then(
      res => res as Patient[]);
  }

  public getPatientByRecordNumber(recordNumber: string): Promise<Patient> {
    return this.http.get<Patient>(`${this.url}/record-number/${recordNumber}`, {headers: this.headers}).toPromise().then(
      res => res as Patient);
  }

  public update(patient: Patient): Promise<Patient> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.put<Patient>(this.url, patient, {headers: h}).toPromise().then(
      res => res as Patient);
  }

}
