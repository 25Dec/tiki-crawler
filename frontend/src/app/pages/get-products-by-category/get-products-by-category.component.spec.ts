import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetProductsByCategoryComponent } from './get-products-by-category.component';

describe('GetProductsByCategoryComponent', () => {
  let component: GetProductsByCategoryComponent;
  let fixture: ComponentFixture<GetProductsByCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetProductsByCategoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetProductsByCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
