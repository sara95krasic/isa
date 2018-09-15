import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FanZonaComponent } from './fan-zona.component';

describe('FanZonaComponent', () => {
  let component: FanZonaComponent;
  let fixture: ComponentFixture<FanZonaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FanZonaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FanZonaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
