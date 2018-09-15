import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PozBioComponent } from './poz-bio.component';

describe('PozBioComponent', () => {
  let component: PozBioComponent;
  let fixture: ComponentFixture<PozBioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PozBioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PozBioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
