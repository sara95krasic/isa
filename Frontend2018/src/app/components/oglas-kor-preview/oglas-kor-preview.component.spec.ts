import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasKorPreviewComponent } from './oglas-kor-preview.component';

describe('OglasKorPreviewComponent', () => {
  let component: OglasKorPreviewComponent;
  let fixture: ComponentFixture<OglasKorPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasKorPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasKorPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
