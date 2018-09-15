import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';

@Injectable()
export class PonudaService {

  constructor(private http : Http) { }

  public postaviHeadere() : RequestOptions{
    let headers = new Headers();
    headers.append('token', localStorage.getItem('logovanKorisnik'));
    let options = new RequestOptions({headers : headers});

    return options;
  }

  posaljiPonudu(path:string, body: any){
    return this.http.post(path, body, this.postaviHeadere());
  }

  dobaviPonudu(path: string){
    return this.http.get(path, this.postaviHeadere()).map(res => res.json());
  }

  izmeniPonudu(path: string, body: any){
    return this.http.put(path, body, this.postaviHeadere());
  }

  obrisiPonudu(path: string){
    return this.http.delete(path, this.postaviHeadere());
  }

  prihvatiPonudu(path: string){
    return this.http.put(path, this.postaviHeadere());
  }
}
