import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Http } from '@angular/http';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-tip-segmenta',
  templateUrl: './tip-segmenta.component.html',
  styleUrls: ['./tip-segmenta.component.css']
})
export class TipSegmentaComponent implements OnInit {

  private naslov: string;
  private tipForma: any;
  
  // 0 => dodavanje, 1 => izmena
  @Input() mode: number;

  constructor(private http: Http, private activatedRoute: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    this.tipForma = new FormGroup({
      naziv : new FormControl("",Validators.compose([
        Validators.required
      ])),
      cena : new FormControl("",Validators.compose([
        Validators.required
      ]))
    })

    if(this.mode == 0){
      this.naslov = 'Dodajte novi tip';
    }else if(this.mode == 1){
      this.naslov = 'Izmenite tip';
    }else{
      alert('Greska!')
      this.router.navigate(['']);
    }

  }

  potvrdi = function(noviTip){

    this.http.post("/app/secured/sacuvajTipSegmenta", noviTip, this.pks.postaviHeadere()).subscribe((res) => {
      if(res['_body'] != ''){
        let retVal = res.json();
        console.log(retVal)
        window.location.reload();
      }else{
        alert("Greska!"+res.headers.get("Message"));
      }
    })

  }

}
