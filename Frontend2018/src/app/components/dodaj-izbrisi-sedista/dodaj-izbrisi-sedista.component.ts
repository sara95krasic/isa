import { Component, OnInit, Input } from '@angular/core';
import {FormControl, FormGroup, FormArray, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';
import { ProjekcijaPreviewComponent } from '../projekcija-preview/projekcija-preview.component';

@Component({
  selector: 'app-dodaj-izbrisi-sedista',
  templateUrl: './dodaj-izbrisi-sedista.component.html',
  styleUrls: ['./dodaj-izbrisi-sedista.component.css']
})
export class DodajIzbrisiSedistaComponent implements OnInit {

  // 0 => dodavanje, 1 => izmena
  private mode: number;
  private brojSedista: any;

  private sedista: any[];
  private zaBrisanje: any[];

  @Input() segment: any;

  constructor(private http: Http, private activatedRoute: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    this.brojSedista = new FormControl("",Validators.compose([
      Validators.pattern('[0-9]+'),
      Validators.required
    ]))

    this.http.get("app/vratiSedista/"+this.segment.id).subscribe(res => {
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

  potvrdiBrisanje = function(){
    console.log(this.sedista)

    let sedistaZaBrisanje = [];

    for(let i = 0; i < this.sedista.length; i++){
      if(this.sedista[i].isChecked){
        sedistaZaBrisanje[sedistaZaBrisanje.length] = this.sedista[i].id;
      }
    }

    console.log(sedistaZaBrisanje)

    this.http.post("app/secured/obrisiSedista", sedistaZaBrisanje, this.pks.postaviHeadere()).subscribe(res => {
      if(res["_body"] != ""){
        window.location.reload();
      }else{
        alert("Greska prilikom brisanja.")
      }
    })
  }

  potvrdiDodavanje = function(){
    
    this.http.post("app/secured/dodajSedista/"+this.segment.id+"?brojSedista="+this.brojSedista.value, this.pks.postaviHeadere()).subscribe(res => {
      if(res["_body"] != ""){
        window.location.reload();
      }else{
        alert(res.headers.get("message"))
      }
    })

  }

}
