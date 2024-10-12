export class Episode {
    constructor(
        public id: number,
        public name: string,
        public episodeNumber: number,
        public overview: string,
        public airDate: Date,
        public seasonNumber: number
    ) {}
}

export class Season {
    constructor(
        public id: number,
        public seasonNumber: number,
        public name: string,
        public episodeCount: number,
        public overview: string,
        public posterPath: string,
        public airDate: Date,
        public episodes: Episode[] = [] // Default to an empty array
    ) {}
}
