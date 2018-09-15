import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmeniPonuduComponent } from './izmeni-ponudu.component';

describe('IzmeniPonuduComponent', () => {
  let component: IzmeniPonuduComponent;
  let fixture: ComponentFixture<IzmeniPonuduComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmeniPonuduComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmeniPonuduComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
