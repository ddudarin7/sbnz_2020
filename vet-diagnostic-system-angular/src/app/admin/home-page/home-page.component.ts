import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {LogInService} from './../../core/services/log-in.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private router: Router,private logInService: LogInService) { }

  ngOnInit(): void {
  }

  logout(){
    this.logInService.logOut().then(
      data=>{
        localStorage.removeItem('currentUser');
        this.router.navigateByUrl('');
      },
      error=>{
        console.log("Log out error");
      }
    );
  }
}
