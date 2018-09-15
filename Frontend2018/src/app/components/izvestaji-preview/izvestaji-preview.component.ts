import { Component, OnInit, ViewChild,ElementRef } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-izvestaji-preview',
  templateUrl: './izvestaji-preview.component.html',
  styleUrls: ['./izvestaji-preview.component.css']
})

export class IzvestajiPreviewComponent implements OnInit {

  @ViewChild("izvestaji") canvasNesto: ElementRef;

  private pozorista: any[];
  private bioskopi: any[];
  private predstave: any[];
  private filmovi: any[];
  private pozBios: any[];
  private pozBiosPr: any[];
  private predFilms: any[];
  private poslovanjePB: any[];

  private ambijentTip: number;
  private ambijentZa: number;

  private projekcijeTip: number;
  private projekcijeZa: number;

  private ocenaA: number;
  private ocenaP: number;

  private prihodiTip: number; 
  private prihodiZa: any;

  private izvestajDatum1: any;
  private izvestajDatum2: any;
  private izvestajDatum3: any;

  //D, N, M
  private izvestajTip: number;
  private poslovanjeTip: number;
  private poslovanjeZa: number;

  private xOsa: any[];
  private yOsa: any[];

  private chart: any[];
  private uloga: any;


  constructor(private http:Http, private rks: PrijavljenKorisnikService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.ocenaA = 0.0;
    this.ocenaP = 0.0;

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

    this.http.get("/app/vratiSva").subscribe(res => {
      if(res['_body'] != ''){
        let temp = res.json();
        this.pozorista = temp[0];
        this.bioskopi = temp[1];
      }
    });

    this.http.get("/app/vratiSvePredFilmove").subscribe(res => {
      if(res['_body'] != ''){
        let temp = res.json();
        this.predstave = temp[0];
        this.filmovi = temp[1];
      }
    });

  }

  //vratiSvePredFilmove

  promeniAmbijent = function(){

    if(this.ambijentTip == 0){
      this.pozBios = this.pozorista;
    }else if(this.ambijentTip == 1){
      this.pozBios = this.bioskopi;
    }else{
      this.pozBios = [];
    }
  }

  promeniProjekciju = function(){

    if(this.projekcijeTip == 1){
      this.predFilms = this.predstave;
    }else if(this.projekcijeTip == 0){
      this.predFilms = this.filmovi;
    }else{
      this.predFilms = [];
    }
  }

  promeniPrihod = function(){

    if(this.prihodiTip == 0){
      this.pozBiosPr = this.pozorista;
    }else if(this.prihodiTip == 1){
      this.pozBiosPr = this.bioskopi;
    }else{
      this.pozBios = [];
    }

  }

  promeniIzvestaj = function(){
    
    if(this.poslovanjeTip == 0){
      this.poslovanjePB = this.pozorista;
    }else if(this.poslovanjeTip == 1){
      this.poslovanjePB = this.bioskopi;
    }else{
      this.poslovanjePB = [];
    }
  }

  prikaziAmbijent = function(){
    if(this.ambijentTip != undefined && this.ambijentZa != undefined){
      this.http.get('/app/ocenaAmbijenta/'+this.ambijentZa).subscribe(res => {
        console.log(res);
        if(res['_body'] != ''){
          this.ocenaA = Math.round(res.json() * 100) / 100;
        }else{
          alert(res.header.get("message"));
        }
      })
    }
  }

  prikaziProjekcije = function(){
    
    if(this.projekcijeTip != undefined && this.projekcijeZa != undefined){
      this.http.get('/app/ocenaProjekcije/'+this.projekcijeZa).subscribe(res => {
        console.log(res);
        if(res['_body'] != ''){
          this.ocenaP = Math.round(res.json() * 100) / 100;
        }else{
          alert(res.header.get("message"));
        }
      })
    }
  }

  prikaziPrihode = function(){

    let reqBody = {
      idPozBio : this.prihodiZa,
      datumOd : ''+this.izvestajDatum2,
      datumDo : ''+this.izvestajDatum3,
      mode : -1
    }

    if(this.prihodiTip != undefined && this.prihodiZa != undefined && this.izvestajDatum2 != undefined && this.izvestajDatum3 != undefined){
      console.log(reqBody);
     
      this.http.post('/app/secured/prihod', reqBody, this.rks.postaviHeadere()).subscribe(res => {
        if(res['_body'] != ''){
          this.prihod = res.json();
        }
      })
    }
  }

  prikaziIzvestaj = function(){

    console.log('tip: '+this.izvestajTip)
    console.log('za: '+this.poslovanjeZa)
    console.log('datum: '+this.izvestajDatum1)

    let reqBody = {
      idPozBio : this.poslovanjeZa,
      datumOd : ''+this.izvestajDatum1,
      datumDo : '',
      mode : this.izvestajTip
    }

    if(this.izvestajTip != undefined && this.poslovanjeZa != undefined && this.izvestajDatum1 != undefined){
      console.log(reqBody);

      this.http.post('/app/secured/dijagram', reqBody, this.rks.postaviHeadere()).subscribe(res => {
        console.log('PROSLO!');
  
        this.yOsa = res.json();
        this.xOsa = [];

        for(let i = 0; i < this.yOsa.length; i++){
          this.xOsa[i] = ''+(i+1);
        }
        
        console.log(this.xOsa)
        console.log(this.yOsa)
        this.napraviDijagram()
      })
    }

  }

  napraviDijagram = function(){

    var canvas = <HTMLCanvasElement> document.getElementById("izvestaji");
    var ctx = canvas.getContext("2d");


    this.chart = new Chart(ctx, {
      type : 'line',
      data: {
        //po x-osi
        labels : this.xOsa,
        datasets : [
          {
            //konkretna posecenost
            data: this.yOsa,
            borderColor : '#003300',
            fill: false
          }
        ]
      },
      options : {
        legend : {
          display: false
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }]
        }
      }
    });
  }

}

