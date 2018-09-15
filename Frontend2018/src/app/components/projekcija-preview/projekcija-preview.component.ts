import { Component, OnInit, Input } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-projekcija-preview',
  templateUrl: './projekcija-preview.component.html',
  styleUrls: ['./projekcija-preview.component.css']
})
export class ProjekcijaPreviewComponent implements OnInit {

  @Input() pozBio;
  private datumProjekcija: any;
  private projekcije: any[] = [];
  private startAt;

  private isBrzaRezervacija: boolean;
  private zaBrzu: any;
  private uloga: any[];

  constructor(private http: Http, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    let korisnikToken = localStorage.getItem('logovanKorisnik');
    if(korisnikToken){
      let logovanKorisnik = JSON.parse(window.atob(korisnikToken.split('.')[1]));
      this.uloga = logovanKorisnik.uloga[0].authority;
    }

    this.startAt = new Date(Date.now());
    console.log(this.startAt)
    this.datumProjekcija = this.startAt;
    this.isBrzaRezervacija = false;
  }

  preuzmiProjekcije = function(){

    if(new Date(this.datumProjekcija) >= this.startAt){
      this.http.post("/app/vratiProjekcijePoDanu?idPozBio="+this.pozBio.id+"&datum="+this.datumProjekcija).subscribe(res => {
        if(res['_body'] != ""){
          this.projekcije = res.json();
        }else{
          alert(res.headers.get('message'))
        }
      })
    }else{
      alert('Mozete pregledati samo projekcije koje slede.')
    }

  }

  konvertujDatume = function(dateInMili: number){
    var date = new Date(dateInMili);
    return (date.toString()).substr(3, 18);
  }

  pogledajPredFilm = function(val: number){
    console.log(val)
    this.router.navigate(['predFilm/'+val]);
  }

  brzaRezervacija = function(val: any){
    this.isBrzaRezervacija = !this.isBrzaRezervacija;
    this.zaBrzu = val;
  }

  rezervisiProjekciju = function(idProj){
    this.router.navigate(['rezervisi/'+idProj]);
  }

  izbrisi = function(val: any){
      if(val != undefined){
      this.http.delete("/app/secured/obrisiProj/"+val.id, this.pks.postaviHeadere()).subscribe(res => {
        if(res['_body'] != ""){
          alert('Uspesno brisanje.')
          window.location.reload();
        }else{
          alert('Neuspesno brisanje.')
        }
      })
    }
  }

}
