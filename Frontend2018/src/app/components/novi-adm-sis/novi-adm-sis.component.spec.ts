import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviAdmSisComponent } from './novi-adm-sis.component';

describe('NoviAdmSisComponent', () => {
  let component: NoviAdmSisComponent;
  let fixture: ComponentFixture<NoviAdmSisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoviAdmSisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoviAdmSisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
