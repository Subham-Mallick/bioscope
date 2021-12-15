import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private httpClient: HttpClient) { }

  addNewmovie(moviePayLoad: any) {
    return this.httpClient.post(environment.api_url + environment.movies, moviePayLoad);
  }

  updatemovie(moviePayLoad: any) {
    return this.httpClient.put(environment.api_url + environment.movies + moviePayLoad.id, moviePayLoad);
  }

  deletemovie(moviePayLoad: any) {
    return this.httpClient.delete(environment.api_url + environment.movies + moviePayLoad.id);
  }

  getAllmovies() {
    return this.httpClient.get(environment.api_url + environment.movies);
  }

  getmovieById(moviePayLoad: any) {
    return this.httpClient.get(environment.api_url + environment.movies + moviePayLoad.id);
  }

}
