import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PrijavljenKorisnikService } from '../../services/prijavljen-korisnik.service';
import { Router } from '@angular/router';
import { OuterSubscriber } from 'rxjs/OuterSubscriber';


@Component({
  selector: 'app-rezervacija-mesto',
  templateUrl: './rezervacija-mesto.component.html',
  styleUrls: ['./rezervacija-mesto.component.scss']
})
export class RezervacijaMestoComponent implements OnInit {

  @Input() projId;
  @Output() ucitavacSedista = new EventEmitter<number[]>();
  segmenti : any = null;
  sedista : any = null;
  cenaKarte : number = -1;
  brRedova : number;
  poslednjiRed : number;
  segmentId : number;
  @Input() checkedSedista : number[] = [];

  constructor(private prijavljenKorisnikService : PrijavljenKorisnikService, private router : Router) { }

  ngOnInit() {
    this.ucitajSegmente();
  }


  ucitajSegmente = function(){
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiSegmenteProj/'+this.projId).subscribe(res=>{
      if(res['_body']!=""){
        this.segmenti = res.json();
      }else{
        this.router.navigate(['']);
      }  
    });
  }

  ucitajSedista = function(segmentId){
      if(segmentId!=-1){
        for(let s of this.segmenti){
          if(s.id==segmentId){
            this.cenaKarte = s.tip.cena;
            break;
          }
        }
        this.segmentId = segmentId;
        this.vratiSedista(segmentId);
      }else{
        this.cenaKarte = -1;
      }
  }

  vratiSedista = function(segmentId){
    this.sedista = null;
    this.prijavljenKorisnikService.dobaviRegistrovanogKorisnika('/app/secured/vratiSedistaProj/proj='+this.projId+'&seg='+segmentId).subscribe(res=>{
      if(res['_body']!=""){       
        this.sedista = res.json();
        this.brRedova = Math.floor(this.sedista.length/10);
        this.poslednjiRed = this.sedista.length-(this.brRedova*10);
      }else{
        this.router.navigate(['']);
      }  
    });
  }

  counter(i: number) {
    return new Array(i);
  }

  izracunajId = function(i:number, j:number) : string{
    var id = "segment "+this.segmentId+" "+(i*10)+(j+1);
    return id;
  }

  izracunajIndex = function(i:number, j:number) : number{
    var id = (i*10)+j;
    return id;
  }

  izracunajLabelu = function(i:number, j:number) : string{

    var retVal = (j+1) + String.fromCharCode(65+i);

    return retVal;
  }

  cekiraj(element: number){
    const index : number = this.checkedSedista.indexOf(element);
    if(index==-1){
      this.checkedSedista.push(+element);
    }else{
      this.checkedSedista.splice(index,1);
    }
  }

  otkaci(element) : boolean{
    const index : number = this.checkedSedista.indexOf(element);
    if(index==-1){
      return false;
    }else{
      return true;
    }
  
  }

  birajPrijatelji = function(){
    this.ucitavacSedista.emit(this.checkedSedista);
  }

  odustaniMesta = function(){
    this.router.navigate('');
  }

}
