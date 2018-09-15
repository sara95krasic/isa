import { Component, OnInit } from '@angular/core';
import { OglasiService } from '../../services/oglasi.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-odobri-oglase',
  templateUrl: './odobri-oglase.component.html',
  styleUrls: ['./odobri-oglase.component.css']
})
export class OdobriOglaseComponent implements OnInit {

  stranica : number;
  oglasi : any;

  constructor(private oglasiService : OglasiService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {


    if(this.stranica == undefined || this.stranica <= 0){
      this.stranica = 1;
      this.router.navigate(['/odobriOglase/stranica/'+this.stranica]);
    }
    
    this.oglasiService.dobaviOglase('/app/secured/odobriOglase/'+this.stranica).subscribe((data) => {
        this.oglasi = data.content;
        console.log(this.oglasi);

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
    
    this.vratiSadrzaj('/app/secured/odobriOglase/');
  }

  vratiSadrzaj(putanja:string){
    this.oglasiService.dobaviOglase(putanja+this.stranica).subscribe((data) => {
      this.oglasi = data.content;
   
      if(this.oglasi.length <= 0){
        this.stranica--;
        alert("Dosli ste do kraja pretrage");
        this.ngOnInit();
        return;
      }
      this.router.navigate(['/odobriOglase/stranica/'+this.stranica]);
      
    });
  }
    

  Pogledaj(value) {
      this.stranica = 1;
      this.router.navigate(['/pregledajOglas/',  value]);

  }
  

}
