import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervacijaPrijateljiComponent } from './rezervacija-prijatelji.component';

describe('RezervacijaPrijateljiComponent', () => {
  let component: RezervacijaPrijateljiComponent;
  let fixture: ComponentFixture<RezervacijaPrijateljiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervacijaPrijateljiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervacijaPrijateljiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
