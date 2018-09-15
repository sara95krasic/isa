import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { RegisterService } from '../../services/register.service';
import { AlertService } from '../../services/alert.service'

@Component({
  selector: 'app-izmeni-admin',
  templateUrl: './izmeni-admin.component.html',
  styleUrls: ['./izmeni-admin.component.css']
})
export class IzmeniAdminComponent implements OnInit {

  izmeniForma;
  admini : any;
  message : string;
  success : boolean;
  id : any;
  tip : any;

  constructor(private route: ActivatedRoute, private router : Router, private registerService : RegisterService, private alertService : AlertService) { }

  ngOnInit() {

    this.izmeniForma = new FormGroup({
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

    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    
   this.registerService.dobaviKorisnika('/app/admin/edit/'+this.id).subscribe((data) => {
      
      if(data['_body'] != ""){
        this.admini = data;
        console.log(this.admini)
        this.tip = this.admini.tip
        this.izmeniForma.patchValue({email: this.admini.email});
        this.izmeniForma.patchValue({lozinka: this.admini.lozinka});
        this.izmeniForma.patchValue({sifraPotvrda:  this.admini.sifraPotvrda});
        this.izmeniForma.patchValue({ime:  this.admini.ime});
        this.izmeniForma.patchValue({prezime:  this.admini.prezime});
        this.izmeniForma.patchValue({grad:  this.admini.grad});
        this.izmeniForma.patchValue({telefon:  this.admini.telefon});

      }else{
        console.log('Greska pri dobavljanju admina')
        this.router.navigate(['/']);
      }
   });
  
  
  
  }



  passwordMatchValidator = function(g: FormGroup) {

    return g.get('lozinka').value === g.get('sifraPotvrda').value ? null : {'missmatch': true};

  }

  registruj = function(korisnik){

  korisnik.id = +this.id;

  this.registerService.izmeniKorisnika('/app/sacuvajIzmenjenogAdmina/'+this.tip, korisnik).subscribe((res) => {
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
