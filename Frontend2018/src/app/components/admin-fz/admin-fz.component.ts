import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from '../../services/admin-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Http } from '@angular/http';

@Component({
  selector: 'app-admin-fz',
  templateUrl: './admin-fz.component.html',
  styleUrls: ['./admin-fz.component.css']
})
export class AdminFzComponent implements OnInit {

  admini : any;
  stranica : number;
  tip : string;

  constructor(private http : Http,private prijavKorSer : AdminServiceService, private router: Router, private route: ActivatedRoute) { 
    
  }

  ngOnInit() {


    console.log(this.router.url)
    let niz = this.router.url.split('/');

    for(let i = 0; i < niz.length; i++){
      if(niz[i] === 'adminFz' || niz[i] === 'adminSi' || niz[i] === 'adminPb'){
        this.tip = niz[i].substring(0, 7);
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
      this.router.navigate(['/adminFz/stranica/'+this.stranica]);
    }
    
    if(this.tip === 'adminFz'){
        this.prijavKorSer.vratiSadrzaj('/app/adminFz/'+this.stranica).subscribe((data) => {
          this.admini = data.content;
          console.log("Pokupio A FZ")
        });
    }if(this.tip === 'adminSi'){
      this.prijavKorSer.vratiSadrzaj('/app/adminSis/'+this.stranica).subscribe((data) => {
        this.admini = data.content;
        console.log("Pokupio A S")
      });
    }else{
      this.prijavKorSer.vratiSadrzaj('/app/adminPb/'+this.stranica).subscribe((data) => {
        this.admini = data.content;
        console.log("Pokupio A P B")
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
    
    if(this.tip === 'adminFz'){
      this.vratiSadrzaj('/app/adminFz/');
    }else if(this.tip === 'adminSi'){
      this.vratiSadrzaj('/app/adminSis/');
    }else if(this.tip === 'adminPb'){
      this.vratiSadrzaj('/app/adminPb/');
    }else{
      this.router.navigate(['']);
    }
  }

  vratiSadrzaj(putanja:string){
    this.prijavKorSer.vratiSadrzaj(putanja+this.stranica).subscribe((data) => {
      this.admini = data.content;
   
      if(this.admini.length <= 0){
        this.stranica--;
        alert("Dosli ste do kraja pretrage");
        this.ngOnInit();
        return;
      }
      if(this.tip === 'adminFz'){
        this.router.navigate(['/adminFz/stranica/'+this.stranica]);
      }else if(this.tip === 'adminSi'){
        this.router.navigate(['/adminSis/stranica/'+this.stranica]);
      }else if(this.tip === 'adminPb'){
        this.router.navigate(['/adminPb/stranica/'+this.stranica]);
      }
      
    });
  }

  
  Izmeni(param){
    this.stranica = 1;
    if(this.prijavKorSer.getTip() === 'adminFz'){
      this.router.navigate(['/admin/edit', param]);
    }else if(this.prijavKorSer.getTip() === 'adminSi'){
      this.router.navigate(['/admin/edit', param]);
    }else{
      this.router.navigate(['/admin/edit', param]);
    }

  }
  

  
  Obrisi(param){
    this.stranica = 1;
    if(this.prijavKorSer.getTip() === 'adminFz'){
      
      this.http.delete('/app/adminFz/' + param).subscribe((res) => {
         console.log(res)

        if(res['_body'] != ""){
          this.prijavKorSer.vratiSadrzaj('/app/adminFz/'+this.stranica).subscribe((data) => {
            this.admini = data.content; 
          }) 
      }});

    }else if(this.prijavKorSer.getTip() === 'adminSi'){

      this.http.delete('/app/adminSi/' + param).subscribe((res) => {
       
        if(res['_body'] != ""){
          this.prijavKorSer.vratiSadrzaj('/app/adminSis/'+this.stranica).subscribe((data) => {
            this.admini = data.content; 
          }) 
      }});

    }else {
      this.http.delete('/app/adminPb/' + param).subscribe((res) => {
       
        if(res['_body'] != ""){
          this.prijavKorSer.vratiSadrzaj('/app/adminPb/'+this.stranica).subscribe((data) => {
            this.admini = data.content; 
          }) 
      }});
    }
  }
  
  
}
