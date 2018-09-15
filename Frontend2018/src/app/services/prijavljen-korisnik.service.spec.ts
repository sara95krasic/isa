import { TestBed, inject } from '@angular/core/testing';

import { PrijavljenKorisnikService } from './prijavljen-korisnik.service';

describe('PrijavljenKorisnikService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PrijavljenKorisnikService]
    });
  });

  it('should be created', inject([PrijavljenKorisnikService], (service: PrijavljenKorisnikService) => {
    expect(service).toBeTruthy();
  }));
});
