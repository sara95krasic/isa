import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrzaRezervacijaRezervisiComponent } from './brza-rezervacija-rezervisi.component';

describe('BrzaRezervacijaRezervisiComponent', () => {
  let component: BrzaRezervacijaRezervisiComponent;
  let fixture: ComponentFixture<BrzaRezervacijaRezervisiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrzaRezervacijaRezervisiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrzaRezervacijaRezervisiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
