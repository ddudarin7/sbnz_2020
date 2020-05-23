import {Ingredient} from './ingredient';

export class Medicine {

  id: number;
  name: string;
  ingredients: Ingredient[];

  constructor(id: number, name: string, ingredients: Ingredient[]) {
    this.id = id;
    this.name = name;
    this.ingredients = ingredients;
  }
}
