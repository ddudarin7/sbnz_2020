import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Vaccine} from '../../shared/model/vaccine';

@Injectable({
  providedIn: 'root'
})
export class VaccineService {

  readonly url: string = 'http://localhost:8081/api/vaccines';
  constructor(private http: HttpClient) { }

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getVaccines(): Promise<Vaccine[]> {
    return this.http.get<Vaccine[]>(this.url, {headers: this.headers, withCredentials: true}).toPromise().then(
      res => res as Vaccine[]);
  }
}
