import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DajGlasComponent } from './daj-glas.component';

describe('DajGlasComponent', () => {
  let component: DajGlasComponent;
  let fixture: ComponentFixture<DajGlasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DajGlasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DajGlasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
