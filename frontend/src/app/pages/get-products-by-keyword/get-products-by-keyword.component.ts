import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators,} from '@angular/forms';
import {ButtonModule} from 'primeng/button';
import {DividerModule} from 'primeng/divider';
import {InputNumberModule} from 'primeng/inputnumber';
import {InputTextModule} from 'primeng/inputtext';
import {Product} from '../../models/product';
import {ProductService} from '../../services/product/product.service';
import {ActionsAfterCrawlingComponent} from '../../components/actions-after-crawling/actions-after-crawling.component';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-get-products-by-keyword',
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    InputNumberModule,
    InputTextModule,
    ButtonModule,
    DividerModule,
    ActionsAfterCrawlingComponent,
  ],
  templateUrl: './get-products-by-keyword.component.html',
  styleUrl: './get-products-by-keyword.component.scss',
})
export class GetProductsByKeywordComponent implements OnInit {
  myForm: FormGroup = new FormGroup({});
  isWaitingForCrawling: WritableSignal<boolean> = signal(false);
  crawledProducts: WritableSignal<Array<Product>> = signal([]);

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService
  ) {
  }

  ngOnInit(): void {
    this.myForm = this.formBuilder.group({
      keyword: ['', Validators.required],
      numberOfPagesToCrawl: [1, Validators.required],
    });
  }

  getProductsByKeyword(): void {
    if (this.myForm.valid) {
      let inputs: any = this.myForm.value;
      this.isWaitingForCrawling.set(true);

      this.productService
        .getProductsByKeyword(inputs.keyword, inputs.numberOfPagesToCrawl)
        .subscribe({
            next: (data) => {
              this.crawledProducts.set(data);
            },
            error: (err) => {
              console.error('Error while crawling:', err);
            },
            complete: () => {
              this.isWaitingForCrawling.set(false);
            },
          }
        );
    }
  }
}
