import { TestBed, inject } from '@angular/core/testing';

import { OglasiService } from './oglasi.service';

describe('OglasiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OglasiService]
    });
  });

  it('should be created', inject([OglasiService], (service: OglasiService) => {
    expect(service).toBeTruthy();
  }));
});
