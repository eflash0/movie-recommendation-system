export class Episode {
    constructor(
        public id: number,
        public name: string,
        public episode_number: number,
        public overview: string,
        public air_date: Date,
        public still_path: string
    ) {}
}

export class Season {
    constructor(
        public id: number,
        public season_number: number,
        public name: string,
        public episode_count: number,
        public overview: string,
        public poster_path: string,
        public air_date: Date,
        public episodes: Episode[] = []
    ) {}
}
