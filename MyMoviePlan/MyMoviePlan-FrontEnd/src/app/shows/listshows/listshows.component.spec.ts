import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListshowsComponent } from './listshows.component';

describe('ListshowsComponent', () => {
  let component: ListshowsComponent;
  let fixture: ComponentFixture<ListshowsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListshowsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListshowsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
