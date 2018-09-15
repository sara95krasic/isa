import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-sala-preview',
  templateUrl: './sala-preview.component.html',
  styleUrls: ['./sala-preview.component.css']
})
export class SalaPreviewComponent implements OnInit {

  private segmenti: any;
  private sala : any = {};
  private idSala : number;
  private mode: number;
  private zaIzmenu: any;
  private zaBrzu: any;
  private uloga: any;

  private isIzmena: boolean;
  private isDodavanjeSegmenta: boolean;
  private isTipSegmenta: boolean;
  private isIzmenaSegmenta: boolean;
  private isBrzaRezervacija: boolean;

  constructor(private http:Http, private route: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    this.mode = 1;
    this.isIzmena = false;
    this.isDodavanjeSegmenta = false;
    this.isTipSegmenta = false;

    this.route.params.subscribe(params => {
      this.idSala = +params['id'];
    });

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

    this.http.get('/app/vratiJednuSalu/'+this.idSala).subscribe((res) => {
      
      if(res['_body'] != ""){
        this.sala = res.json();
      }else{
        alert("Greska!"+res.headers.get("Message"));
      }
    });

    this.http.get('/app/vratiSegmenteSala/'+this.idSala).subscribe((res) => {
      
      if(res['_body'] != ""){
        this.segmenti = res.json();
      }else{
        alert("Greska!"+res.headers.get("Message"));
      }
    });

  }

  izmeni = function(){
    this.isIzmena = !this.isIzmena;
  }

  dodajSegment = function(){
    this.isDodavanjeSegmenta = !this.isDodavanjeSegmenta;
  }

  dodajTipSegmenta = function(){
    this.isTipSegmenta = !this.isTipSegmenta;
  }

  izmeniSegment = function(value){
    this.isIzmenaSegmenta = !this.isIzmenaSegmenta;

    if(this.isIzmenaSegmenta){
      this.zaIzmenu = value;
      console.log(this.zaIzmenu)
    }
    
  }

}
