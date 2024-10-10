import { Component, Inject } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-trailer-dialog',
  standalone: true,
  imports: [],
  templateUrl: './trailer-dialog.component.html',
  styleUrl: './trailer-dialog.component.css'
})
export class TrailerDialogComponent {
  trailerUrl : SafeResourceUrl = '';
  constructor(@Inject(MAT_DIALOG_DATA) public data : any,private sanitizer : DomSanitizer){
    this.trailerUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
      `https://www.youtube.com/embed/${data.trailerKey}`
    );
    console.log(data.trailerKey);
    
  }
}
