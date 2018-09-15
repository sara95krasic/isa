import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajIzbrisiSedistaComponent } from './dodaj-izbrisi-sedista.component';

describe('DodajIzbrisiSedistaComponent', () => {
  let component: DodajIzbrisiSedistaComponent;
  let fixture: ComponentFixture<DodajIzbrisiSedistaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodajIzbrisiSedistaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajIzbrisiSedistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
