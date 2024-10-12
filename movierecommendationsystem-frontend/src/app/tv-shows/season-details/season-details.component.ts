import { Component, OnInit } from '@angular/core';
import { Season } from '../../model/season';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { TvShowService } from '../../service/tv-show.service';

@Component({
  selector: 'app-season-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './season-details.component.html',
  styleUrl: './season-details.component.css'
})
export class SeasonDetailsComponent implements OnInit {
  season : Season | null = null;
  seasonNumber : number = 0;
  tvShowId : number = 0;
  constructor(private route : ActivatedRoute, private tvShowService : TvShowService){ }
  ngOnInit(): void {
    this.seasonNumber = +this.route.snapshot.paramMap.get('seasonNumber')!;
    this.tvShowId = +this.route.snapshot.paramMap.get('tvShowId')!;
    this.tvShowService.getSeasonDetails(this.tvShowId,this.seasonNumber).subscribe(
      response => {this.season = response;},
      error => {console.error('error fetching season details');}
    );
  }
}
