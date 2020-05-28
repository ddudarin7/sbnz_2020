import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'lowerCase'
})
export class LowerCasePipe implements PipeTransform {

  transform(name:string): any {
    return name.split("_").join(" ").toLowerCase();
  }

}
