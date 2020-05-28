import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Vaccination} from '../../shared/model/vaccination';

@Injectable({
  providedIn: 'root'
})
export class VaccinationService {

  readonly url: string = 'http://localhost:8081/api/vaccinations';
  constructor(private http: HttpClient) { }

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getVaccinations(): Promise<Vaccination[]> {
    return this.http.get<Vaccination[]>(this.url, {headers: this.headers}).toPromise().then(
      res => res as Vaccination[]);
  }
}
