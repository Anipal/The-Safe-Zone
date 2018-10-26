import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SafezonesComponent } from './safezones.component';

describe('SafezonesComponent', () => {
  let component: SafezonesComponent;
  let fixture: ComponentFixture<SafezonesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SafezonesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SafezonesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
