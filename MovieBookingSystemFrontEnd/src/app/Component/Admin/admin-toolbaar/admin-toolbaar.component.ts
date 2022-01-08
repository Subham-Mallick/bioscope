import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-toolbaar',
  templateUrl: './admin-toolbaar.component.html',
  styleUrls: ['./admin-toolbaar.component.scss']
})
export class AdminToolbaarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  signOut() {
    localStorage.removeItem('jwttoken')
    localStorage.removeItem('timestamp')
    this.router.navigateByUrl("/");
  }
}
