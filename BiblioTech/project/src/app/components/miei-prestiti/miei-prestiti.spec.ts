import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MieiPrestiti } from './miei-prestiti';

describe('MieiPrestiti', () => {
  let component: MieiPrestiti;
  let fixture: ComponentFixture<MieiPrestiti>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MieiPrestiti]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MieiPrestiti);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
