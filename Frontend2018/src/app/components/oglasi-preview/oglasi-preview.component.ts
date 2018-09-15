import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { PonudaService } from '../../services/ponuda.service';
import { AgmCoreModule } from '@agm/core';

@Component({
  selector: 'app-oglasi-preview',
  templateUrl: './oglasi-preview.component.html',
  styleUrls: ['./oglasi-preview.component.css']
})
export class OglasiPreviewComponent implements OnInit {

  private oglas: any;
  private id : any;
  private slika : any;
  private uloga : any;
  private korisnikToken : string;
  private logovanKorisnik : any;
  private ponude : any;
  private tudjiOglas : any;

  constructor(private http:Http,  private ponudeService : PonudaService, private route: ActivatedRoute, private router: Router, private oglasiService : OglasiService) { }

  ngOnInit() {

    this.oglas = {};

    this.korisnikToken = localStorage.getItem('logovanKorisnik');
    this.logovanKorisnik = JSON.parse(window.atob(this.korisnikToken.split('.')[1]));
    this.uloga = this.logovanKorisnik.uloga[0].authority;

    console.log(this.logovanKorisnik);

    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    
   this.oglasiService.dobaviOglase('/app/secured/oglas/'+this.id).subscribe((data) => {
      
      if(data['_body'] != ""){
        this.oglas = data;
        console.log(this.oglas);       
      }else{
        console.log('Greska pri dobavljanju oglasa')
        this.router.navigate(['/']);
      }
   });
   
 
    this.ponudeService.dobaviPonudu('/app/secured/vratiPonude/'+this.id).subscribe((data) => {
        
      if(data['_body'] != ""){
        this.ponude = data;
        console.log(this.ponude);   
      }else{
        console.log('Greska pri dobavljanju ponuda')
        this.router.navigate(['/']);
      }
    });

  


  }

  odbaciOglas(value) {
    this.oglasiService.obrisiOglas('/app/secured/obrisiOglas/'+value).subscribe((res) => {
      
      if(res['_body'] != ""){
        this.router.navigate(['/odobriOglase/stranica/1']);
      }else{
        console.log("Greska pro odbacivanju oglasa")
      }
    });

  }


  odobriOglas(value) {
    this.oglasiService.izmeniOglas('/app/secured/odobriOglas', this.oglas).subscribe((res) => {
      
      if(res['_body'] != ""){
        this.router.navigate(['/odobriOglase/stranica/1']);
      }else{
        console.log("Greska pro odbacivanju oglasa")
      }
    });

  }

  IzmeniOglas(value) {
    this.router.navigate(['/izmeniOglas/'+ value]);
  }

  prihvati(value) {
    
  }

}
