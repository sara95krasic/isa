import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmeniOglasComponent } from './izmeni-oglas.component';

describe('IzmeniOglasComponent', () => {
  let component: IzmeniOglasComponent;
  let fixture: ComponentFixture<IzmeniOglasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmeniOglasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmeniOglasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
