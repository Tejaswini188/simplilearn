import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListgenreComponent } from './listgenre.component';

describe('ListgenreComponent', () => {
  let component: ListgenreComponent;
  let fixture: ComponentFixture<ListgenreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListgenreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListgenreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
