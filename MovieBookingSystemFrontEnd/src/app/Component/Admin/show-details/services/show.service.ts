import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShowService {

  private audiId = environment.AudiId + '/';
  constructor(private httpClient: HttpClient) { }

  addNewShow(showPayLoad: any) {
    return this.httpClient.post(environment.api_url + environment.user_admin + this.audiId + environment.shows, showPayLoad);
  }

  updateShow(showPayLoad: any) {
    return this.httpClient.put(environment.api_url + environment.user_admin + environment.shows + showPayLoad.showId, showPayLoad);
  }

  deleteShow(showPayLoad: any) {
    return this.httpClient.delete(environment.api_url + environment.user_admin + environment.shows + showPayLoad.showId);
  }

  getAllShows() {
    return this.httpClient.get(environment.api_url + environment.user_admin + environment.shows);
  }

  getShowById(showPayLoad: any) {
    return this.httpClient.get(environment.api_url + environment.user_admin +  this.audiId + environment.shows + showPayLoad.id);
  }

}
