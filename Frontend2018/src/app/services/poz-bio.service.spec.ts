import { TestBed, inject } from '@angular/core/testing';

import { PozBioService } from './poz-bio.service';

describe('PozBioService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PozBioService]
    });
  });

  it('should be created', inject([PozBioService], (service: PozBioService) => {
    expect(service).toBeTruthy();
  }));
});
