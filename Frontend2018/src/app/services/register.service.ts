import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/map';

@Injectable()
export class RegisterService {

  constructor(public http:Http) { }

  registrujKorisnika(path : string, body : any){
    return this.http.post(path, body);
  }

  dobaviKorisnika(path : string){
    return this.http.get(path).map(res => res.json());
  }

  izmeniKorisnika(path: string,  body : any){
    return this.http.put(path, body);
  }

}
