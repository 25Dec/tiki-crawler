import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetProductsByKeywordComponent } from './get-products-by-keyword.component';

describe('GetProductsByKeywordComponent', () => {
  let component: GetProductsByKeywordComponent;
  let fixture: ComponentFixture<GetProductsByKeywordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetProductsByKeywordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetProductsByKeywordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
