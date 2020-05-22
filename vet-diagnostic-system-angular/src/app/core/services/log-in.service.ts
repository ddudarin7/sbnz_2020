import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {LogInDTO} from './../../shared/model/log-in-dto';
import {CurrentUserDTO} from './../../shared/model/current-user-dto';
import{UserService} from './user.service';


@Injectable({
  providedIn: 'root'
})

export class LogInService {


  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  readonly logInURL: string="http://localhost:8081/log-in";
  readonly logOutURL: string="http://localhost:8081/log-out";
  private username:string;


  constructor(private http: HttpClient,private userService: UserService) { }

  logIn(password: string, username:string):Promise<CurrentUserDTO>{
    return this.http.post(this.logInURL,new LogInDTO(username,password),{headers:this.headers,withCredentials: true}).toPromise().then(res=>res as CurrentUserDTO);
  }

  logOut(): Promise<any>{
    this.username=this.userService.getUsername();
    return this.http.post(this.logOutURL,this.username,{headers:this.headers,withCredentials: true}).toPromise();
  }
}
