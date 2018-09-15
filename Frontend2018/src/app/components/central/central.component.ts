import { Component, OnInit } from '@angular/core';
import { PozBioService } from '../../services/poz-bio.service';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';


@Component({
  selector: 'app-central',
  templateUrl: './central.component.html',
  styleUrls: ['./central.component.css']
})
export class CentralComponent implements OnInit {

  istorijatPoseta : any = null;
  istorijatStranica : number = 1;
  logovan : boolean = false;

  private mode: number;
  private idProjekcija: number;
  private isGlasanje: boolean;

  constructor(private pozBioService : PozBioService, private router: Router, private prijavljenKorisnikService : PrijavljenKorisnikService, private http: Http) { }

  ngOnInit() {

    this.isGlasanje = false;
    this.mode = -1;

    var korisnikToken = localStorage.getItem('logovanKorisnik');
    if(korisnikToken!=null){
      var korisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
      var uloga = korisnik.uloga[0].authority;
      if(uloga==='RK'){
        this.logovan = true;
        this.ucitajIstorijatPoseta();
      }
    }
  }

  prikaziBioskope(){
    this.pozBioService.setTip('bio');
    this.router.navigate(['/bioskopi/stranica/1']);
  }

  prikaziPozorista(){
    this.pozBioService.setTip('poz');
    this.router.navigate(['/pozorista/stranica/1']);
  }

  ucitajIstorijatPoseta = function(){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiIstoriju/'+this.istorijatStranica).subscribe(res=>{
      if(res['_body']!=""){
        this.istorijatPoseta = res.json();
      }
    });
  }

  prevIstorijat(){
    this.istorijatStranica--;
    if(this.istorijatStranica <= 1){
      this.istorijatStranica = 1;
    }

    this.ucitajIstorijatPoseta();
  }

  nextIstorijat(){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiIstoriju/'+(this.istorijatStranica+1)).subscribe(res=>{

      if(res['_body']!=""){
        var lista = res.json();
        if(JSON.stringify(lista).toLowerCase() != JSON.stringify(this.istorijatPoseta).toLowerCase()){
          this.istorijatStranica++;
          this.istorijatPoseta = lista;
        }
      }
    });
  }

  oceniAmbijent = function(idProj : number){
    this.mode = 0;
    this.idProjekcija = idProj;
    this.isGlasanje = !this.isGlasanje;
  }

  oceniProjekciju = function(idProj : number){
    this.mode = 1;
    this.idProjekcija = idProj;
    this.isGlasanje = !this.isGlasanje;
  }

  skloniMe = function(val: boolean){
    this.isGlasanje = false;
  }

  ponistiAmbijent = function(idProj : number){
    
    this.http.put("/app/secured/izbrisiOcenu/"+0+"?idProjekcije="+idProj,null , this.prijavljenKorisnikService.postaviHeadere()).subscribe(res => {
      if(res['_body'] == 'true'){
        console.log(res);
        window.location.reload();
      }else{
        alert(res.headers.get('message'));
      }
    })
    
  }

  ponistiProjekciju = function(idProj : number){
    this.http.put("/app/secured/izbrisiOcenu/"+1+"?idProjekcije="+idProj,null , this.prijavljenKorisnikService.postaviHeadere()).subscribe(res => {
      if(res['_body'] == 'true'){
        console.log(res);
        window.location.reload();
      }else{
        alert(res.headers.get('message'));
      }
    })
  }


}
