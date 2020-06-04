import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Owner} from '../../shared/model/owner';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  readonly url: string = 'http://localhost:8081/api/owners';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getOwners(): Promise<Owner[]> {
    return this.http.get<Owner[]>(this.url, {headers: this.headers, withCredentials: true}).toPromise().then(
      res => res as Owner[]);
  }
}
