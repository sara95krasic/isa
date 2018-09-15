import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezOglasiComponent } from './rez-oglasi.component';

describe('RezOglasiComponent', () => {
  let component: RezOglasiComponent;
  let fixture: ComponentFixture<RezOglasiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezOglasiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezOglasiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
