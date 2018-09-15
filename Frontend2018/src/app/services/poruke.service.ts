import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';

@Injectable()
export class PorukeService {

  constructor(private http : Http) { }

  public postaviHeadere() : RequestOptions{
    let headers = new Headers();
    headers.append('token', localStorage.getItem('logovanKorisnik'));
    let options = new RequestOptions({headers : headers});

    return options;
  }

  dobaviPoruke(path: string){
    return this.http.get(path, this.postaviHeadere()).map(res => res.json());
  }


  obrisiPoruku(path: string){
    return this.http.delete(path, this.postaviHeadere());
  }


}
