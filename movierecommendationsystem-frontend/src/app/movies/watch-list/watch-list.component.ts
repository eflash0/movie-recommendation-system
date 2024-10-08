import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NavigationBarComponent } from "../../navigation-bar/navigation-bar.component";

@Component({
  selector: 'app-watch-list',
  standalone: true,
  imports: [CommonModule, NavigationBarComponent],
  templateUrl: './watch-list.component.html',
  styleUrl: './watch-list.component.css'
})
export class WatchListComponent implements OnInit {
  watchList : any = [];
  ngOnInit(): void {
      
  }

  removeFromWatchList(id : number){
    
  }
}
