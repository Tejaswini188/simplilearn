export class User {
    id: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    token: string;
}



export class UserDetails {
    id: number;
    name: string;
    username: string;
    roles:Roles;
    token: string;
}


export enum Roles {
    Admin="ROLE_ADMIN", 
    User="ROLE_USER", 
    Moderator="ROLE_MODERATOR"
  };