export enum Role {
    USER = 'USER',
    ADMIN = 'ADMIN',
}

export interface User {
    userId: number;
    username: string;
    email: string;
    role: Role;
}
