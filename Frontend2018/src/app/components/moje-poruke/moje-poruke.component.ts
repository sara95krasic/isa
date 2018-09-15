import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PorukeService } from '../../services/poruke.service';
import { AgmCoreModule } from '@agm/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-moje-poruke',
  templateUrl: './moje-poruke.component.html',
  styleUrls: ['./moje-poruke.component.css']
})
export class MojePorukeComponent implements OnInit {

  private poruke : any;
  stranica : number;

  constructor(private porukeService : PorukeService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    if(this.stranica == undefined || this.stranica <= 0){
      this.stranica = 1;
      this.router.navigate(['/mojePoruke/stranica/'+this.stranica]);
    }



    this.porukeService.dobaviPoruke('/app/secured/vratiLicnePoruke/' + this.stranica).subscribe((data) => {;
      if(data['_body'] != ""){
        this.poruke = data.content;
        console.log(this.poruke);       
      }else{
        console.log('Greska pri dobavljanju poruka')
        //this.router.navigate(['/']);
      }
    });

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
    
    this.vratiSadrzaj('/app/secured/vratiLicnePoruke/');
  }

  vratiSadrzaj(putanja:string){
    this.porukeService.dobaviPoruke(putanja+this.stranica).subscribe((data) => {
      this.poruke = data.content;
   
      if(this.poruke.length <= 0){
        this.stranica--;
        alert("Dosli ste do kraja pretrage");
        this.ngOnInit();
        return;
      }
      this.router.navigate(['/mojePoruke/stranica/'+this.stranica]);
      
    });
  }

  obrisi(value) {

    this.porukeService.obrisiPoruku('/app/secured/obrisiLicnuPoruku/' + value).subscribe((data) => {;
      
      console.log(data)
      if(data['_body'] != ""){
        
        this.porukeService.dobaviPoruke('/app/secured/vratiLicnePoruke/' + this.stranica).subscribe((data) => {;
          if(data['_body'] != ""){
            this.poruke = data.content;
            console.log(this.poruke);       
          }else{
            console.log('Greska pri dobavljanju poruka')
          }
        });
              
      }else{
        console.log('Greska pri dobavljanju poruka')
        
      }
    });


  }


}
