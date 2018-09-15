import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-rezervacija-glavna',
  templateUrl: './rezervacija-glavna.component.html',
  styleUrls: ['./rezervacija-glavna.component.css']
})
export class RezervacijaGlavnaComponent implements OnInit {

  izborSedista : boolean = true;
  pozivPrijatelja : boolean = false;
  projekcijaId : number;
  izabranaSedista : number[] = [];

  constructor(private route : ActivatedRoute, private router : Router) { }

  ngOnInit() {

    var korisnikToken = localStorage.getItem('logovanKorisnik');
    if(korisnikToken==null){
      this.router.navigate(['']);
    }else{
      var korisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
      var uloga = korisnik.uloga[0].authority;
      if(uloga!=='RK'){
        this.router.navigate(['']);
      }else{
        this.route.params.subscribe(params => {
          this.projekcijaId = +params['id'];
        });
    
        if(this.projekcijaId == undefined || this.projekcijaId <= 0){
          this.router.navigate(['']);
        }
      }    
    }
  }

  izaberiSedista(sedista){
    this.izabranaSedista = sedista;
    this.izborSedista = false;
    this.pozivPrijatelja = true;
  }

  odustaniPrijatelji(){
    this.pozivPrijatelja = false;
    this.izborSedista = true;
  }

}
