import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
@Injectable()
export class OglasiService {

  constructor(private http : Http) { }

  public postaviHeadere() : RequestOptions{
    let headers = new Headers();
    headers.append('token', localStorage.getItem('logovanKorisnik'));
    let options = new RequestOptions({headers : headers});

    return options;
  }

  posaljiOglas(path:string, body: any){
    return this.http.post(path, body, this.postaviHeadere());
  }

  dobaviOglase(path: string){
    return this.http.get(path, this.postaviHeadere()).map(res => res.json());
  }

  
  izmeniOglas(path: string, body: any){
    return this.http.put(path, body, this.postaviHeadere());
  }

  rezervisiOglas(path: string){
    return this.http.get(path, this.postaviHeadere());
  }

  izmeniOglasPost(path: string, body: any){
    return this.http.post(path, body, this.postaviHeadere());
  }

  obrisiOglas(path: string){
    return this.http.delete(path,this.postaviHeadere());
  }

  daLiJeTudji(path: string){
    return this.http.post(path, this.postaviHeadere());
  }

}
