import { TestBed, inject } from '@angular/core/testing';

import { KartaService } from './karta.service';

describe('KartaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [KartaService]
    });
  });

  it('should be created', inject([KartaService], (service: KartaService) => {
    expect(service).toBeTruthy();
  }));
});
