import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentreceiptComponent } from './paymentreceipt.component';

describe('PaymentreceiptComponent', () => {
  let component: PaymentreceiptComponent;
  let fixture: ComponentFixture<PaymentreceiptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentreceiptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentreceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
