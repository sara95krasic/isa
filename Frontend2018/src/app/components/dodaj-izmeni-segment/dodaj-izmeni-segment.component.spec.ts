import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajIzmeniSegmentComponent } from './dodaj-izmeni-segment.component';

describe('DodajIzmeniSegmentComponent', () => {
  let component: DodajIzmeniSegmentComponent;
  let fixture: ComponentFixture<DodajIzmeniSegmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodajIzmeniSegmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajIzmeniSegmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
