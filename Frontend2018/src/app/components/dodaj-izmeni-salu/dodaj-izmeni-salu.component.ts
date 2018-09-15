import { Component, OnInit, Input } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';

@Component({
  selector: 'app-dodaj-izmeni-salu',
  templateUrl: './dodaj-izmeni-salu.component.html',
  styleUrls: ['./dodaj-izmeni-salu.component.css']
})
export class DodajIzmeniSaluComponent implements OnInit {

  private salaForma: any;
  private sala:any;
  private naslov: string = '';

  // 0 => dodavanje, 1 => izmena
  @Input() mode: number;
  @Input() idPozBio:number;
  @Input() idSala:number;

  constructor(private http:Http, private route: ActivatedRoute, private router: Router, private pks: PrijavljenKorisnikService) { }

  ngOnInit() {

    this.salaForma = new FormGroup({
      naziv : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(90)
      ]))
    })

    if(this.mode == 1){

      this.naslov = 'Izmenite salu';

      this.http.get('/app/vratiJednuSalu/'+this.idSala).subscribe((res) => {

        if(res['_body'] != ""){
          this.sala = res.json();
          this.salaForma.patchValue({naziv: this.sala.naziv});
        }else{
          alert(res.headers.get('message'))
        }
    
      });

    }else if(this.mode == 0){

      this.naslov = 'Dodajte novu salu';

    }else{
      this.router.navigate(['']);
    }

    console.log('MODE: '+this.mode)
    console.log('Id sale: '+this.idSala)
    console.log('Id pozBio: '+this.idPozBio)

  }

  potvrdi = function(sala:any){

    console.log(sala)
    console.log(this.sala)
    
      if(this.mode == 0){

        this.http.post('/app/secured/dodajSalu/'+this.idPozBio, sala, this.pks.postaviHeadere()).subscribe((res) => {   

          if(res['_body'] != ""){
            window.location.reload();
          }else{
            alert(res.headers.get('message'))
          }
      
        });

      }else if(this.mode == 1){
        this.sala.naziv = sala.naziv;
        this.http.put('/app/secured/izmeniSalu/'+this.idSala, this.sala, this.pks.postaviHeadere()).subscribe((res) => {   

          if(res['_body'] != ""){
            window.location.reload();
          }else{
            alert(res.headers.get('message'))
          }
      
        });
      }else{
        alert('Greska! Nedozvoljen mod!');
      }
    

  }
}

