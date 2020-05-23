import {Address} from './address';

export class Owner {

  id: number;
  firstName: string;
  lastName: string;
  phoneNum: string;
  address: Address;
  constructor(id: number, firstName: string, lastName: string, phoneNum: string, address: Address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNum = phoneNum;
    this.address = address;
  }
}
