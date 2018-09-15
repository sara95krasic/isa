import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrijateljiComponent } from './prijatelji.component';

describe('PrijateljiComponent', () => {
  let component: PrijateljiComponent;
  let fixture: ComponentFixture<PrijateljiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrijateljiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrijateljiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
