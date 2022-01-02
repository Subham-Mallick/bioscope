import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BlockedSeatsService {

  constructor(private httpClient: HttpClient) { }

  getShowDetails(showId: String) {
    return this.httpClient.get(environment.api_url + environment.user_admin + environment.shows + showId);
  }
}
