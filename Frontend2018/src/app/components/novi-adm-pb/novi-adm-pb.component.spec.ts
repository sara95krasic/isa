import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviAdmPbComponent } from './novi-adm-pb.component';

describe('NoviAdmPbComponent', () => {
  let component: NoviAdmPbComponent;
  let fixture: ComponentFixture<NoviAdmPbComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoviAdmPbComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoviAdmPbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
