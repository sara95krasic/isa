import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../services/alert.service'
import { AppComponent } from '../../app.component';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';


@Component({
  selector: 'app-rezervacije',
  templateUrl: './rezervacije.component.html',
  styleUrls: ['./rezervacije.component.css']
})
export class RezervacijeComponent implements OnInit {

  rezervacije : any = null;
  rezervacijeStranica : number = 1;

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
        this.ucitajRezervacije();
      }    
    }

  }

  ucitajRezervacije = function(){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiRezervacije/'+this.rezervacijeStranica).subscribe(res=>{
      if(res['_body']!=""){
        this.rezervacije = res.json();
      }
    });
  }

  prevRezervacije(){
    this.rezervacijeStranica--;
    if(this.rezervacijeStranica <= 1){
      this.rezervacijeStranica = 1;
    }

    this.ucitajRezervacije();

  }

  nextRezervacije(){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiRezervacije/'+(this.rezervacijeStranica+1)).subscribe(res=>{

      if(res['_body']!=""){
        var lista = res.json();
        if(JSON.stringify(lista).toLowerCase() != JSON.stringify(this.rezervacije).toLowerCase()){
          this.rezervacijeStranica++;
          this.rezervacije = lista;
        }
      }
    });
  }

  otkaziRezervaciju = function(id:number){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/otkaziRezervaciju/'+id).subscribe(res=>{
      if(res['_body']!=""){
        var uspeh = res.json();
        if(uspeh){
          var index = 0;
          for(let r of this.rezervacije){
            if(r.id===id){
              this.rezervacije.splice(index,1);
              break;
            }
            index++;
          }
        }
      }
    });
  }
}
