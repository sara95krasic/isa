import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervacijaMestoComponent } from './rezervacija-mesto.component';

describe('RezervacijaMestoComponent', () => {
  let component: RezervacijaMestoComponent;
  let fixture: ComponentFixture<RezervacijaMestoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervacijaMestoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervacijaMestoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
