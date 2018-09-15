import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../../services/register.service';
import { AlertService } from '../../services/alert.service'

@Component({
  selector: 'app-novi-adm-sis',
  templateUrl: './novi-adm-sis.component.html',
  styleUrls: ['./novi-adm-sis.component.css']
})
export class NoviAdmSisComponent implements OnInit {

  registracijaForma;
  message : string;
  success : boolean;


  constructor(private router : Router, private registerService : RegisterService, private alertService : AlertService) { }

  ngOnInit() {
    this.registracijaForma = new FormGroup({
      email : new FormControl("",Validators.compose([
        Validators.required,
        Validators.pattern('[a-zA-z0-9._]{0,64}@[a-z]{2,10}(\\.[a-z]{2,10})+'),
        Validators.maxLength(90)
      ])),
      lozinka : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(30)
      ])),
      sifraPotvrda : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(30)
      ])),
      ime : new FormControl("",Validators.compose([
        Validators.required,
        Validators.maxLength(30)
      ])),
      prezime : new FormControl("",Validators.compose([
        Validators.required,
        Validators.maxLength(30)
      ])),
      grad : new FormControl("",Validators.compose([
        Validators.required,
        Validators.maxLength(60)
      ])),
      telefon : new FormControl("",Validators.pattern('\\+?[0-9]{6,12}'))
    },this.passwordMatchValidator)
  }

  passwordMatchValidator = function(g: FormGroup) {

    return g.get('lozinka').value === g.get('sifraPotvrda').value ? null : {'missmatch': true};

  }

 registruj = function(korisnik){

  this.registerService.registrujKorisnika('/app/registracija/asis', korisnik).subscribe((res) => {
    this.success = res.json();
    this.message = res.headers.get('message');
    if(!this.success){
      this.alertService.error(this.message);
    }else{
      //this.router.navigate(['/uspesnaRegistracija']);
      this.router.navigate(['']);
    }
  });
}

}
