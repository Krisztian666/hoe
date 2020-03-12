import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroToplistDialogComponent } from './hero-toplist-dialog.component';

describe('HeroToplistDialogComponent', () => {
  let component: HeroToplistDialogComponent;
  let fixture: ComponentFixture<HeroToplistDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeroToplistDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroToplistDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
