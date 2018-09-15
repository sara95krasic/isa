import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PozBioPreviewComponent } from './poz-bio-preview.component';

describe('PozBioPreviewComponent', () => {
  let component: PozBioPreviewComponent;
  let fixture: ComponentFixture<PozBioPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PozBioPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PozBioPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
