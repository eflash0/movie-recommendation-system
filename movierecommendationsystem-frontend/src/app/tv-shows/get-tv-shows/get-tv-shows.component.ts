import { Component, OnInit } from '@angular/core';
import { FooterComponent } from "../../footer/footer.component";
import { NavigationBarComponent } from "../../navigation-bar/navigation-bar.component";
import { Router } from '@angular/router';
import { TvShowService } from '../../service/tv-show.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-tv-shows',
  standalone: true,
  imports: [FooterComponent, NavigationBarComponent, CommonModule],
  templateUrl: './get-tv-shows.component.html',
  styleUrl: './get-tv-shows.component.css'
})
export class GetTvShowsComponent implements OnInit {
  tvShows : any = [];
  page : number = 1;
  loading: boolean = false;
  constructor(private tvShowService : TvShowService,private router : Router){ }
  ngOnInit(): void {
      this.loadTvShows();
  }
  loadTvShows(){
    this.page++;
    this.tvShowService.getPopularTvShows(this.page).subscribe(
      response => {this.tvShows.push(...response);},
      error => {console.error('error fetching tvShows',error);}
    );
  }
  tvShowDetails(id : number){
    this.router.navigate(['/tv-shows',id]);
  }
}
