import {Address} from './address';

export class Owner {

  id: number;
  firstName: string;
  lastName: string;
  phoneNum: string;
  adrress: Address;
  constructor(id: number, firstName: string, lastName: string, phoneNum: string, adrress: Address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNum = phoneNum;
    this.adrress = adrress;
  }
}
