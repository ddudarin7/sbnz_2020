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

}
