import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';
import { AgmCoreModule } from '@agm/core';
import { FormControl, FormGroup, Validators, AbstractControl, ValidatorFn } from '@angular/forms'

@Component({
  selector: 'app-izmeni-oglas',
  templateUrl: './izmeni-oglas.component.html',
  styleUrls: ['./izmeni-oglas.component.css']
})
export class IzmeniOglasComponent implements OnInit {

  id: any;
  oglas : any;
  izmeniOglasForma : any;
  file : any;
  change : boolean;
  pictureChange : boolean;

  @ViewChild('file') fileInput: ElementRef;

  constructor(private http:Http, private route: ActivatedRoute, private router: Router, private oglasiService : OglasiService) { }

  ngOnInit() {

    this.oglas = {}
    this.change = false;
    this.pictureChange = false;

    this.izmeniOglasForma = new FormGroup({
      naziv : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(90)
      ])),
      opis : new FormControl(""),
      aktivnoDo : new FormControl(""),
      putanja : new FormControl("")
    })

    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    
   this.oglasiService.dobaviOglase('/app/secured/oglas/'+this.id).subscribe((data) => {
      
      if(data['_body'] != ""){
        this.oglas = data;
        console.log(this.oglas)
        this.izmeniOglasForma.patchValue({naziv: this.oglas.naziv});
        this.izmeniOglasForma.patchValue({opis: this.oglas.opis});
        this.izmeniOglasForma.patchValue({aktivnoDo: this.oglas.aktivnoDo});

      }else{
        console.log('Greska pri dobavljanju oglasa')
        this.router.navigate(['/']);
      }
   });

  }

  onFileChange(event) {
    if(event.target.files.length > 0) {
      this.file = event.target.files[0];
      this.pictureChange = true;
      let image : any = this.fileInput.nativeElement.files[0];  
    }
  }

  promena(event) {
    this.change = true;
  }

  private prepareSave(): any {
    let input = new FormData();

    if(this.pictureChange == false){

        if(this.change == false){  
          console.log('Nema promene')
          input.append('naziv', this.izmeniOglasForma.get('naziv').value);
          input.append('opis', this.izmeniOglasForma.get('opis').value);
          input.append('aktivnoDo', this.izmeniOglasForma.get('aktivnoDo').value);
          //input.append('putanja', this.oglas.path);
          return input;
        }else {
          let image : any = this.fileInput.nativeElement.files[0];
          input.append('naziv', this.izmeniOglasForma.get('naziv').value);
          input.append('opis', this.izmeniOglasForma.get('opis').value);
          input.append('aktivnoDo', this.izmeniOglasForma.get('aktivnoDo').value);
          //input.append('putanja', image);
          return input;
        }

    }else {
      if(this.change == false){  
        input.append('naziv', this.izmeniOglasForma.get('naziv').value);
        input.append('opis', this.izmeniOglasForma.get('opis').value);
        input.append('aktivnoDo', this.izmeniOglasForma.get('aktivnoDo').value);
        input.append('putanja', this.oglas.path);
        return input;
      }else {
        let image : any = this.fileInput.nativeElement.files[0];
        input.append('naziv', this.izmeniOglasForma.get('naziv').value);
        input.append('opis', this.izmeniOglasForma.get('opis').value);
        input.append('aktivnoDo', this.izmeniOglasForma.get('aktivnoDo').value);
        input.append('putanja', image);
        return input;
      }

    }
  }

  posalji(value) {
    const formModel = this.prepareSave();

      if(this.pictureChange == false){
        console.log('Bez slike')
        this.oglasiService.izmeniOglasPost('/app/secured/izmeniOglas/' + this.oglas.id, formModel).subscribe((res) => {   

          if(res['_body'] != ""){
            this.router.navigate(['/odobriOglase/stranica/1']);
          }else{
            alert(res.headers.get('message'))
          }
      
        });

      }else {
        console.log('Sa slikom')
        this.oglasiService.izmeniOglasPost('/app/secured/izmeniOglasSaSlikom/' + this.oglas.id, formModel).subscribe((res) => {   

          if(res['_body'] != ""){
            this.router.navigate(['/odobriOglase/stranica/1']);
          }else{
            alert(res.headers.get('message'))
          }
      
        });
      }  

  }

}
