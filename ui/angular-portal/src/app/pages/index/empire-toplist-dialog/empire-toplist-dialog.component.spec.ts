import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpireToplistDialogComponent } from './empire-toplist-dialog.component';

describe('EmpireToplistDialogComponent', () => {
  let component: EmpireToplistDialogComponent;
  let fixture: ComponentFixture<EmpireToplistDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmpireToplistDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpireToplistDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
