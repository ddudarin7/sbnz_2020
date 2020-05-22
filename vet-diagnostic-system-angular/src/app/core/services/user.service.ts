import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  isSignedIn(): boolean {
    return this.getUsername() !== '';
  }

  getUsername(): string {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const username = currentUser && currentUser.username;
    return username ? username : '';
  }
  
  getRole(): string {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(localStorage.getItem('currentUser'));
    const role = currentUser && currentUser.role;
    return role ? role : '';
  }

}
