import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpeciesPageComponent } from './species-page.component';

describe('SpeciesPageComponent', () => {
  let component: SpeciesPageComponent;
  let fixture: ComponentFixture<SpeciesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpeciesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpeciesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
