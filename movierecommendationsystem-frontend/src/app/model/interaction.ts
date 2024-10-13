import { User } from "./user";

export interface Interaction {
    interactionId: number;
    user: User;
    mediaId: number;
    rating: number;
    favorite: boolean;
    watchList: boolean;
    mediaType: string;
}
