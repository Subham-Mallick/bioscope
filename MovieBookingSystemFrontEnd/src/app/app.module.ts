import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//Clients
import { TheaterSeatSelectionComponent } from './Component/Clients/theater-seat-selection/theater-seat-selection.component';
import { UserDetailsAddingComponent } from './Component/Clients/user-details-adding/user-details-adding.component';
import { LandingPageComponent } from './Component/Clients/landing-page/landing-page.component';
import { CheckoutMovieTicketComponent } from './Component/Clients/checkout-movie-ticket/checkout-movie-ticket.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatCarouselModule } from '@ngmodule/material-carousel';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input'
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatSnackBarModule} from '@angular/material/snack-bar';

//Admin

import { AdminDashboardComponent } from './Component/Admin/admin-dashboard/admin-dashboard.component';
import { MovieGuestDetailsComponent } from './Component/Admin/movie-guest-details/movie-guest-details.component';
import { AddMovieComponent } from './Component/Admin/add-movie/add-movie.component';
import { AuthenticatonComponent } from './Component/Admin/authenticaton/authenticaton.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminUsersComponent } from './Component/Admin/admin-users/admin-users.component';
import { MovieEditAddComponent } from './Component/Admin/add-movie/dialog/movie-edit-add/movie-edit-add.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ShowDetailsComponent } from './Component/Admin/show-details/show-details.component';
import { AddEditShowComponent } from './Component/Admin/show-details/Dialog/add-edit-show/add-edit-show.component';
import { ShowGuestListComponent } from './Component/Admin/show-guest-list/show-guest-list.component';
import { MatTableModule } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    TheaterSeatSelectionComponent,
    UserDetailsAddingComponent,
    LandingPageComponent,
    CheckoutMovieTicketComponent,
    AdminDashboardComponent,
    MovieGuestDetailsComponent,
    AddMovieComponent,
    AuthenticatonComponent,
    AdminUsersComponent,
    MovieEditAddComponent,
    ShowDetailsComponent,
    AddEditShowComponent,
    ShowGuestListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatCarouselModule.forRoot(),
    MatToolbarModule,
    MatIconModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatDividerModule,
    MatSidenavModule,
    MatCheckboxModule,
    FormsModule, ReactiveFormsModule,
    MatProgressBarModule,
    MatDialogModule, 
    MatTableModule,
    MatSnackBarModule
  ],
  exports: [
    MatDatepickerModule,
    MatFormFieldModule
  ],
  providers: [MatFormFieldModule,MatDatepickerModule],
  bootstrap: [AppComponent]
})

export class AppModule { }
