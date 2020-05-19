import { Component, OnInit } from '@angular/core';
import {LogInService} from './../../core/services/log-in.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  password: string;
  username: string;
  error: string;

  constructor( private logInService: LogInService) { }

  ngOnInit(): void {
  }

  login() {
    this.logInService.logIn(this.username,this.password).subscribe(
      data=>{
        console.log("successful");
      },
      error=>{
        this.error="Wrong password or username";
      }
    );
  }

}
