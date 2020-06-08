import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Vaccination} from '../../shared/model/vaccination';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VaccinationService {

  private source = new BehaviorSubject('');
  patientInFocus = this.source.asObservable();

  readonly url: string = 'http://localhost:8081/api/vaccinations';
  constructor(private http: HttpClient) { }

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public setPatientInFocus(patientRecordNumber: string){
    this.source.next(patientRecordNumber);
  }

  public getVaccinations(): Promise<Vaccination[]> {
    return this.http.get<Vaccination[]>(this.url, {headers: this.headers}).toPromise().then(
      res => res as Vaccination[]);
  }

  public add(vaccination: Vaccination): Promise<Vaccination> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'});
    return this.http.post<Vaccination>(this.url, vaccination, {headers: h, withCredentials: true}).toPromise()
      .then(res => res as Vaccination);
  }

  public delete(vaccinationId: number): Promise<void> {
    return this.http.delete<void>(`${this.url}/${vaccinationId}`, {withCredentials: true}).toPromise<void>();
  }
}
