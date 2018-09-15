import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MojePorukeComponent } from './moje-poruke.component';

describe('MojePorukeComponent', () => {
  let component: MojePorukeComponent;
  let fixture: ComponentFixture<MojePorukeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MojePorukeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MojePorukeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
