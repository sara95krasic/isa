import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaPodatakaComponent } from './izmena-podataka.component';

describe('IzmenaPodatakaComponent', () => {
  let component: IzmenaPodatakaComponent;
  let fixture: ComponentFixture<IzmenaPodatakaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaPodatakaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaPodatakaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
