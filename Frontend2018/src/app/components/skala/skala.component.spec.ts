import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkalaComponent } from './skala.component';

describe('SkalaComponent', () => {
  let component: SkalaComponent;
  let fixture: ComponentFixture<SkalaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkalaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkalaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
