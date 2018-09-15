import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledSkalaComponent } from './pregled-skala.component';

describe('PregledSkalaComponent', () => {
  let component: PregledSkalaComponent;
  let fixture: ComponentFixture<PregledSkalaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PregledSkalaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PregledSkalaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
