import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../services/alert.service'
import { AppComponent } from '../../app.component';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  izmenaForma;
  lozinkaForma;
  logovanKorisnik : any;
  zaIzmenu : any;
  izmena : boolean = false;
  izmenaLozinke : boolean = false;

  constructor(private alertService : AlertService, private prijavljenKorisnikService: PrijavljenKorisnikService, private router : Router) { }

  ngOnInit() {

    var korisnikToken = localStorage.getItem('logovanKorisnik');
    if(korisnikToken==null){
      this.router.navigate(['']);
    }else{
      var korisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
      var uloga = korisnik.uloga[0].authority;
      if(uloga==='AS'){
        this.router.navigate(['']);
      }else{
        this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiRegKorisnika').subscribe(res=>{
        this.logovanKorisnik = res.json();
      });
      }    
    }
  }

  izmeni = function(){
    this.zaIzmenu = Object.assign({},this.logovanKorisnik);
    this.izmenaForma = new FormGroup({
      ime : new FormControl(this.zaIzmenu.ime,Validators.compose([
        Validators.required,
        Validators.maxLength(30)
      ])),
      prezime : new FormControl(this.zaIzmenu.prezime,Validators.compose([
        Validators.required,
        Validators.maxLength(30)
      ])),
      grad : new FormControl(this.zaIzmenu.grad,Validators.compose([
        Validators.required,
        Validators.maxLength(60)
      ])),
      telefon : new FormControl(this.zaIzmenu.telefon,Validators.pattern('\\+?[0-9]{6,12}'))
    });
    this.izmena = true;
  }

  promeniPoljaLozinke = function(){
    this.lozinkaForma = new FormGroup({
      staraLozinka : new FormControl("",Validators.compose([
        Validators.required
      ])),
      novaLozinka : new FormControl("", Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(30)
      ])),
      potvrdaLozinke : new FormControl("", Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(30)
      ]))
    },this.passwordMatchValidator);
    this.izmenaLozinke = true;
  }

  passwordMatchValidator = function(g: FormGroup) {
    return g.get('novaLozinka').value === g.get('potvrdaLozinke').value ? null : {'missmatch': true};
 }

  ponisti = function(){
    this.izmena = false;
  }

  potvrdiIzmenu = function(korisnik){
    
    korisnik.email = this.logovanKorisnik.email;
    korisnik.tip = this.logovanKorisnik.tip;
    this.prijavljenKorisnikService.izmeniKorisnika('/app/secured/izmena',korisnik).subscribe((res) => {
      this.success = res.json();
      this.message = res.headers.get('message');
      if(!this.success){
        this.alertService.error(this.message);
      }else{
        this.logovanKorisnik = korisnik;
        this.alertService.success(this.message);
      }
    });
  }

  promeniLozinku = function(){

    if(this.izmenaLozinke){
      this.izmenaLozinke = false; 
    }else{
      this.promeniPoljaLozinke()
    }
  }

  potvrdiIzmenuLozinke = function(lozinka){
    this.prijavljenKorisnikService.izmeniKorisnika('/app/secured/izmenaLozinke',lozinka).subscribe((res) => {
      this.success = res.json();
      this.message = res.headers.get('message');
      if(!this.success){
        this.alertService.error(this.message);
      }else{
        this.alertService.success(this.message);
      }
    });


  }

}
