import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { AgmCoreModule } from '@agm/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-pregled-skala',
  templateUrl: './pregled-skala.component.html',
  styleUrls: ['./pregled-skala.component.css']
})
export class PregledSkalaComponent implements OnInit {

  private skale : any;

  constructor(private oglasService : OglasiService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.oglasService.dobaviOglase('/app/secured/vratiskale').subscribe((data) => {;
      console.log(data)
      if(data['_body'] != ""){
        this.skale = data;
        console.log(this.skale);       
      }else{
        console.log('Greska pri dobavljanju skala')
      }
    });

  }

  obrisi(value) {

    this.oglasService.obrisiOglas('/app/secured/obrisiSkalu/' + value).subscribe((data) => {;
      console.log(data)
      if(data['_body'] != ""){

        this.oglasService.dobaviOglase('/app/secured/vratiskale').subscribe((data) => {;
          console.log(data)
          if(data['_body'] != ""){
            this.skale = data;
            console.log(this.skale);       
          }else{
            console.log('Greska pri ponovnom dobavljanju skala')
          }
        });

      }else{
        console.log('Greska pri brisanju rekvizita')
      }
    });


  }


  izmeni(value) {
    this.router.navigate(['izmeniSkalu/'+value]);
  }

}
