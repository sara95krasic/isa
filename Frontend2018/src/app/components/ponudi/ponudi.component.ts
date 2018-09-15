import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PonudaService } from '../../services/ponuda.service';
import { AgmCoreModule } from '@agm/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';

@Component({
  selector: 'app-ponudi',
  templateUrl: './ponudi.component.html',
  styleUrls: ['./ponudi.component.css']
})
export class PonudiComponent implements OnInit {

  private ponudiForma : any;
  private oglasId : any;

  constructor(private ponudaService : PonudaService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.oglasId = +params['id'];
    });

    this.ponudiForma = new FormGroup({
      iznos : new FormControl("",Validators.compose([
        Validators.required,
        Validators.pattern('[0-9]{1,10}'),
        Validators.maxLength(10)
      ]))
    })

  }
  posalji(value) {

    this.ponudaService.posaljiPonudu('/app/secured/dodajPonudu/' + this.oglasId, value).subscribe((res) => {
      
      if(res['_body'] != ""){
        this.router.navigate(['/pregledajKorOglas/' + this.oglasId]);
      }else{
        console.log("Greska pro slanju ponude")
      }
    });

  }


}
