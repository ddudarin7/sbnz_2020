import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Rule} from '../../shared/model/rule';

@Injectable({
  providedIn: 'root'
})
export class RuleService {
  readonly url: string = 'http://localhost:8081/api/rules';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getRules(): Promise<Rule> {
    return this.http.get<Rule>(this.url, {headers: this.headers, withCredentials: true}).toPromise().then(
      res => res as Rule);
  }

  public add(rule: Rule): Promise<Rule> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.post<Rule>(this.url, rule, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Rule);
  }
}
