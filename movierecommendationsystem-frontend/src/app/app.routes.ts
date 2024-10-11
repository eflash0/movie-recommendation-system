import { Routes } from '@angular/router';
import { GetMoviesComponent } from './movies/get-movies/get-movies.component';
import { MovieDetailsComponent } from './movies/movie-details/movie-details.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { WatchListComponent } from './watch-list/watch-list.component';
import { FavoriteMoviesComponent } from './favorite-movies/favorite-movies.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { GetTvShowsComponent } from './tv-shows/get-tv-shows/get-tv-shows.component';
import { TvShowDetailsComponent } from './tv-shows/tv-show-details/tv-show-details.component';

export const routes: Routes = [
    {path : 'movies', component : GetMoviesComponent},
    {path : 'movies/:id', component : MovieDetailsComponent},
    {path : 'login', component : LoginComponent},
    {path : 'signup', component : RegistrationComponent},
    {path : 'watchlist', component : WatchListComponent},
    {path : 'favorite', component : FavoriteMoviesComponent},
    {path : 'searchResults', component : SearchResultsComponent},
    {path : 'tv-shows', component : GetTvShowsComponent},
    {path : 'tv-shows/:id', component : TvShowDetailsComponent}
];
