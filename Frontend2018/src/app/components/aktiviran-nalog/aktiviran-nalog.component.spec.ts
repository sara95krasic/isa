import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AktiviranNalogComponent } from './aktiviran-nalog.component';

describe('AktiviranNalogComponent', () => {
  let component: AktiviranNalogComponent;
  let fixture: ComponentFixture<AktiviranNalogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AktiviranNalogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AktiviranNalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
