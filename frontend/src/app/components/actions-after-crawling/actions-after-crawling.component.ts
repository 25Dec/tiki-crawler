import {Component, Input} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {DividerModule} from 'primeng/divider';
import {TableModule} from 'primeng/table';
import {Product} from '../../models/product';
import {DialogModule} from 'primeng/dialog';
import {Rating} from 'primeng/rating';
import {FormsModule} from '@angular/forms';
import {ProductService} from '../../services/product/product.service';

@Component({
  selector: 'app-actions-after-crawling',
  imports: [ButtonModule, DividerModule, TableModule, DialogModule, Rating, FormsModule],
  templateUrl: './actions-after-crawling.component.html',
  styleUrl: './actions-after-crawling.component.scss',
})
export class ActionsAfterCrawlingComponent {
  @Input()
  crawledProducts: Array<Product> = []
  dialogVisible: boolean = false

  constructor(private productService: ProductService,) {
  }

  showDialog(): void {
    this.dialogVisible = true
  }

  exportToJSON() {
    this.productService.exportToJSON(this.crawledProducts)
  }

  exportToCSV() {
    this.productService.exportToCSV(this.crawledProducts)
  }
}
