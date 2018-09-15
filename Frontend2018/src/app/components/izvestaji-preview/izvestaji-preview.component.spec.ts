import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzvestajiPreviewComponent } from './izvestaji-preview.component';

describe('IzvestajiPreviewComponent', () => {
  let component: IzvestajiPreviewComponent;
  let fixture: ComponentFixture<IzvestajiPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzvestajiPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzvestajiPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
