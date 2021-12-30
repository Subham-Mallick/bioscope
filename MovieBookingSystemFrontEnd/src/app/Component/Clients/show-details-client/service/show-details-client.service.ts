import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShowDetailsClientService {

  constructor(private httpClient: HttpClient) { }
  
  getAllShows() {
    return this.httpClient.get(environment.api_url + environment.user_client + environment.shows);
  }
}
