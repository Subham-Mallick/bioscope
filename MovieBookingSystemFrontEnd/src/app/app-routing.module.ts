import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMovieComponent } from "./Component/Admin/add-movie/add-movie.component"
import { AdminDashboardComponent } from "./Component/Admin/admin-dashboard/admin-dashboard.component"
import { ShowDetailsComponent } from './Component/Admin/show-details/show-details.component';

const routes: Routes = [
  { path: 'admin/movies' , component: AddMovieComponent }, 
  { path: 'admin/showdetails', component: ShowDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
