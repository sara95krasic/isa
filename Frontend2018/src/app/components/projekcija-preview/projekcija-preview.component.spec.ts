import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjekcijaPreviewComponent } from './projekcija-preview.component';

describe('ProjekcijaPreviewComponent', () => {
  let component: ProjekcijaPreviewComponent;
  let fixture: ComponentFixture<ProjekcijaPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjekcijaPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjekcijaPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
