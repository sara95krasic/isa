import { Component, OnInit, Input } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-brza-rezervacija-rezervisi',
  templateUrl: './brza-rezervacija-rezervisi.component.html',
  styleUrls: ['./brza-rezervacija-rezervisi.component.css']
})
export class BrzaRezervacijaRezervisiComponent implements OnInit {

  @Input() pozBio: any;

  private karte: any[];
  private zaRez: any;

  constructor(private http:Http, private route: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService ) { }

  ngOnInit() {

    let today = new Date(Date.now());
    this.http.get("/app/secured/vratiBrzu/"+this.pozBio.id+"?datum="+today, this.pks.postaviHeadere()).subscribe(res => {
      if(res['_body'] != ''){
        this.karte = res.json();
      }else{
        console.log('Ne postoje karte za brzu rezervaciju, pokusajte kasnije.')
      }
    })
  }

  konvertujDatume = function(dateInMili: number){
    var date = new Date(dateInMili);
    return (date.toString()).substr(3, 18);
  }

  potvrdi = function(){
    this.http.get("/app/secured/rezervisiBrzo/"+this.zaRez, this.pks.postaviHeadere()).subscribe(res => {
      if(res['_body'] != ""){
        alert('Uspesna rezervacija!')
      }else{
        alert('Greska!')
      }
    })

  }

}
