import { Genre, Media } from "./media";
import { Season } from "./season";

export class TvShow extends Media{
    name : string;
    first_air_date : string;
    seasons : Season[];
    constructor(
        id: number,
        overview: string,
        poster_path: string,
        backdrop_path: string,
        popularity: number,
        vote_average: number,
        genres: Genre[],
        name: string,
        first_air_date: string,
        seasons: Season[] = []
    ){
        super(id, overview, poster_path, backdrop_path, popularity, vote_average, genres);
        this.name = name;
        this.first_air_date = first_air_date;
        this.seasons = seasons;
    }
}