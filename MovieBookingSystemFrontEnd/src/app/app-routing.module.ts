import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMovieComponent } from "./Component/Admin/add-movie/add-movie.component"
import { AdminDashboardComponent } from "./Component/Admin/admin-dashboard/admin-dashboard.component"
import { ShowDetailsComponent } from './Component/Admin/show-details/show-details.component';
import { LandingPageComponent } from './Component/Clients/landing-page/landing-page.component';
import { TheaterSeatSelectionComponent } from './Component/Clients/theater-seat-selection/theater-seat-selection.component';
import { UserDetailsAddingComponent } from './Component/Clients/user-details-adding/user-details-adding.component';

const routes: Routes = [
  { path : '', component: LandingPageComponent, pathMatch: 'full'},
  { path : 'client/userdetails/:showId', component: UserDetailsAddingComponent, pathMatch: 'full'},
  { path : 'client/theaterSeat', component: TheaterSeatSelectionComponent, pathMatch: 'full'},
  { path: 'admin/movies' , component: AddMovieComponent, pathMatch: 'full' }, 
  { path: 'admin/showdetails', component: ShowDetailsComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
