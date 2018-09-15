import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class PozBioService {

  private tip : string;

  constructor(public http:Http) {}

  vratiSadrzaj(path:string){
    return this.http.get(path).map(res => res.json());
  }

  setTip(param: string){
    this.tip = param;
  }

  getTip(){
    return this.tip;
  }
   
}
