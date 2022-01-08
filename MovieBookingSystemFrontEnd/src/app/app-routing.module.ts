import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMovieComponent } from "./Component/Admin/add-movie/add-movie.component"
import { AdminDashboardComponent } from "./Component/Admin/admin-dashboard/admin-dashboard.component"
import { AuthenticatonComponent } from './Component/Admin/authenticaton/authenticaton.component';
import { ShowDetailsComponent } from './Component/Admin/show-details/show-details.component';
import { AuthGuard } from './Component/Auth/AuthGuard/auth.guard';
import { CheckoutMovieTicketComponent } from './Component/Clients/checkout-movie-ticket/checkout-movie-ticket.component';
import { LandingPageComponent } from './Component/Clients/landing-page/landing-page.component';
import { TheaterSeatSelectionComponent } from './Component/Clients/theater-seat-selection/theater-seat-selection.component';
import { UserDetailsAddingComponent } from './Component/Clients/user-details-adding/user-details-adding.component';

const routes: Routes = [
  { path : '', component: LandingPageComponent, pathMatch: 'full'},
  { path : 'client/userdetails/:showId', component: UserDetailsAddingComponent, pathMatch: 'full'},
  { path : 'client/theaterSeat', component: TheaterSeatSelectionComponent, pathMatch: 'full'},
  { path: 'client/ticket' , component: CheckoutMovieTicketComponent, pathMatch: 'full' }, 
  { path: 'admin/movies' , component: AddMovieComponent, pathMatch: 'full', canActivate: [AuthGuard] }, 
  { path: 'admin/showdetails', component: ShowDetailsComponent, pathMatch: 'full', canActivate: [AuthGuard] },
  { path: 'admin/login', component: AuthenticatonComponent, pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
