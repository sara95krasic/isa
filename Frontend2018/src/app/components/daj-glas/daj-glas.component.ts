import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {FormControl, FormGroup, FormArray, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-daj-glas',
  templateUrl: './daj-glas.component.html',
  styleUrls: ['./daj-glas.component.css']
})
export class DajGlasComponent implements OnInit {

  @Input()
  idProjekcija: number;

  @Input()
  mode: number;

  @Output()
  skloniMe: EventEmitter<boolean> = new EventEmitter<boolean>();
  

  private cega: string;
  private ocena: any;

  constructor(private http: Http, private activatedRoute: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    if(this.mode == 0){
      this.cega = 'ambijenta'
    }else if(this.mode == 1){
      this.cega = 'projekcije';
    }else{
      this.skloniMe.emit(false);
    }

  }

  potvrdi = function(){

    this.http.put("/app/secured/oceni/"+this.mode+"?idProjekcije="+this.idProjekcija+"&ocena="+this.ocena+"",null , this.pks.postaviHeadere()).subscribe(res => {
      if(res['_body'] == 'true'){
        console.log(res);
        this.skloniMe.emit(false);
        window.location.reload();
      }else{
        alert(res.headers.get('message'));
      }
    })

  } 

}
