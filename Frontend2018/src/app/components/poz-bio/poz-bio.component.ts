import { Component, OnInit } from '@angular/core';
import { PozBioService } from '../../services/poz-bio.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-poz-bio',
  templateUrl: './poz-bio.component.html',
  styleUrls: ['./poz-bio.component.css']
})
export class PozBioComponent implements OnInit {

  pozBios : any;
  stranica : number;
  tip : string;
  sortParam : string = '';
  pretrazi : boolean = false;
  pretragaText : string = '';
  pretragaZaSlanje : string = '';

  constructor(private pozBioService : PozBioService, private router: Router, private route: ActivatedRoute) { 
    
  }

  ngOnInit() {

    let niz = this.router.url.split('/');

    for(let i = 0; i < niz.length; i++){
      if(niz[i] === 'pozorista' || niz[i] === 'bioskopi'){
        this.tip = niz[i].substring(0, 3);
        break;
      }
    }

    if(this.tip == undefined){
      this.router.navigate(['']);
    }

    this.route.params.subscribe(params => {
      this.stranica = +params['id'];
    });

    if(this.stranica == undefined || this.stranica <= 0){
      this.stranica = 1;
      this.router.navigate(['/bioskopi/stranica/'+this.stranica]);
    }
    
    if(this.tip === 'bio'){
        this.pozBioService.vratiSadrzaj('/app/bioskopi/'+this.stranica).subscribe((data) => {
          this.pozBios = data.content;
        });
    }else{
      this.pozBioService.vratiSadrzaj('/app/pozorista/'+this.stranica).subscribe((data) => {
        this.pozBios = data.content;
      });
    }
    
  }

  prev(){
    this.stranica--;

    if(this.stranica >= 1){
      this.promeniStranicu(vratiSadrzaj => this.vratiSadrzaj);
      
    }else{
      this.stranica = 1;
    }
    
  }

  next(){
    this.stranica++;
    this.promeniStranicu(vratiSadrzaj => this.vratiSadrzaj);
  }

  promeniStranicu(vratiSadrzaj){
    
    if(this.tip === 'bio'){
      this.vratiSadrzaj('/app/bioskopi');
    }else if(this.tip === 'poz'){
      this.vratiSadrzaj('/app/pozorista');
    }else{
      this.router.navigate(['']);
    }
  }

  vratiSadrzaj(putanja:string){
    var url = '';
    if(!this.pretrazi){
      url = putanja+"/"+this.stranica;
    }else{
      url = putanja+"Pretraga/"+"stranica="+this.stranica+"&kriterijum="+this.pretragaZaSlanje;
    }
    this.pozBioService.vratiSadrzaj(url).subscribe((data) => {
      var lista = data.content;
      if(JSON.stringify(lista).toLowerCase() != JSON.stringify(this.pozBios).toLowerCase()){
        this.pozBios = lista;
      }else{
        this.stranica--;
      }
   
      if(this.tip === 'bio'){
        this.router.navigate(['/bioskopi/stranica/'+this.stranica]);
      }else if(this.tip === 'poz'){
        this.router.navigate(['/pozorista/stranica/'+this.stranica]);
      }
    });
  }

  pogledaj(param){
    this.stranica = 1;
    if(this.pozBioService.getTip() === 'bio'){
      this.router.navigate(['/bioskopi/bioskop', param]);
    }else{
      this.router.navigate(['/pozorista/pozoriste', param]);
    }
  }

  sortiraj = function(param){

    if(this.sortParam===''){
      this.sortParam = param;
    }else{
      this.sortParam = '';
    }

  }

  pretraga = function(){
    this.pretrazi = true;
    this.stranica = 1;
    this.pretragaZaSlanje = this.pretragaText;
    this.promeniStranicu(vratiSadrzaj => this.vratiSadrzaj);
  }

  osvezi = function(){
    this.pretrazi = false;
    this.stranica = 1;
    this.pretragaText = "";
    this.pretragaZaSlanje = "";
    this.promeniStranicu(vratiSadrzaj => this.vratiSadrzaj);
  }

}
