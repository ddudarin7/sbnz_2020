import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {LogInService} from './../../core/services/log-in.service';
import {MessageService} from 'primeng/api';
declare var SockJS;
declare var Stomp;


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [MessageService]
})
export class HomePageComponent implements OnInit {

  notificationMessage:string;
  private serverUrl = 'http://localhost:8081/api/socket';
  private stompClient;

  constructor(private route: ActivatedRoute,
    private router: Router,private logInService: LogInService,private messageService: MessageService) { 
      this.initializeWebSocketConnection();
    }

  ngOnInit(): void {
  }

  /*constructor(private webSocketService : WebSocketService,private route: ActivatedRoute,
    private router: Router,private logInService: LogInService){
    let stompClient = this.webSocketService.connect();
    stompClient.connect({}, frame =>{
      stompClient.subscribe('/notifications', message =>{
        this.monitoringMessage = message.body;
        //$("#monitoring").modal("show");
      });
    });
  }*/

  initializeWebSocketConnection() {
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, (frame) => {
      this.stompClient.subscribe('/notifications', (message) => {
        if (message.body) {
          console.log(message.body);
          this.messageService.add({severity:'error', summary:'Error!', detail:message.body,life:5000});
        }
      });
    });
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
