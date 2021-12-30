import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingDetailsService } from '../Service/booking-details.service';
import { ShowDetailsClientService } from './service/show-details-client.service';

@Component({
  selector: 'app-show-details-client',
  templateUrl: './show-details-client.component.html',
  styleUrls: ['./show-details-client.component.scss']
})
export class ShowDetailsClientComponent implements OnInit {

  shows: any [] = []
  private AllShows : any [] = []

  constructor( private showDetailClientService: ShowDetailsClientService, private router: Router, 
    private bookingService : BookingDetailsService) { }

  ngOnInit(): void {
    this.getAllShows()
  }

  modifyShow() {

    this.shows = this.AllShows.filter((x) => x.bookingAvailable === true).map(show => {
      var date = new Date(show.showDateTime)
      var month = date.getMonth() + 1;
      var minutes = date.getMinutes();
      var hours = date.getHours();
      return {
        ...show,
        showDate : `${date.getDate() > 9 ? date.getDate() : '0'+date.getDate()}/${ month > 9 ? month : '0' + month}/${date.getFullYear()}`,
        showTime : `${hours > 9 ? hours: '0'+hours }:${minutes > 9 ? minutes : '0'+minutes}`
      }
    })
  }

  getAllShows() {
    this.showDetailClientService.getAllShows().subscribe((response: any) => {
      console.log(response)
      this.AllShows = response;
      this.modifyShow()
    })
  }
  bookShow(show : any, i : number) {
    console.log(show)
    console.log(this.AllShows[i])
    this.bookingService.updateShowDetails(show)
    this.router.navigateByUrl('/client/userdetails')

  }

}
