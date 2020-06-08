import { Role } from './role';

export class Admin {
	public id:number;
    public username:string;
    public password:string;
    public firstName:string;
    public lastName:string;
    public role:Role;
    
    constructor(id:number,username:string, password:string, firstName:string,lastName:string,role:Role){
        this.id=id;
        this.username = username;
        this.password = password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.role=role;
    }
}