import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetTvShowsComponent } from './get-tv-shows.component';

describe('GetTvShowsComponent', () => {
  let component: GetTvShowsComponent;
  let fixture: ComponentFixture<GetTvShowsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetTvShowsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetTvShowsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
