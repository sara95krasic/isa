import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipSegmentaComponent } from './tip-segmenta.component';

describe('TipSegmentaComponent', () => {
  let component: TipSegmentaComponent;
  let fixture: ComponentFixture<TipSegmentaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipSegmentaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipSegmentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
