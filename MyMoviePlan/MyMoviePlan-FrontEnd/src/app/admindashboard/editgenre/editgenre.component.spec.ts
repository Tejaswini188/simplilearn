import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditgenreComponent } from './editgenre.component';

describe('EditgenreComponent', () => {
  let component: EditgenreComponent;
  let fixture: ComponentFixture<EditgenreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditgenreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditgenreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
