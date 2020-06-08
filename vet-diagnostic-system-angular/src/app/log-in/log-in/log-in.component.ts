import { Component, OnInit } from '@angular/core';
import {LogInService} from './../../core/services/log-in.service';
import { Router, ActivatedRoute } from '@angular/router';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
  providers: [MessageService]
})
export class LogInComponent implements OnInit {

  password: string;
  username: string;

  constructor( private route: ActivatedRoute,
    private router: Router,private logInService: LogInService,
    private messageService: MessageService) { }

  ngOnInit(): void {
  }

  login() {
    this.logInService.logIn(this.password, this.username).then(
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
        this.messageService.add({severity:'error', summary:'Error!', detail:'Wrong username or password.',life:5000});
      }
    );
  }

}
