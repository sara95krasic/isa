import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PonudiComponent } from './ponudi.component';

describe('PonudiComponent', () => {
  let component: PonudiComponent;
  let fixture: ComponentFixture<PonudiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PonudiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PonudiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
