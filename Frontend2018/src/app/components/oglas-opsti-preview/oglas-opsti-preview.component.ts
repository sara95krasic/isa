import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { PonudaService } from '../../services/ponuda.service';
import { AgmCoreModule } from '@agm/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-oglas-opsti-preview',
  templateUrl: './oglas-opsti-preview.component.html',
  styleUrls: ['./oglas-opsti-preview.component.css']
})
export class OglasOpstiPreviewComponent implements OnInit {

  private oglas: any;
  private id : any;
  private slika : any;
  private uloga : any;
  private korisnikToken : string;
  private logovanKorisnik : any;

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

  }

  Rezervisi(value) {

    
      this.oglasiService.rezervisiOglas('/app/secured/rezervisiOglas/'+ value).subscribe((data) => {
          
        console.log(data)
  
        if(data['_body'] != ""){
          console.log("Rezervisan oglas!")
        }else{
          console.log('Greska pri rezervaciji oglasa')
          this.router.navigate(['/']);
        }
      });
  


  }


}
