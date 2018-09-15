import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmeniSkaluComponent } from './izmeni-skalu.component';

describe('IzmeniSkaluComponent', () => {
  let component: IzmeniSkaluComponent;
  let fixture: ComponentFixture<IzmeniSkaluComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmeniSkaluComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmeniSkaluComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
