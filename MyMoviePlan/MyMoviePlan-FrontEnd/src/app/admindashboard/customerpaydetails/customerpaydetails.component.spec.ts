import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerpaydetailsComponent } from './customerpaydetails.component';

describe('CustomerpaydetailsComponent', () => {
  let component: CustomerpaydetailsComponent;
  let fixture: ComponentFixture<CustomerpaydetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerpaydetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerpaydetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
