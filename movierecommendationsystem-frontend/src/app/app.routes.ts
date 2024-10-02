import { Routes } from '@angular/router';
import { GetMoviesComponent } from './movies/get-movies/get-movies.component';
import { MovieDetailsComponent } from './movies/movie-details/movie-details.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';

export const routes: Routes = [
    {path : 'movies', component : GetMoviesComponent},
    {path : 'movies/:id', component : MovieDetailsComponent},
    {path : 'login', component : LoginComponent},
    {path : 'signup', component : RegistrationComponent}
];
