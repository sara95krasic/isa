import { Component, OnInit } from '@angular/core';
import { AlertService } from '../../services/alert.service'
import { Router } from '@angular/router';
import { RegisterService } from '../../services/register.service';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  prijavaForma;
  korisnik : any;
  message : string;

  constructor(private router : Router, private registerService : RegisterService, private alertService: AlertService) { }

  ngOnInit() {
    this.prijavaForma = new FormGroup({
      email : new FormControl(""),
      lozinka : new FormControl("")
    })
  }

  prijava = function(korisnik){
    this.registerService.registrujKorisnika('/app/login', korisnik).subscribe(res=>{

        try{
          this.message = res.headers.get('message');
          if(this.message == "Promeni"){
            this.korisnik = res.text();
            localStorage.setItem('logovanKorisnik',this.korisnik);
            AppComponent.updateUserStatus.next(true);
            this.router.navigate(['/promeniLozinku']); 
          }else {
            this.korisnik = res.text();
            localStorage.setItem('logovanKorisnik',this.korisnik);
            AppComponent.updateUserStatus.next(true);
            this.router.navigate(['']);
          }
          
        }catch(error){
          this.message = res.headers.get('message');
          this.alertService.error(this.message);
        }
    });
  }

  idiNaRegistraciju(){
    this.router.navigate(['/register']);
  }

}

