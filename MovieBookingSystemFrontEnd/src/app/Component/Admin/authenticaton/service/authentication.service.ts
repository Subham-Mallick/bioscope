import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  authState = new BehaviorSubject<Boolean>(false);

  authenticate(username: string, password: string) : boolean {
    
    return username === 'user' && password === 'password';
  }

  setAuthState(state: Boolean) {
    this.authState.next(state);
  }

  // getAuthState() : Boolean {
  //   this.authState.subscribe((data: Boolean) =>{ return data});
  // }
}
