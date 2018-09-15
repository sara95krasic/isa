import { Component, OnInit, Input } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-dodaj-izmeni-segment',
  templateUrl: './dodaj-izmeni-segment.component.html',
  styleUrls: ['./dodaj-izmeni-segment.component.css']
})
export class DodajIzmeniSegmentComponent implements OnInit {

  // 0 => dodavanje, 1 => izmena
  @Input() mode: number;
  @Input() idSala:number;
  @Input() idSegment:number;

  private segmentForma: any;
  private segment:any;
  private naslov: string = '';
  private tipovi: any[];

  constructor(private http: Http, private activatedRoute: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService ) { }

  ngOnInit() {

    this.segmentForma = new FormGroup({
      brojSedista : new FormControl("",Validators.compose([
        Validators.pattern('[0-9]+'),
        Validators.required
      ])),
      tip : new FormControl("",Validators.compose([
        Validators.required
      ]))
    })

    this.http.get("/app/vratiTipoveSegmenata").subscribe((res) => {
      if(res['_body'] != ''){
        this.tipovi = res.json();
        console.log(res)
      }
      
    })

    if(this.mode == 0){
      this.naslov = 'Dodajte novi segment';
    }else if(this.mode == 1){
      this.naslov = 'Izmenite segment';
    }else{
      alert('Greska');
      this.router.navigate(['']);
    }

  }

  potvrdi = function(noviSegment){

    console.log(noviSegment)

    this.http.post("/app/secured/sacuvajSegment/"+this.idSala+"/"+noviSegment.tip+"?brojSedista="+noviSegment.brojSedista, noviSegment, this.pks.postaviHeadere()).subscribe((res) => {
      if(res['_body'] != ''){
        let res1 = res.json();
        console.log(res1)
        window.location.reload();
      }else{
        alert("Greska!"+res.headers.get("Message"));
      }
    })
    

  }

}
