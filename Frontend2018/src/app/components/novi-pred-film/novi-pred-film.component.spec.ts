import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviPredFilmComponent } from './novi-pred-film.component';

describe('NoviPredFilmComponent', () => {
  let component: NoviPredFilmComponent;
  let fixture: ComponentFixture<NoviPredFilmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoviPredFilmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoviPredFilmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
