import { Component, OnInit, Input } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-brza-rezervacija',
  templateUrl: './brza-rezervacija.component.html',
  styleUrls: ['./brza-rezervacija.component.css']
})
export class BrzaRezervacijaComponent implements OnInit {

  @Input() projekcija;

  private sedista: any[];
  private segmenti: any[];

  private segZaBrzu: any;

  constructor(private http:Http, private route: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    console.log(this.projekcija);
    console.log(this.projekcija.sala.id)
    if(this.projekcija != undefined){
      this.http.get("app/vratiSegmenteSala/"+this.projekcija.sala.id).subscribe(res => {
        if(res['_body'] != ''){
          this.segmenti = res.json();
          console.log(this.segmenti)
        }
      })
    }

    
  }

  konvertujDatume = function(dateInMili: number){
    var date = new Date(dateInMili);
    return (date.toString()).substr(3, 18);
  }

  ucitajSedista = function(){
    console.log(this.segZaBrzu)
    if(this.segZaBrzu != undefined){
      this.http.get("app/secured/pripremiZaBrzu/"+this.segZaBrzu, this.pks.postaviHeadere()).subscribe(res => {
        if(res["_body"] != ""){
          this.sedista = res.json();

          for(let i = 0; i < this.sedista.length; i++){
            this.sedista[i].isChecked = false;
          }
        }else{
          alert(res.headers.get("message"))
        }
      })
   }
  }

  potvrdi = function(){

    let sedistaZaBrzu = [];

    for(let i = 0; i < this.sedista.length; i++){
      if(this.sedista[i].isChecked){
        sedistaZaBrzu[sedistaZaBrzu.length] = this.sedista[i].sediste.id;
      }
    }

    if(sedistaZaBrzu.length > 0){
      this.http.post("app/secured/formirajBrzu/"+this.projekcija.id, sedistaZaBrzu, this.pks.postaviHeadere()).subscribe(res => {
        if(res["_body"] != ""){
          window.location.reload();
        }else{
          alert(res.headers.get("message"))
        }
      })
    }
  }

  

}
