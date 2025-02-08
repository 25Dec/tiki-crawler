import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionsAfterCrawlingComponent } from './actions-after-crawling.component';

describe('ActionsAfterCrawlingComponent', () => {
  let component: ActionsAfterCrawlingComponent;
  let fixture: ComponentFixture<ActionsAfterCrawlingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActionsAfterCrawlingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActionsAfterCrawlingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
