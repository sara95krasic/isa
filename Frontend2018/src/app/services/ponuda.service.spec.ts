import { TestBed, inject } from '@angular/core/testing';

import { PonudaService } from './ponuda.service';

describe('PonudaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PonudaService]
    });
  });

  it('should be created', inject([PonudaService], (service: PonudaService) => {
    expect(service).toBeTruthy();
  }));
});
