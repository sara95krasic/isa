import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PonudaService } from '../../services/ponuda.service';
import { AgmCoreModule } from '@agm/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';

@Component({
  selector: 'app-izmeni-ponudu',
  templateUrl: './izmeni-ponudu.component.html',
  styleUrls: ['./izmeni-ponudu.component.css']
})
export class IzmeniPonuduComponent implements OnInit {

  private izmeniPonuduForma : any;
  private ponudaId : any;
  private oglasId : any;
  private ponuda : any;

  constructor(private ponudaService : PonudaService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.ponudaId = +params['id'];
      this.oglasId = +params['idOglas'];

    });

    this.izmeniPonuduForma = new FormGroup({
      iznos : new FormControl("",Validators.compose([
        Validators.required,
        Validators.pattern('[0-9]{1,10}'),
        Validators.maxLength(10)
      ]))
    })

    this.ponudaService.dobaviPonudu('/app/secured/vratiJednuPonudu/' + this.ponudaId).subscribe((res) => {
      
      if(res['_body'] != ""){
        this.ponuda = res;
        console.log(this.ponuda)
        this.izmeniPonuduForma.patchValue({iznos: this.ponuda.iznos});
      }else{
        console.log("Greska pro slanju ponude")
      }
    });

  }


  posalji(value) {

    console.log(value);
    this.ponudaService.posaljiPonudu('/app/secured/sacuvajIzmenjenuPonudu/'+ this.oglasId + '/'+this.ponudaId , value).subscribe((res) => {

      if(res['_body'] != ""){
        this.router.navigate(['/pregledajKorOglas/' + this.oglasId]);
      }else{
        alert(res.headers.get('message'))
      }

    });  
  }

}
