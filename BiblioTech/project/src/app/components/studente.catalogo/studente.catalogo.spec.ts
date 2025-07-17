import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudenteCatalogo } from './studente.catalogo';

describe('StudenteCatalogo', () => {
  let component: StudenteCatalogo;
  let fixture: ComponentFixture<StudenteCatalogo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudenteCatalogo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudenteCatalogo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
