import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasiPreviewComponent } from './oglasi-preview.component';

describe('OglasiPreviewComponent', () => {
  let component: OglasiPreviewComponent;
  let fixture: ComponentFixture<OglasiPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasiPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasiPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
