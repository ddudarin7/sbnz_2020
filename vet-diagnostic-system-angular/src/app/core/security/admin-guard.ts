import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import {UserService} from '../services/user.service';

@Injectable()
export class AdminGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.userService.getRole() === 'ADMIN') {
      return true;
    } else {
      this.router.navigate(['']);
      return false;
    }
  }
}
