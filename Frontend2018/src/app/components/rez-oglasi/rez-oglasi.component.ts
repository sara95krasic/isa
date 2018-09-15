import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { AgmCoreModule } from '@agm/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-rez-oglasi',
  templateUrl: './rez-oglasi.component.html',
  styleUrls: ['./rez-oglasi.component.css']
})
export class RezOglasiComponent implements OnInit {

  private oglasi : any;


  constructor(private oglasService : OglasiService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.oglasService.dobaviOglase('/app/secured/vratiRezervisaneOglase').subscribe((data) => {;
      console.log(data)
      if(data['_body'] != ""){
        this.oglasi = data;
        console.log(this.oglasi);       
      }else{
        console.log('Greska pri dobavljanju rekvizita')
      }
    });

  }

  pogledaj(value) {


  }

  obrisi(value) {

    this.oglasService.obrisiOglas('/app/secured/obrisiRezervisaniOglas/' + value).subscribe((data) => {;
      console.log(data)
      if(data['_body'] != ""){

        this.oglasService.dobaviOglase('/app/secured/vratiRezervisaneOglase').subscribe((data) => {;
          console.log(data)
          if(data['_body'] != ""){
            this.oglasi = data;
            console.log(this.oglasi);       
          }else{
            console.log('Greska pri ponovnom dobavljanju rekvizita')
          }
        });

      }else{
        console.log('Greska pri brisanju rekvizita')
      }
    });

  }

}
