import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';

@Injectable()
export class KartaService {

  constructor(private http: Http) { }

  private postaviHeadere() : RequestOptions{
    let headers = new Headers();
    headers.append('token', localStorage.getItem('logovanKorisnik'));
    let options = new RequestOptions({headers : headers});

    return options;
  }

  dobaviKartu(path: string){
    return this.http.get(path, this.postaviHeadere());
  }

}
