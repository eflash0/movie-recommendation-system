import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../service/movie.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { NavigationBarComponent } from "../../navigation-bar/navigation-bar.component";
import { FooterComponent } from "../../footer/footer.component";
import { Movie } from '../../model/movie';

@Component({
  selector: 'app-get-movies',
  standalone: true,
  imports: [CommonModule, NavigationBarComponent, FooterComponent],
  templateUrl: './get-movies.component.html',
  styleUrl: './get-movies.component.css'
})
export class GetMoviesComponent implements OnInit {
  movies : Movie[] = [];
  page : number = 1;
  loading: boolean = false;
  constructor(private movieService : MovieService,private router : Router){ }
  ngOnInit(): void {
      this.loadMovies();
  }
  loadMovies(){
    this.page++;
    this.movieService.getPopularMovies(this.page).subscribe(
      response => {this.movies.push(...response);},
      error => {console.error('error fetching movies',error);}
    );
  }
  movieDetails(id : number){
    this.router.navigate(['/movies',id]);
  }
}
