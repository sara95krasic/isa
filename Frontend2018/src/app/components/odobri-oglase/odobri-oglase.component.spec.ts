import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdobriOglaseComponent } from './odobri-oglase.component';

describe('OdobriOglaseComponent', () => {
  let component: OdobriOglaseComponent;
  let fixture: ComponentFixture<OdobriOglaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdobriOglaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdobriOglaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
