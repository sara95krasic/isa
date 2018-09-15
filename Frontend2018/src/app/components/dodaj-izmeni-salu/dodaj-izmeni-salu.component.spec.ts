import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajIzmeniSaluComponent } from './dodaj-izmeni-salu.component';

describe('DodajIzmeniSaluComponent', () => {
  let component: DodajIzmeniSaluComponent;
  let fixture: ComponentFixture<DodajIzmeniSaluComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodajIzmeniSaluComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajIzmeniSaluComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
