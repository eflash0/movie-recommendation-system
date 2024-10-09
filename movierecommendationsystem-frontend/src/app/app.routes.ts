import { Routes } from '@angular/router';
import { GetMoviesComponent } from './movies/get-movies/get-movies.component';
import { MovieDetailsComponent } from './movies/movie-details/movie-details.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { WatchListComponent } from './movies/watch-list/watch-list.component';
import { FavoriteMoviesComponent } from './movies/favorite-movies/favorite-movies.component';

export const routes: Routes = [
    {path : 'movies', component : GetMoviesComponent},
    {path : 'movies/:id', component : MovieDetailsComponent},
    {path : 'login', component : LoginComponent},
    {path : 'signup', component : RegistrationComponent},
    {path : 'watchlist', component : WatchListComponent},
    {path : 'favorite', component : FavoriteMoviesComponent}
];
