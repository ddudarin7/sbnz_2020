import {Owner} from './owner';
import {Ingredient} from './ingredient';
import {Medicine} from './medicine';
import {Vaccination} from './vaccination';

export class Patient {

  id: number;
  name: string;
  recordNumber: string;
  dateOfBirth: Date;
  breed: string;
  owner: Owner;
  medicineAllergies: Medicine[];
  ingredientAllergies: Ingredient[];
  vaccinations: Vaccination[];


  constructor(id: number, name: string, recordNumber: string, dateOfBirth: Date, breed: string, owner: Owner,
              medicineAllergies: Medicine[], ingredientAllergies: Ingredient[], vaccinations: Vaccination[]) {
    this.id = id;
    this.name = name;
    this.recordNumber = recordNumber;
    this.dateOfBirth = dateOfBirth;
    this.breed = breed;
    this.owner = owner;
    this.medicineAllergies = medicineAllergies;
    this.ingredientAllergies = ingredientAllergies;
    this.vaccinations = vaccinations;
  }
}
