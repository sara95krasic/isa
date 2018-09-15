import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../services/alert.service'
import { AppComponent } from '../../app.component';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-prijatelji',
  templateUrl: './prijatelji.component.html',
  styleUrls: ['./prijatelji.component.css']
})
export class PrijateljiComponent implements OnInit {

  prijateljiStranica : number = 1;
  zahteviStranica : number = 1;
  korisniciStranica : number = 1;
  prijateljiLista : any[];
  zahteviLista : any[];
  korisniciLista : any[];
  korisniciTipovi : any[];
  searchPrijatelji : boolean = false;
  searchKorisnici : boolean = false;
  searchPrijateljiText : string = "";
  searchPrijateljiZaSlanje : string = "";
  searchKorisniciText : string = "";
  searchKorisniciZaSlanje : string = "";

  constructor(private router : Router, private prijavljenKorisnikService : PrijavljenKorisnikService) { }

  ngOnInit() {
    var korisnikToken = localStorage.getItem('logovanKorisnik');
    if(korisnikToken==null){
      this.router.navigate(['']);
    }else{
      var korisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
      var uloga = korisnik.uloga[0].authority;
      if(uloga!=='RK'){
        this.router.navigate(['']);
      }else{
        this.ucitajPrijatelje();
      }    
    }
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
        console.log(lista);
        if(JSON.stringify(lista).toLowerCase() != JSON.stringify(this.prijateljiLista).toLowerCase()){
          this.prijateljiStranica++;
          this.prijateljiLista = lista;
        }
    }
    });

  }

  prevZahtevi(){
    this.zahteviStranica--;
    if(this.zahteviStranica <= 1){
      this.zahteviStranica = 1;
    }

    this.ucitajPosiljaoce();

  }

  nextZahtevi(){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vrativratiPosiljaoce/'+(this.zahteviStranica+1)).subscribe(res=>{

      if(res['_body']!=""){
        var lista = res.json();
        if(JSON.stringify(lista).toLowerCase() != JSON.stringify(this.zahteviLista).toLowerCase()){
          this.zahteviStranica++;
          this.zahteviLista = lista;
        }
      }
    });

  }

  prevKorisnici(){
    this.korisniciStranica--;
    if(this.korisniciStranica <= 1){
      this.korisniciStranica = 1;
    }

    this.ucitajKorisnike();

  }

  nextKorisnici(){
    var url = "";
    if(!this.searchKorisnici){
      url = "vratiKorisnike/"+(this.korisniciStranica+1);
    }else{
      url = "vratiKorisnikeImePrezime/stranica="+(this.korisniciStranica+1)+"&kriterijum="+this.searchKorisniciZaSlanje;
    }
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/'+url).subscribe(res=>{
      if(res['_body']!=""){
        var lista = res.json();
        if(JSON.stringify(lista).toLowerCase() != JSON.stringify(this.korisniciLista).toLowerCase()){
          this.korisniciStranica++;
          this.korisniciLista = lista;
          this.postaviTipoveKorisnika();
        }
    }

    });

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

  ucitajPosiljaoce = function(){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiPosiljaoce/'+this.zahteviStranica).subscribe(res=>{
      if(res['_body']!=""){
        this.zahteviLista = res.json();
      }
    });
  }

  ucitajKorisnike = function(){
    var url = "";
    if(!this.searchKorisnici){
      url = "vratiKorisnike/"+this.korisniciStranica;
    }else{
      url = "vratiKorisnikeImePrezime/stranica="+this.korisniciStranica+"&kriterijum="+this.searchKorisniciZaSlanje;
    }
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/'+url).subscribe(res=>{
      if(res['_body']!=""){
        this.korisniciLista = res.json();
        this.postaviTipoveKorisnika();
      }
    });
  } 

  prihvatiZahtev = function(korisnik,tip){
    this.prijavljenKorisnikService.postuj('/app/secured/prihvatiZahtev',korisnik).subscribe(res=>{
      if(res['_body']!=""){
        var uspeh = res.json();
        if(uspeh){
          if(tip==='Z'){
            this.deleteFromArray(this.zahteviLista,korisnik);
          }else if(tip==='K'){
            this.ucitajKorisnike();
          }

        }
    }
    });
  }
  
  obrisiZahtev = function(korisnik,tip){
    this.prijavljenKorisnikService.postuj('/app/secured/obrisiZahtev',korisnik).subscribe(res=>{
      if(res['_body']!=""){
        var uspeh = res.json();
        if(uspeh){
          if(tip==='Z'){
            this.deleteFromArray(this.zahteviLista,korisnik);
          }else if(tip==='K'){
            this.ucitajKorisnike();
          }

        }
      }
    });
  }

  posaljiZahtev = function(korisnik,tip){
    this.prijavljenKorisnikService.postuj('/app/secured/posaljiZahtev',korisnik).subscribe(res=>{
      if(res['_body']!=""){
        var uspeh = res.json();
        if(uspeh){
          if(tip==='K'){
            this.ucitajKorisnike();
          }

        }
      }
    });
  }

  obrisiPrijatelja= function(korisnik,tip){
    this.prijavljenKorisnikService.postuj('/app/secured/obrisiPrijatelja',korisnik).subscribe(res=>{
      if(res['_body']!=""){
        var uspeh = res.json();
        if(uspeh){
          if(tip==='P'){
            this.deleteFromArray(this.prijateljiLista,korisnik);
          }else if(tip==='K'){
            this.ucitajKorisnike();
          }

        }
      }
    });
  }

  postaviTipoveKorisnika = function(){

    this.prijavljenKorisnikService.postujListu('/app/secured/proveriPrijateljstvo',this.korisniciLista).subscribe(res=>{
      if(res['_body']!=""){
        this.korisniciTipovi = res.json();
      }
    });
  }

  deleteFromArray = function(array: any[], element: any){

    const index : number = array.indexOf(element);
    if(index!==-1){
      array.splice(index,1);
    }
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

  pretraziKorisnike= function(){
    this.searchKorisnici = true;
    this.korisniciStranica = 1;
    this.searchKorisniciZaSlanje = this.searchKorisniciText;
    this.ucitajKorisnike();
  }

  osveziKorisnike= function(){
    this.searchKorisnici = false;
    this.korisniciStranica = 1;
    this.searchKorisniciText = "";
    this.searchKorisniciZaSlanje = "";
    this.ucitajKorisnike();
  }

}
