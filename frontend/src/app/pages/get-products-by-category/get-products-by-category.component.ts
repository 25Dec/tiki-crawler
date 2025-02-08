import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {ButtonModule} from 'primeng/button';
import {DividerModule} from 'primeng/divider';
import {InputNumberModule} from 'primeng/inputnumber';
import {InputTextModule} from 'primeng/inputtext';
import {Product} from '../../models/product';
import {CategoryService} from '../../services/category/category.service';
import {ProductService} from '../../services/product/product.service';
import {ActionsAfterCrawlingComponent} from '../../components/actions-after-crawling/actions-after-crawling.component';
import {NgIf} from '@angular/common';
import {TreeSelectModule} from 'primeng/treeselect';

@Component({
  selector: 'app-get-products-by-category',
  imports: [
    FormsModule,
    InputNumberModule,
    InputTextModule,
    ButtonModule,
    DividerModule,
    ActionsAfterCrawlingComponent,
    NgIf,
    ReactiveFormsModule,
    TreeSelectModule
  ],
  templateUrl: './get-products-by-category.component.html',
  styleUrl: './get-products-by-category.component.scss',
})
export class GetProductsByCategoryComponent implements OnInit {
  myForm: FormGroup = new FormGroup({});
  isWaitingForCrawling: WritableSignal<boolean> = signal(false);
  crawledCategories: Array<any> = [];
  crawledProducts: WritableSignal<Array<Product>> = signal([]);

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private categoryService: CategoryService
  ) {
  }

  ngOnInit(): void {
    this.myForm = this.formBuilder.group({
      selectedCategory: ['', Validators.required],
      numberOfPagesToCrawl: [1, Validators.required],
    });

    let categoriesFromLocalStorage = localStorage.getItem('crawledCategories');

    if (categoriesFromLocalStorage)
      this.crawledCategories = JSON.parse(categoriesFromLocalStorage);
    else {
      this.categoryService.getCategories().subscribe({
          next: (data) => {
            data = data.map((category, index1) => {
              category.key = `${index1}`;
              category.children = category.children.map((child, index2) => {
                child.key = `${index1}-${index2}`;
                return child;
              })
              return category;
            })
            this.crawledCategories = data;
            localStorage.setItem('crawledCategories', JSON.stringify(data));

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

  getProductsByCategory(): void {
    if (this.myForm.valid) {
      let inputs: any = this.myForm.value;
      this.isWaitingForCrawling.set(true);

      this.productService
        .getProductsByCategory(inputs.selectedCategory.data, inputs.numberOfPagesToCrawl)
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
