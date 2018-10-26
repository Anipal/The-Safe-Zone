import { TestBed, inject } from '@angular/core/testing';

import { SafezoneService } from './safezone.service';

describe('SafezoneService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SafezoneService]
    });
  });

  it('should be created', inject([SafezoneService], (service: SafezoneService) => {
    expect(service).toBeTruthy();
  }));
});
