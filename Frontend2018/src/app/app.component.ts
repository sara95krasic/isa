import { Component } from '@angular/core';
import { PozBioService } from './services/poz-bio.service';
import { AdminServiceService } from './services/admin-service.service';
import { Router } from '@angular/router';
import { Subject } from 'rxjs/Subject';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  korisnikToken : string;
  logovanKorisnik : any;
  uloga : string;

  constructor(private pozBioService : PozBioService,private adminService : AdminServiceService, private router: Router) {
   
   }

  ngOnInit() {
    AppComponent.updateUserStatus.subscribe(res => {
      this.korisnikToken = localStorage.getItem('logovanKorisnik');
      this.logovanKorisnik = JSON.parse(window.atob(this.korisnikToken.split('.')[1]));
      this.uloga = this.logovanKorisnik.uloga[0].authority;
    })
    this.korisnikToken = localStorage.getItem('logovanKorisnik');
    if(this.korisnikToken){
      this.logovanKorisnik = JSON.parse(window.atob(this.korisnikToken.split('.')[1]));
      this.uloga = this.logovanKorisnik.uloga[0].authority;
    }
  }

  prikaziBioskope(){
    this.pozBioService.setTip('bio');
    this.router.navigate(['/bioskopi/stranica/1']);
  }

  prikaziPozorista(){
    this.pozBioService.setTip('poz');
    this.router.navigate(['/pozorista/stranica/1']);
  }

  prikaziAdministatoreFz(){
    this.adminService.setTip('adminFz');
    this.router.navigate(['/adminFz/stranica/1']);

  }

  prikaziAdministatoreSis(){
    this.adminService.setTip('adminSi');
    this.router.navigate(['/adminSi/stranica/1']);


  }

  prikaziAdministatorePB(){
    this.adminService.setTip('adminPb');
    this.router.navigate(['/adminPb/stranica/1']);


  }

  prikaziFanZonu() {
      this.router.navigate(['/fanzona/stranica/1']);
  }


  napraviNovoPB(){
    this.router.navigate(['/noviPozBio']);
  }

  napraviNovoAFZ(){
    this.router.navigate(['/noviAdmFz']);
  }

  napraviNovoASIS(){
    this.router.navigate(['/noviAdmSis']);
  }

  napraviNovoAPB() {
    this.router.navigate(['/noviAdmPB']);
  }

  idiNaProfil(){
    this.router.navigate(['/profil']);
  }

  idiPrijatelje(){
    this.router.navigate(['/prijatelji']);
  }

  idiNaFanZonu() {
    this.router.navigate(['/fanzona']);
  }

  registrujOglas() {
    this.router.navigate(['/noviOglas']);
  }

  prijava(){
    this.router.navigate(['/login']);
  }

  odjava = function(){
    this.logovanKorisnik = null;
    localStorage.removeItem("logovanKorisnik");
    window.location.reload();
  }

  odobriOglase() {
    this.router.navigate(['/odobriOglase/stranica/1']);

  }

  idiRezervacije(){
    this.router.navigate(['/rezervacije']);
  }

  izmeniPodatke(){
    this.router.navigate(['/izmenaPodataka'])
  }

  idiNaPoruke() {
    this.router.navigate(['/mojePoruke/stranica/1'])
  }

  idiRekvizite() {
    this.router.navigate(['/rekviziti'])
  }

  postaviBS() {
    this.router.navigate(['/skala'])
  }

  prikaziSkale() {
    this.router.navigate(['/skale'])
  }
    
  izvestaji(){
    this.router.navigate(['/izvestaji']);
  }

  napraviPredFilm(){
    this.router.navigate(['/noviPredFilm']);
  }

  public static updateUserStatus: Subject<boolean> = new Subject();

}
