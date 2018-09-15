import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasLicniPreviewComponent } from './oglas-licni-preview.component';

describe('OglasLicniPreviewComponent', () => {
  let component: OglasLicniPreviewComponent;
  let fixture: ComponentFixture<OglasLicniPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasLicniPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasLicniPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
