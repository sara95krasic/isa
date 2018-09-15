import { Component, OnInit, Input } from '@angular/core';
import {FormControl, FormGroup, FormArray, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';


@Component({
  selector: 'app-dodaj-izmeni-projekciju',
  templateUrl: './dodaj-izmeni-projekciju.component.html',
  styleUrls: ['./dodaj-izmeni-projekciju.component.css']
})
export class DodajIzmeniProjekcijuComponent implements OnInit {

  @Input() pozBio: any;

  private projekcijaForma: any;
  private sale: any[];
  private predFilmovi: any[];
  private projType: number;

  constructor(private http: Http, private activatedRoute: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    this.projekcijaForma = new FormGroup({
      predFilm : new FormControl("",Validators.compose([
        Validators.required
      ])),
      sala : new FormControl("",Validators.compose([
        Validators.required
      ])),
      datum : new FormControl("",Validators.compose([
        Validators.required
      ]))
    })

    this.http.get("/app/vratiSale/"+this.pozBio.id).subscribe(res => {
      if(res['_body'] != ""){
        this.sale = res.json();
        console.log(this.sale)
      }else{
        alert(res.headers.get("message"));
      }
    })

    if(this.pozBio.tip === 'BIO'){
      this.projType = 0;
    }else if(this.pozBio.tip === 'POZ'){
      this.projType = 1;
    }else{
      this.projType = -1;
    }

    console.log(this.projType)

    this.http.get("/app/vratiSvePredFilmove/"+this.projType).subscribe(res => {
      if(res['_body'] != ""){
        this.predFilmovi = res.json();
        console.log(this.predFilmovi)
      }else{
        alert(res.headers.get("message"));
      }
    })
  

  }

  potvrdi = function(projekcija){
    console.log(projekcija.datum);

    this.http.post("/app/secured/sacuvajProjekciju?idSale="+projekcija.sala+"&idPredFilm="+projekcija.predFilm+"&datum="+projekcija.datum, null, this.pks.postaviHeadere()).subscribe(res => {
        if(res['_body'] != ""){
          console.log(res.json());
          window.location.reload();
        }else{
          alert(res.headers.get('message'))
        }
    })
  }
}
