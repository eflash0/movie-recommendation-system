import { Routes } from '@angular/router';
import { GetMoviesComponent } from './movies/get-movies/get-movies.component';
import { MovieDetailsComponent } from './movies/movie-details/movie-details.component';

export const routes: Routes = [
    {path : 'movies', component : GetMoviesComponent},
    {path : 'movies/:id', component : MovieDetailsComponent},
];
