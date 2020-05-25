import { Medicine } from './medicine';

export class Therapy {

    id: number;
    description: string;
    medicines: Medicine[];
    
  
    constructor(id: number,description: string,medicines:Medicine[]) {
      this.id = id;
      this.description=description;
      this.medicines=medicines;
    }
  }
  