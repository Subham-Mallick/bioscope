import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-authenticaton',
  templateUrl: './authenticaton.component.html',
  styleUrls: ['./authenticaton.component.scss']
})
export class AuthenticatonComponent implements OnInit {
  hide = true;
  username = new FormControl('')
  password = new FormControl('')

  constructor(private router: Router, private authService: AuthenticationService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  validateLogin() {
    if(this.username.pristine || this.password.pristine) return;
    
    var authResult = this.authService.authenticate(this.username.value, this.password.value)
    if(authResult === false) this.openSnackBar()
    else {
      localStorage.setItem("authResult", String(authResult));
      localStorage.setItem("timestamp", String(new Date()));
      this.router.navigateByUrl("admin/movies");
    }
    // .subscribe((res:any) => {
    //   localStorage.setItem("jwttoken", res.jwt);
    //   localStorage.setItem("timestamp", String(new Date()));
    //   this.authService.setAuthState(true);
    //   this.router.navigateByUrl("admin/movies");
    // }, (error) => {
    //   this.authService.setAuthState(false);
    //   this.openSnackBar()
    // })
    
  }

  openSnackBar() {
    this._snackBar.open("Unsuccessfull Login Attempt" , "Failed", {
      duration: 3000,
    });
  }

}
