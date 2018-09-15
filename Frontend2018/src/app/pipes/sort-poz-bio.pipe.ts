import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sortPozBio'
})
export class SortPozBioPipe implements PipeTransform {

  transform(value: any, args: string): any {

    if(args==='N'){

      value.sort((a: any, b: any) => {
        if (a.naziv < b.naziv) {
          return -1;
        } else if (a.naziv > b.naziv) {
          return 1;
        } else {
          return 0;
        }
      });

    }else if(args==='A'){

      value.sort((a: any, b: any) => {
        if (a.adresa < b.adresa) {
          return -1;
        } else if (a.adresa > b.adresa) {
          return 1;
        } else {
          return 0;
        }
      });
    }else{

      value.sort((a: any, b: any) => {
        if (a.id < b.id) {
          return -1;
        } else if (a.id > b.id) {
          return 1;
        } else {
          return 0;
        }
      });

    }

    return value;
  }

}
