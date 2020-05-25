import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import {Disease} from '../../shared/model/disease';

@Injectable({
  providedIn: 'root'
})
export class DiseaseService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  readonly diseaseUrl: string="http://localhost:8081/diseases";

  constructor(private http: HttpClient) {
  }


  getAllDiseases():Promise<Disease[]>{
    return this.http.get(this.diseaseUrl,{headers:this.headers}).toPromise().then(res=>res as Disease[]);
  }

  findAllwithSymptoms():Promise<Disease[]>{
    return this.http.get(this.diseaseUrl+"/with-one-or-more-symptoms",{headers:this.headers,withCredentials: true}).toPromise().then(res=>res as Disease[]);
  }

  getDiseaseById(id: number): Promise<Disease> {
    return this.http.get<Disease>(`${this.diseaseUrl}${id}`, {headers: this.headers}).toPromise().then(
      res => res as Disease);
  }

  getDiseaseByName(name: string): Promise<Disease> {
    return this.http.get<Disease>(`${this.diseaseUrl}${name}`, {headers: this.headers}).toPromise().then(
      res => res as Disease);
  }

}
