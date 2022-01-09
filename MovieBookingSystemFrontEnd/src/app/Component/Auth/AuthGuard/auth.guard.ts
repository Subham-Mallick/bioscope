import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../../Admin/authenticaton/service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthenticationService, private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      // if(localStorage.getItem('jwttoken') == null || localStorage.getItem('jwttoken') == undefined) {
      //   this.router.navigateByUrl("/")  
      //   return false;
      // }
      // else if(localStorage.getItem('timestamp') === null || localStorage.getItem('timestamp')=== undefined || localStorage.getItem('timestamp') === '') { 
      //   this.router.navigateByUrl("/")
      //   return false; 
      // }
      // else{
      //   var timestamp = localStorage.getItem('timestamp') || '';
      //   var d1 = new Date(timestamp);
      //   var d2 = new Date();
      //   var isGreater = +d2 < d1.setDate(d1.getDate() + 1);
      //   if(isGreater == false) {
      //     localStorage.removeItem('jwttoken')
      //     localStorage.removeItem('timestamp')
      //     this.router.navigateByUrl("/")
      //   }
      //   return isGreater;
      
      // }
      var timestamp = localStorage.getItem('timestamp') || '';
      var d1 = new Date(timestamp);
      var d2 = new Date();
      var isGreater = +d2 < d1.setDate(d1.getDate() + 1);
      if(isGreater == false || localStorage.getItem("authResult") === "false" || localStorage.getItem("authResult") === null) {
        localStorage.removeItem('authResult')
        localStorage.removeItem('timestamp')
        this.router.navigateByUrl("/")
      }
      
      return localStorage.getItem("authResult") === "true";
  }
  
}
