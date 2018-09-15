import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFzComponent } from './admin-fz.component';

describe('AdminFzComponent', () => {
  let component: AdminFzComponent;
  let fixture: ComponentFixture<AdminFzComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminFzComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFzComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
