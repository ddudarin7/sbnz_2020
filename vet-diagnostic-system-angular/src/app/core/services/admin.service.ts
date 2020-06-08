import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Admin} from '../../shared/model/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  readonly url: string = 'http://localhost:8081/api/admins';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getAdmins(): Promise<Admin[]> {
    return this.http.get<Admin[]>(this.url, {headers: this.headers, withCredentials: true}).toPromise().then(
      res => res as Admin[]);
  }

  public update(admin: Admin): Promise<Admin> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.put<Admin>(this.url, admin, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Admin);
  }

  public delete(adminId: number): Promise<void> {
    return this.http.delete<void>(`${this.url}/${adminId}`, {withCredentials: true}).toPromise<void>();
  }

  public add(admin: Admin): Promise<Admin> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.post<Admin>(this.url, admin, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Admin);
  }
}
