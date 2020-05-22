import { Role } from './role';

export class CurrentUserDTO {
    public username: string;
    public password: string;
    public role:Role;
    
    constructor(username, password, role){
        this.username = username;
        this.password = password;
        this.role=role;
    }
}