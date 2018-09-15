import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasOpstiPreviewComponent } from './oglas-opsti-preview.component';

describe('OglasOpstiPreviewComponent', () => {
  let component: OglasOpstiPreviewComponent;
  let fixture: ComponentFixture<OglasOpstiPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OglasOpstiPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OglasOpstiPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
