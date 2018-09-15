import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervacijaGlavnaComponent } from './rezervacija-glavna.component';

describe('RezervacijaGlavnaComponent', () => {
  let component: RezervacijaGlavnaComponent;
  let fixture: ComponentFixture<RezervacijaGlavnaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervacijaGlavnaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervacijaGlavnaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
