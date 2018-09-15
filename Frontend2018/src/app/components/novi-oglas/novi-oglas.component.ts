import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import {FormControl, FormGroup, Validators, AbstractControl, ValidatorFn} from '@angular/forms';
import { Http } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OglasiService } from '../../services/oglasi.service';

@Component({
  selector: 'app-novi-oglas',
  templateUrl: './novi-oglas.component.html',
  styleUrls: ['./novi-oglas.component.css']
})
export class NoviOglasComponent implements OnInit {

  noviOglasForma : any;
  file : any;

  @ViewChild('file') fileInput: ElementRef;

  constructor(private oglasiService : OglasiService ,private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {

    this.noviOglasForma = new FormGroup({
      naziv : new FormControl("",Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(90)
      ])),
      opis : new FormControl(""),
      aktivnoDo : new FormControl(""),
      putanja : new FormControl("")
    })

  }

  onFileChange(event) {
    if(event.target.files.length > 0) {
      this.file = event.target.files[0];
      console.log("File " + this.file)
      //this.noviOglasForma.get('putanja').setValue(file);
    }
  }

  private prepareSave(): any {
    let input = new FormData();
    let image : any = this.fileInput.nativeElement.files[0];
    input.append('naziv', this.noviOglasForma.get('naziv').value);
    input.append('opis', this.noviOglasForma.get('opis').value);
    input.append('aktivnoDo', this.noviOglasForma.get('aktivnoDo').value);
    input.append('putanja', image, image.name);
    return input;
  }

  posalji(value) {
    const formModel = this.prepareSave();
    
    console.log(formModel)
  
    this.oglasiService.posaljiOglas('/app/secured/sacuvajOglas',formModel).subscribe((res) => {
      if(res['_body'] != ""){
        this.router.navigate(['']);
      }else{
        alert(res.headers.get('message'))
      }
    });

  }


}
