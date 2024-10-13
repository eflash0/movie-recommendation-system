import { Genre, Media } from './media';

export class Movie extends Media {
  title: string;
  release_date: string;

  constructor(
    id: number,
    overview: string,
    poster_path: string,
    backdrop_path: string,
    popularity: number,
    vote_average: number,
    genres: Genre[],
    title: string,
    release_date: string
  ) {
    super(id, overview, poster_path, backdrop_path, popularity, vote_average, genres);
    this.title = title;
    this.release_date = release_date;
  }
}
