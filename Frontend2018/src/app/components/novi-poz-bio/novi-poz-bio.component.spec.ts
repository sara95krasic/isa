import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviPozBioComponent } from './novi-poz-bio.component';

describe('NoviPozBioComponent', () => {
  let component: NoviPozBioComponent;
  let fixture: ComponentFixture<NoviPozBioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoviPozBioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoviPozBioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
