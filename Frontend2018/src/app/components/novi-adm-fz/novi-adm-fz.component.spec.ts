import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviAdmFzComponent } from './novi-adm-fz.component';

describe('NoviAdmFzComponent', () => {
  let component: NoviAdmFzComponent;
  let fixture: ComponentFixture<NoviAdmFzComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoviAdmFzComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoviAdmFzComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
