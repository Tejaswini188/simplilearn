import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentcanceledComponent } from './paymentcanceled.component';

describe('PaymentcanceledComponent', () => {
  let component: PaymentcanceledComponent;
  let fixture: ComponentFixture<PaymentcanceledComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentcanceledComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentcanceledComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
