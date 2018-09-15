import { TestBed, inject } from '@angular/core/testing';

import { PorukeService } from './poruke.service';

describe('PorukeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PorukeService]
    });
  });

  it('should be created', inject([PorukeService], (service: PorukeService) => {
    expect(service).toBeTruthy();
  }));
});
