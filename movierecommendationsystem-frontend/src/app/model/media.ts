export class Media{
    constructor(
        public id : number,
        public overview : string,
        public poster_path : string,
        public backdrop_path : string,
        public popularity : number,
        public vote_average : number,
        public genres : Genre[] = []
    ){}
}

export class Genre{
    constructor(
        public id : number,
        public name : string
    ){}
}