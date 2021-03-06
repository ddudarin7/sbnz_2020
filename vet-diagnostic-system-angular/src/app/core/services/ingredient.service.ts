import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Ingredient} from '../../shared/model/ingredient';

@Injectable()
export class IngredientService {

  readonly url: string = 'http://localhost:8081/api/ingredients';
  constructor(private http: HttpClient) {}

  private headers = new HttpHeaders({ Accept: 'application/json' });

  public getIngredients(): Promise<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.url, {headers: this.headers, withCredentials: true}).toPromise()
      .then(res => res as Ingredient[]);
  }

  public add(ingredient: Ingredient): Promise<Ingredient> {
    const h = new HttpHeaders( { Accept: 'application/json', 'Content-Type': 'application/json'})
    return this.http.post<Ingredient>(this.url, ingredient, {headers: h, withCredentials: true}).toPromise().then(
      res => res as Ingredient);
  }
}
