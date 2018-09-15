import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { PonudaService } from '../../services/ponuda.service';
import { AgmCoreModule } from '@agm/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-oglas-licni-preview',
  templateUrl: './oglas-licni-preview.component.html',
  styleUrls: ['./oglas-licni-preview.component.css']
})
export class OglasLicniPreviewComponent implements OnInit {

  private oglas: any;
  private id : any;
  private slika : any;
  private uloga : any;
  private korisnikToken : string;
  private logovanKorisnik : any;
  private ponude : any;


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
   
   this.ponudeService.dobaviPonudu('/app/secured/vratiPonude/'+this.id).subscribe((data) => {
        
      if(data['_body'] != ""){
        this.ponude = data;
        console.log(this.ponude);   
      }else{
        console.log('Greska pri dobavljanju mojih ponuda')
        this.router.navigate(['/']);
      }
    });

  }

  Prihvati(value) {

    console.log('Oglas id: ' + this.id + ', ponuda id: ' + value)
    this.ponudeService.prihvatiPonudu('/app/secured/prihvatiPonudu/'+this.id+'/'+value).subscribe((data) => {
        
      console.log(data)
      if(data['_body'] != ""){
        console.log("Ponuda uspesno prihvacenja");   
      }else{
        console.log('Greska pri prihvatanju ponude')
        this.router.navigate(['/']);
      }
    });


  }



}
