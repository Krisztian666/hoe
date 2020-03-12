import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpirePageComponent } from './empire-page.component';

describe('EmpirePageComponent', () => {
  let component: EmpirePageComponent;
  let fixture: ComponentFixture<EmpirePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmpirePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpirePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
