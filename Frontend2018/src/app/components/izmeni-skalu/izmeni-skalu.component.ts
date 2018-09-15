import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { AlertService } from '../../services/alert.service'
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-izmeni-skalu',
  templateUrl: './izmeni-skalu.component.html',
  styleUrls: ['./izmeni-skalu.component.css']
})
export class IzmeniSkaluComponent implements OnInit {

  skalaForma;
  private jednaSkala : any;
  message : string;
  success : boolean;
  skalaId : any;

  constructor(private route: ActivatedRoute, private router : Router, private oglasiService : OglasiService, private alertService : AlertService) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.skalaId = +params['id'];

    });

    this.skalaForma = new FormGroup({
      naziv : new FormControl("",Validators.compose([
        Validators.required,
        Validators.maxLength(30)
      ])),
      od : new FormControl("",Validators.compose([
        Validators.required,
        Validators.pattern('[0-9]{1,10}'),
        Validators.maxLength(10)
      ])),
      doo : new FormControl("",Validators.compose([
        Validators.required,
        Validators.pattern('[0-9]{1,10}'),
        Validators.maxLength(10)
      ]))
    })

    this.oglasiService.dobaviOglase('/app/secured/skala/' + this.skalaId).subscribe((res) => {
      if(res['_body'] != ""){
        this.jednaSkala = res;
        console.log(this.jednaSkala)
        this.skalaForma.patchValue({naziv: this.jednaSkala.naziv});
        this.skalaForma.patchValue({od: this.jednaSkala.od});
        this.skalaForma.patchValue({doo: this.jednaSkala.doo});
      }else{
        console.log("Greska pro dobijanju skale ponude")
      }
    });

  }

  posalji(value) {

    value.id = this.skalaId;
    console.log(value);
    this.oglasiService.izmeniOglas('/app/secured/sacuvajIzmenjeuSkalu', value).subscribe((res) => {

      if(res['_body'] != ""){
        this.router.navigate(['/skale/']);
      }else{
        alert(res.headers.get('message'))
      }

    });  
  }


}
