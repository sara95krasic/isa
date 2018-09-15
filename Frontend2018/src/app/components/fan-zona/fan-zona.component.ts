import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service'
import { Http } from '@angular/http';


@Component({
  selector: 'app-fan-zona',
  templateUrl: './fan-zona.component.html',
  styleUrls: ['./fan-zona.component.css']
})
export class FanZonaComponent implements OnInit {

  private tudjiOglasi : any;
  private mojiOglasi : any;
  private opstiOglasi : any;
  private stranica1 : any;
  private stranica2 : any;
  private uloga : any;
  private korisnikToken : string;
  private logovanKorisnik : any;
 

  constructor(private http : Http,private oglasiService : OglasiService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    
    this.korisnikToken = localStorage.getItem('logovanKorisnik');
    this.logovanKorisnik = JSON.parse(window.atob(this.korisnikToken.split('.')[1]));
    this.uloga = this.logovanKorisnik.uloga[0].authority;


    if(this.stranica1 == undefined || this.stranica1 <= 0){
      this.stranica1 = 1;
      this.stranica2 = 1;
      this.router.navigate(['/fanzona/stranica/'+this.stranica1]);
    }
    
    this.oglasiService.dobaviOglase('/app/secured/dobaviTudjeOglase/'+this.stranica1).subscribe((data) => {
        this.tudjiOglasi = data.content;
        console.log(this.tudjiOglasi);

    });

    this.oglasiService.dobaviOglase('/app/secured/dobaviLicneOglase/'+this.stranica1).subscribe((data) => {
      this.mojiOglasi = data.content;
      console.log(this.mojiOglasi);

    });

    this.oglasiService.dobaviOglase('/app/secured/dobaviOpsteOglase').subscribe((data) => {
      this.opstiOglasi = data;
      console.log(this.opstiOglasi);

    });
 
  }

  Obrisi(value){

    this.http.delete('/app/obrisiOglas/'+value).subscribe((res) => {
      
      if(res['_body'] != ""){
        this.router.navigate(['/fanzona/stranica/1']);
      }else{
        console.log("Greska pro brisanju oglasa")
      }
    });

      
  }

  Pogledaj(value) {
    this.stranica1 = 1;
    this.stranica2 = 1;
    this.router.navigate(['/pregledajKorOglas/',  value]);
  }

  PogledajSvoje(value) {
    this.stranica1 = 1;
    this.stranica2 = 1;
    this.router.navigate(['/pregledajSvojeOglase/',  value]);
  }

  PogledajOpsti(value) {
    this.router.navigate(['/pregledajOpsteOglase/',  value]);
  }


  
  prev1(){
    this.stranica1--;

    if(this.stranica1 >= 1){
      this.promeniStranicu1(vratiSadrzaj => this.vratiSadrzaj1);
      
    }else{
      this.stranica1 = 1;
    }
    
  }

  
  next1(){
    this.stranica1++;
    this.promeniStranicu1(vratiSadrzaj => this.vratiSadrzaj1);
  }

  promeniStranicu1(vratiSadrzaj){
    
    this.vratiSadrzaj1('/app/secured/dobaviTudjeOglase/');
  }

  
  vratiSadrzaj1(putanja:string){
    this.oglasiService.dobaviOglase(putanja+this.stranica1).subscribe((data) => {
      this.tudjiOglasi = data.content;
   
      if(this.tudjiOglasi.length <= 0){
        this.stranica1--;
        alert("Dosli ste do kraja pretrage");
        this.ngOnInit();
        return;
      }
      this.router.navigate(['/fanzona/stranica/'+this.stranica1]);
      
    });
  }
 

  prev2(){
    this.stranica2--;

    if(this.stranica2 >= 1){
      this.promeniStranicu2(vratiSadrzaj => this.vratiSadrzaj2);
      
    }else{
      this.stranica2 = 1;
    }
    
  }

  
  next2(){
    this.stranica2++;
    this.promeniStranicu2(vratiSadrzaj => this.vratiSadrzaj2);
  }

  promeniStranicu2(vratiSadrzaj){
    
    this.vratiSadrzaj2('/app/secured/dobaviLicneOglase/');
  }

  
  vratiSadrzaj2(putanja:string){
    this.oglasiService.dobaviOglase(putanja+this.stranica2).subscribe((data) => {
      this.mojiOglasi = data.content;
   
      if(this.mojiOglasi.length <= 0){
        this.stranica2--;
        alert("Dosli ste do kraja pretrage");
        this.ngOnInit();
        return;
      }
      this.router.navigate(['/fanzona/stranica/'+this.stranica2]);
      
    });
  }



}
