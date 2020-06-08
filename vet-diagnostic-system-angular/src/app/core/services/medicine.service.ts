import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Medicine} from '../../shared/model/medicine';

@Injectable()
export class MedicineService {

  readonly url: string = 'http://localhost:8081/api/medicines';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getMedicines(): Promise<Medicine[]> {
    return this.http.get<Medicine[]>(this.url, {headers: this.headers, withCredentials: true}).toPromise().then(
      res => res as Medicine[]);
  }

  public add(medicine: Medicine): Promise<Medicine> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.post<Medicine>(this.url, medicine, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Medicine);
  }

  public delete(medicinetId: number): Promise<void> {
    return this.http.delete<void>(`${this.url}/${medicinetId}`, {withCredentials: true}).toPromise<void>();
  }

}
