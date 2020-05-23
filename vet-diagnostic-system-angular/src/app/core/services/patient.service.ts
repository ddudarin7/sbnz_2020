import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Patient} from '../../shared/model/patient';
import {Observable} from "rxjs";

@Injectable()
export class PatientService{

  readonly url: string = 'http://localhost:8081/patients';
  constructor(private http: HttpClient) {}

  public getPatients(): Promise<Patient[]> {
    return this.http.get<Patient[]>(this.url).toPromise();
  }
}
