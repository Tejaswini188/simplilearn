import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListshowComponent } from './listshow.component';

describe('ListshowComponent', () => {
  let component: ListshowComponent;
  let fixture: ComponentFixture<ListshowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListshowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListshowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
