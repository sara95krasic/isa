import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PozBioService } from '../../services/poz-bio.service';
import { AgmCoreModule } from '@agm/core';


@Component({
  selector: 'app-poz-bio-preview',
  templateUrl: './poz-bio-preview.component.html',
  styleUrls: ['./poz-bio-preview.component.css']
})
export class PozBioPreviewComponent implements OnInit {

  private pozBio: any;
  private sale: any[];
  private id: number;
  private idSala: number;
  private ocena: any;

  latitude: number = 51.678418;
  longitude: number = 7.809007;

  private isDodavanje: boolean;
  private isIzmena: boolean;
  private isProjekcija: boolean;
  private isBrza: boolean;
  private uloga: any;

  constructor(private http:Http, private route: ActivatedRoute, private router: Router, private pozBioService: PozBioService) { }

  ngOnInit() {

    this.idSala = -1;

    this.isDodavanje = false;
    this.isIzmena = false;
    this.isProjekcija = false;
    this.pozBio = {};
    this.isBrza = false;

    let korisnikToken = localStorage.getItem('logovanKorisnik');
    if(korisnikToken){
      let logovanKorisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
      this.uloga = logovanKorisnik.uloga[0].authority;
    }

    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });

    this.http.get('/app/vratiJedan/'+this.id).subscribe((res1) => {
      
      if(res1['_body'] != ""){
        this.pozBio = res1.json();

        this.http.get('https://maps.googleapis.com/maps/api/geocode/json?address=' + this.pozBio.adresa ).subscribe((res) => {
          let temp = res.json();
          this.longitude = res.json().results[0].geometry.location.lng;
          this.latitude = res.json().results[0].geometry.location.lat;
        });

        
      }else{
        this.router.navigate(['/']);
      }
    });

    this.http.get('/app/ocenaAmbijenta/'+this.id).subscribe((res) => {
      if(res['_body'] != ""){
        this.ocena = Math.round(res.json() * 100) / 100;
      }else{
        this.ocena = 0;
      }
    });

    this.http.get('/app/vratiSale/'+this.id).subscribe((res) => {
      
      if(res['_body'] === ""){
        this.router.navigate(['/']);
      }else{
        this.sale = res.json();
      }
    });

  }

  pogledajSalu = function(salaId : number){
    this.router.navigate(['/sala/'+salaId]);
  }

  izmeniSalu = function(salaId : number){

    this.isIzmena = !this.isIzmena;
    this.idSala = salaId;   
  }

  dodaj = function(){
    this.isDodavanje = !this.isDodavanje;
  }

  izmeniPozBio = function(){
    this.router.navigate(['izmeniPozBio/'+this.pozBio.id]);
  }

  dodajProjekciju = function(){
    this.isProjekcija = !this.isProjekcija;
  }
  
  brzaRezervacija = function(){
    this.isBrza = !this.isBrza;
  }

}
