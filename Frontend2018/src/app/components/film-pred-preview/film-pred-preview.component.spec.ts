import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmPredPreviewComponent } from './film-pred-preview.component';

describe('FilmPredPreviewComponent', () => {
  let component: FilmPredPreviewComponent;
  let fixture: ComponentFixture<FilmPredPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmPredPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmPredPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
