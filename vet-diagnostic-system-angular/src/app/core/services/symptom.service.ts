import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {Symptom} from '../../shared/model/symptom';

@Injectable({
  providedIn: 'root'
})
export class SymptomService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  readonly url: string="http://localhost:8081/api/symptoms";

  constructor(private http: HttpClient) { }

  getAllSymptoms():Promise<Symptom[]>{
    return this.http.get(this.url,{headers:this.headers}).toPromise().then(res=>res as Symptom[]);
  }

}
