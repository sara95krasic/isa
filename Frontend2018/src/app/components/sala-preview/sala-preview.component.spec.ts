import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalaPreviewComponent } from './sala-preview.component';

describe('SalaPreviewComponent', () => {
  let component: SalaPreviewComponent;
  let fixture: ComponentFixture<SalaPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalaPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalaPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
