import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajIzmeniProjekcijuComponent } from './dodaj-izmeni-projekciju.component';

describe('DodajIzmeniProjekcijuComponent', () => {
  let component: DodajIzmeniProjekcijuComponent;
  let fixture: ComponentFixture<DodajIzmeniProjekcijuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodajIzmeniProjekcijuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajIzmeniProjekcijuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
