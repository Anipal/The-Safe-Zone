import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SafezoneRegComponent } from './safezone-reg.component';

describe('SafezoneRegComponent', () => {
  let component: SafezoneRegComponent;
  let fixture: ComponentFixture<SafezoneRegComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SafezoneRegComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SafezoneRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
