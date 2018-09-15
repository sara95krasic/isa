import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { PonudaService } from '../../services/ponuda.service';
import { AgmCoreModule } from '@agm/core';
import { Location } from '@angular/common';


@Component({
  selector: 'app-oglas-kor-preview',
  templateUrl: './oglas-kor-preview.component.html',
  styleUrls: ['./oglas-kor-preview.component.css']
})
export class OglasKorPreviewComponent implements OnInit {

  private oglas: any;
  private id : any;
  private slika : any;
  private uloga : any;
  private korisnikToken : string;
  private logovanKorisnik : any;
  private mojePonude : any;
  private tudjePonude : any;
  private tudjiOglas : any;

  constructor(private location: Location,private http:Http,  private ponudeService : PonudaService, private route: ActivatedRoute, private router: Router, private oglasiService : OglasiService) { }

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
   
   this.ponudeService.dobaviPonudu('/app/secured/vratiMojePonude/'+this.id).subscribe((data) => {
        
      if(data['_body'] != ""){
        this.mojePonude = data;
        console.log(this.mojePonude);   
      }else{
        console.log('Greska pri dobavljanju mojih ponuda')
        this.router.navigate(['/']);
      }
    });


    this.ponudeService.dobaviPonudu('/app/secured/vratiTudjePonude/'+this.id).subscribe((data) => {
        
      if(data['_body'] != ""){
        this.tudjePonude = data;
        console.log(this.tudjePonude);   
      }else{
        console.log('Greska pri dobavljanju tudjih ponuda')
        this.router.navigate(['/']);
      }
    });
  }


  Ponudi(value) {
    this.router.navigate(['/ponudi/' + this.oglas.id]);
  }

  Izmeni(value) {
    this.router.navigate(['/izmeniPonudu/' + value+ '/' + this.id]);
  }


  Obrisi(value) {
    this.ponudeService.obrisiPonudu('/app/secured/obrisiPonudu/'+ value).subscribe((data) => {
        
      console.log(data)

      if(data['_body'] == ""){
        this.ponudeService.dobaviPonudu('/app/secured/vratiMojePonude/'+this.id).subscribe((data) => {
        
          if(data['_body'] != ""){
            this.mojePonude = data;
            console.log(this.mojePonude);   
          }else{
            console.log('Greska pri dobavljanju mojih ponuda')
            this.router.navigate(['/']);
          }
        });


      }else{
        console.log('Greska pri brisanju ponuda')
        this.router.navigate(['/']);
      }
    });

  }

}
