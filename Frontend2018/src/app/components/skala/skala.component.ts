import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { AlertService } from '../../services/alert.service'

@Component({
  selector: 'app-skala',
  templateUrl: './skala.component.html',
  styleUrls: ['./skala.component.css']
})
export class SkalaComponent implements OnInit {

  skalaForma;
  message : string;
  success : boolean;

  constructor(private router : Router, private oglasiService : OglasiService, private alertService : AlertService) { }

  ngOnInit() {

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

  }

  posalji(value) {

    console.log(value)
  
    this.oglasiService.posaljiOglas('/app/secured/sacuvajSkalu',value).subscribe((res) => {
      if(res['_body'] != ""){
        this.router.navigate(['']);
      }else{
        alert(res.headers.get('message'))
      }
    });
    
  }

  

}
