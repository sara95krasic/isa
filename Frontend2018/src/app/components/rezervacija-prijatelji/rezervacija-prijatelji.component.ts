import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';
import { AlertService } from '../../services/alert.service'

@Component({
  selector: 'app-rezervacija-prijatelji',
  templateUrl: './rezervacija-prijatelji.component.html',
  styleUrls: ['./rezervacija-prijatelji.component.css']
})
export class RezervacijaPrijateljiComponent implements OnInit {

  prijateljiStranica : number = 1;
  prijateljiLista : any[];
  searchPrijateljiText : string = "";
  searchPrijateljiZaSlanje : string = "";
  searchPrijatelji : boolean = false;
  @Input() sedista;
  @Output() nazPrijatelji = new EventEmitter();
  @Input() projId;
  izabranoSediste : number = -1;
  korisniciSedista : KorisnikSedisteDTO[] = [];

  constructor(private router : Router, private prijavljenKorisnikService : PrijavljenKorisnikService, private alertService : AlertService) { }

  ngOnInit() {
    this.ucitajPrijatelje();
  }

  ucitajPrijatelje = function(){

    var url = "";
    if(!this.searchPrijatelji){
      url = "vratiPrijatelje/"+this.prijateljiStranica;
    }else{
      url = "vratiPrijateljeImePrezime/stranica="+this.prijateljiStranica+"&kriterijum="+this.searchPrijateljiZaSlanje;
    }
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/'+url).subscribe(res=>{
      if(res['_body']!=""){
        this.prijateljiLista = res.json();   
      }
    });
  }

  prevPrijatelji(){
    this.prijateljiStranica--;
    if(this.prijateljiStranica <= 1){
      this.prijateljiStranica = 1;
    }

    this.ucitajPrijatelje();

  }

  nextPrijatelji(){
    var url = "";
    if(!this.searchPrijatelji){
      url = "vratiPrijatelje/"+(this.prijateljiStranica+1);
    }else{
      url = "vratiPrijateljeImePrezime/stranica="+(this.prijateljiStranica+1)+"&kriterijum="+this.searchPrijateljiZaSlanje;
    }
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/'+url).subscribe(res=>{

      if(res['_body']!=""){
        var lista = res.json();
        if(JSON.stringify(lista).toLowerCase() != JSON.stringify(this.prijateljiLista).toLowerCase()){
          this.prijateljiStranica++;
          this.prijateljiLista = lista;
        }
    }
    });

  }

  pretraziPrijatelje = function(){
    this.searchPrijatelji = true;
    this.prijateljiStranica = 1;
    this.searchPrijateljiZaSlanje = this.searchPrijateljiText;
    this.ucitajPrijatelje();
  }

  osveziPrijatelje = function(){
    this.searchPrijatelji = false;
    this.prijateljiStranica = 1;
    this.searchPrijateljiText = "";
    this.searchPrijateljiZaSlanje = "";
    this.ucitajPrijatelje();
  }

  pozovi = function(korisnik){

    var korisnikSediste : KorisnikSedisteDTO = {
      "korisnikId" : korisnik,
      "sedisteId" : +this.izabranoSediste
    };

      this.korisniciSedista.push(korisnikSediste);
      for(let i in this.sedista){
        if(this.sedista[i]==this.izabranoSediste){
          this.sedista.splice(i,1);
          break;
        }
      }
      this.izabranoSediste = -1;
  }

  odustani = function(korisnik){
    for(let i in this.korisniciSedista){
      if(this.korisniciSedista[i].korisnikId==korisnik){
        this.sedista.push(this.korisniciSedista[i].sedisteId);
        this.korisniciSedista.splice(i,1);
        break;
      }
    }
  }

  mozePozove = function(korisnik) : boolean{

    for(let i in this.korisniciSedista){
      if(this.korisniciSedista[i].korisnikId==korisnik){
        return false;
      }
    }
    return true;
  }

  mozeOdustane = function(korisnik) : boolean{

    for(let i in this.korisniciSedista){
      if(this.korisniciSedista[i].korisnikId==korisnik){
        return true;
      }
    }
    return false;
  }

  izaberiMesto = function(mesto){
    this.izabranoSediste = mesto;
  }

  nazadPrijatelji(){
    this.nazPrijatelji.emit();
  }

  odustaniPrijatelji(){
    this.router.navigate(['']);
  }

  potvrdiRezervaciju = function(){
    var korisnikToken = localStorage.getItem('logovanKorisnik');
    var korisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
    var id = korisnik.id;
    for(let s of this.sedista){
      var korisnikSediste : KorisnikSedisteDTO = {
        "korisnikId" : id,
        "sedisteId" : +s
      };
      this.korisniciSedista.push(korisnikSediste);
    }
    
    var rezervacijaDTO : RezervisiDTO = {
      "korisnikSedistaDTO" :  this.korisniciSedista,
      "projekcijaId" : this.projId
    };
    
    this.prijavljenKorisnikService.postuj('/app/secured/rezervisiKarte', rezervacijaDTO).subscribe(res=>{
      if(res['_body']!=""){
        var odgovor = res.json();
        if(!odgovor){
          this.alertService.console.error(res.headers.get('message'));
        }else{
          this.router.navigate(['']);
        }

      }else{
        this.alertService.console.error('Greska u slanju zahteva');      
      }

    });

    
  }

}

interface KorisnikSedisteDTO{
  korisnikId : number;
  sedisteId : number;
}

interface RezervisiDTO{
  korisnikSedistaDTO : KorisnikSedisteDTO[];
  projekcijaId : number;
}
