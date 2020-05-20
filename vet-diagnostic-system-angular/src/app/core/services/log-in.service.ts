import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LogInDTO} from './../../shared/model/log-in-dto';

@Injectable({
  providedIn: 'root'
})

export class LogInService {

  public currentUser:LogInDTO;
  readonly URL: string="http://localhost:8081/log-in";

  constructor(private http: HttpClient) { }

  logIn(password: string, username:string){
    this.currentUser=new LogInDTO(username,password);
    return this.http.post(this.URL,this.currentUser);
  }
}
