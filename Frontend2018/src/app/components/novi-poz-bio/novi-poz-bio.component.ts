import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-novi-poz-bio',
  templateUrl: './novi-poz-bio.component.html',
  styleUrls: ['./novi-poz-bio.component.css']
})
export class NoviPozBioComponent implements OnInit {

  private noviPozBioForma: any;
  private pozBio : any;
  private idPozBio : number;
  private izmena: boolean;
  private naslov: string;
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
    this.naslov = 'Formirajte novo/i:';

    this.noviPozBioForma = new FormGroup({
      naziv : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(90)
      ])),
      adresa : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(90)
      ])),
      tip : new FormControl("POZ",Validators.compose([
        Validators.required
      ])),
      opis : new FormControl("")
      
    })

    this.route.params.subscribe(params => {
      (params['id'] != undefined) ? this.idPozBio = +params['id'] : this.idPozBio = -1; 
    });

    if(this.idPozBio != -1){

      this.http.get('app/vratiJedan/'+this.idPozBio).subscribe((res) => {

        if(res['_body'] != ""){
          this.pozBio = res.json();
          
          this.noviPozBioForma.patchValue({naziv: this.pozBio.naziv});
          this.noviPozBioForma.patchValue({adresa: this.pozBio.adresa});
          this.noviPozBioForma.patchValue({tip: this.pozBio.tip});
          this.noviPozBioForma.patchValue({opis: this.pozBio.opis});

          this.izmena = true;
          this.naslov = 'Unesite izmene:';

        }
      })
    }

  }

  potvrdi = function(value){
    
    if(this.izmena){
      value.id = this.idPozBio;

      this.http.put('/app/secured/izmeniPozBio', value, this.pks.postaviHeadere()).subscribe((res) => {

        if(res['_body'] != ""){
          window.location.reload();
        }else{
          alert(res.headers.get('message'))
        }
        
      });
      
    }else{
      this.http.post('/app/secured/dodajNoviPozBio', value, this.pks.postaviHeadere()).subscribe((res) => {

        if(res['_body'] != ""){
          window.location.reload();
        }else{
          alert(res.headers.get('message'))
        }
        
      });
    }

    
  }

}
