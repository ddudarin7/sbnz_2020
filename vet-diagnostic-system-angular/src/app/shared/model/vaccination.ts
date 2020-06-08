import {Vaccine} from './vaccine';

export class Vaccination{

  id: number;
  vaccine: Vaccine;
  date: Date;


  constructor(id: number, vaccine: Vaccine, date: Date) {
    this.id = id;
    this.vaccine = vaccine;
    this.date = date;
  }
}
