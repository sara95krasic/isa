import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-novi-pred-film',
  templateUrl: './novi-pred-film.component.html',
  styleUrls: ['./novi-pred-film.component.css']
})
export class NoviPredFilmComponent implements OnInit {

  private noviPredFilmForma: any;
  private naslov: string;
  private izmena: boolean; 

  private idPredFilm: number;
  private predFilm: any;
  private uloga: any;

  constructor(private http:Http, private router: Router, private route: ActivatedRoute, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    let korisnikToken = localStorage.getItem('logovanKorisnik');
    if(korisnikToken){
      let logovanKorisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
      this.uloga = logovanKorisnik.uloga[0].authority;
        if(this.uloga !== 'AU'){
        this.router.navigate(['']);
        }
      }else{
        this.router.navigate(['']);
      }

    this.izmena = false;
    this.naslov = 'Formirajte novu/o :'

    this.noviPredFilmForma = new FormGroup({
      naziv : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(90)
      ])),
      zanr : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(30)
      ])),
      tip : new FormControl("PRED",Validators.compose([
        Validators.required
      ])),
      reditelj : new FormControl("",Validators.compose([
        Validators.maxLength(60)
      ])),
      trajanje : new FormControl("",Validators.compose([
        Validators.required
      ])),
      glumci : new FormControl(""),
      opis : new FormControl("")
    })

    this.route.params.subscribe(params => {
      (params['id'] != undefined) ? this.idPredFilm = +params['id'] : this.idPredFilm = -1; 
    });

    if(this.idPredFilm != -1){

      this.http.get('app/vratiPredFilm/'+this.idPredFilm).subscribe((res) => {

        if(res['_body'] != ""){
          this.predFilm = res.json();
          
          this.noviPredFilmForma.patchValue({naziv: this.predFilm.naziv});
          this.noviPredFilmForma.patchValue({zanr: this.predFilm.zanr});
          this.noviPredFilmForma.patchValue({reditelj: this.predFilm.reditelj});
          this.noviPredFilmForma.patchValue({tip: this.predFilm.tip});
          this.noviPredFilmForma.patchValue({trajanje: this.predFilm.trajanje});
          this.noviPredFilmForma.patchValue({glumci: this.predFilm.glumci});
          this.noviPredFilmForma.patchValue({opis: this.predFilm.opis});
  
          this.izmena = true;
          this.naslov = 'Unesite izmene:';
        }
      })

    }

  }

  potvrdi = function(noviPredFilm){

    if(this.izmena){

      noviPredFilm.id = this.predFilm.id;

      this.http.put('/app/secured/izmeniPredFilm', noviPredFilm, this.pks.postaviHeadere()).subscribe((res) => {

        if(res['_body'] != ""){
          window.location.reload();
        }else{
          alert(res.headers.get('message'))
        }
        
      });
    }else{

      this.http.post('/app/secured/sacuvajPredFilm', noviPredFilm, this.pks.postaviHeadere()).subscribe((res) => {

        if(res['_body'] != ""){
          this.router.navigate(['']);
        }else{
          alert(res.headers.get('message'))
        }
        
      });

    }
  }

}
