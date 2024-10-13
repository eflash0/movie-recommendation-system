import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../service/movie.service';
import { FooterComponent } from "../footer/footer.component";
import { NavigationBarComponent } from "../navigation-bar/navigation-bar.component";
import { CommonModule } from '@angular/common';
import { General } from '../model/generalInfo';

@Component({
  selector: 'app-search-results',
  standalone: true,
  imports: [FooterComponent, NavigationBarComponent, CommonModule],
  templateUrl: './search-results.component.html',
  styleUrl: './search-results.component.css'
})
export class SearchResultsComponent implements OnInit {
  searchResults : General[] = [];
  query : string = '';
  page : number = 1;
  constructor(private route : ActivatedRoute,private movieService : MovieService,
    private router : Router
  ){}
  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.query = params['query'] || '';
        if(this.query){
          this.search();
        }
      }
    );    
  }
  search(){
    this.movieService.search(this.query,this.page).subscribe(
      response => {this.searchResults = response;},
      error => {console.error('error searching',error);}
    );
  }
  
  viewDetails(media : any){
    if(media.title){
      this.router.navigate(['/movies',media.id]);
    }
    else{
      this.router.navigate(['/tv-shows',media.id]);
    }
  }

  loadMovies(){
    this.page++;
    this.movieService.search(this.query,this.page).subscribe(
      response => {this.searchResults.push(...response);},
      error => {console.error('error during the searching',error);}
    );
  }
}
