import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Vet} from '../../shared/model/vet';
import { Disease } from 'src/app/shared/model/disease';
import { ReportChronicDiseaseDTO } from 'src/app/shared/model/report-chronic-disease-dto';
import { ReportWeakImmunityDTO } from 'src/app/shared/model/report-weak-immunity-dto';

@Injectable({
  providedIn: 'root'
})
export class VetService {

  readonly url: string = 'http://localhost:8081/api/vets';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getVets(): Promise<Vet[]> {
    return this.http.get<Vet[]>(this.url, {headers: this.headers, withCredentials: true}).toPromise().then(
      res => res as Vet[]);
  }

  public getVetByRecordNumber(recordNumber: string): Promise<Vet> {
    return this.http.get<Vet>(`${this.url}/record-number/${recordNumber}`, {headers: this.headers,
    withCredentials: true}).toPromise().then(
      res => res as Vet);
  }

  public update(vet: Vet): Promise<Vet> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.put<Vet>(this.url, vet, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Vet);
  }

  public delete(vetId: number): Promise<void> {
    return this.http.delete<void>(`${this.url}/${vetId}`, {withCredentials: true}).toPromise<void>();
  }

  public add(vet: Vet): Promise<Vet> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.post<Vet>(this.url, vet, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Vet);
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
