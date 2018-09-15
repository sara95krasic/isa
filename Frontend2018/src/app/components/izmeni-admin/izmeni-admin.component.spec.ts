import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmeniAdminComponent } from './izmeni-admin.component';

describe('IzmeniAdminComponent', () => {
  let component: IzmeniAdminComponent;
  let fixture: ComponentFixture<IzmeniAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmeniAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmeniAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
