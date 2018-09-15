import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PozBioService } from '../../services/poz-bio.service';

@Component({
  selector: 'app-film-pred-preview',
  templateUrl: './film-pred-preview.component.html',
  styleUrls: ['./film-pred-preview.component.css']
})
export class FilmPredPreviewComponent implements OnInit {

  private predFilm: any;
  private idPredFilm: number;
  private projekcije: any[];
  private ocena: number;

  private stranica: number;

  constructor(private http:Http, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.predFilm = {};
    this.stranica = 1;

    this.route.params.subscribe(params => {
      this.idPredFilm = +params['id'];
    });

    this.http.get('/app/vratiPredFilm/'+this.idPredFilm).subscribe((res1) => {
      
      if(res1['_body'] != ""){
        this.predFilm = res1.json();
      }else{
        this.router.navigate(['']);
      }
    });

    this.http.get('/app/ocenaProjekcije/'+this.idPredFilm).subscribe((res1) => {
      
      if(res1['_body'] != ""){
        this.ocena = Math.round(res1.json() * 100) / 100;
      }else{
        this.router.navigate(['']);
      }
    });

    this.http.get('/app/vratiProjekcijePredFilm/'+this.idPredFilm+"?stranica="+this.stranica).subscribe(res => {
      if(res['_body'] != ""){
        let temp = res.json();
        this.projekcije = temp.content;
        console.log(this.projekcije);
        //window.location.reload();
      }else{
        alert(res.headers.get("message"));
      }

    });

  }

  konvertujDatume = function(dateInMili: number){
    var date = new Date(dateInMili);
    return (date.toString()).substr(3, 18);
  }

  prev(){
    this.stranica--;

    if(this.stranica < 1){
      this.stranica = 1;
    }

    this.http.get("/app/vratiProjekcijePredFilm/"+this.idPredFilm+"?stranica="+this.stranica).subscribe(res => {
        if(res['_body'] != ''){
          let temp = res.json();
          this.projekcije = temp.content;
        }else{
          alert(res.headers.get("message"));
        }
    });
    
  }

  next(){
    this.stranica++;
    
    this.http.get("/app/vratiProjekcijePredFilm/"+this.idPredFilm+"?stranica="+this.stranica).subscribe(res => {
      if(res['_body'] != ''){
        let temp = res.json();
        this.projekcije = temp.content;
      }else{
        alert(res.headers.get("message"));
      }
    });
  }

  izmeniPredFilm = function(){
    this.router.navigate(['izmeniPredFilm/'+this.idPredFilm]);
  }

}
