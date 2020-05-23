import { Component, OnInit } from '@angular/core';
import {LogInService} from './../../core/services/log-in.service';
import { Router, ActivatedRoute } from '@angular/router';
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

  constructor( private route: ActivatedRoute,
    private router: Router,private logInService: LogInService) { }

  ngOnInit(): void {
  }

  login() {
    this.logInService.logIn(this.username,this.password).then(
      data=>{
        localStorage.setItem('currentUser',JSON.stringify({
          username:data.username,
          role:data.role
        }));
        console.log("successful ");
        if(data.role==='VET'){
          this.router.navigate(['/vet/home']);
        }
        else if(data.role==='ADMIN'){
          this.router.navigate(['/admin/home']);
        }
        
      },
      error=>{
        this.error="Wrong password or username";
      }
    );
  }

}
