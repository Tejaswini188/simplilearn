import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewpaymentComponent } from './reviewpayment.component';

describe('ReviewpaymentComponent', () => {
  let component: ReviewpaymentComponent;
  let fixture: ComponentFixture<ReviewpaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewpaymentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewpaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
