import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Product} from '../../models/product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  apiUrl = 'http://localhost:8080/api/v1/product';

  constructor(private http: HttpClient) {
  }

  getProductsByKeyword(
    keyword: string,
    numberOfPagesToCrawl: number
  ): Observable<Array<Product>> {
    return this.http.get<Array<Product>>(
      `${this.apiUrl}/kw?keyword=${keyword}&numberOfPagesToCrawl=${numberOfPagesToCrawl}`
    );
  }

  getProductsByCategory(
    selectedCategory: string,
    numberOfPagesToCrawl: number
  ): Observable<Array<Product>> {
    return this.http.get<Array<Product>>(
      `${this.apiUrl}/ct?categoryUrl=${selectedCategory}&numberOfPagesToCrawl=${numberOfPagesToCrawl}`
    );
  }
  
  exportToJSON(data: Array<Product>) {
    // Chuyển đổi dữ liệu thành chuỗi JSON
    let jsonString = JSON.stringify(data, null, 2);

    // Tạo Blob với type là application/json
    let blob = new Blob([jsonString], {type: 'application/json'});

    // Tạo URL cho blob
    let url = URL.createObjectURL(blob);

    // Tạo thẻ a để tải file
    let link = document.createElement('a');
    link.href = url;
    link.download = "import-data.json";

    // Thêm link vào document và click
    document.body.appendChild(link);
    link.click();

    // Cleanup
    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  }

  exportToCSV(data: Array<Product>): void {
    // Lấy headers từ Product interface
    let headers = [
      'productUrl',
      'productImageUrls',
      'brandOrAuthor',
      'productName',
      'averageRating',
      'reviewCount',
      'soldQuantity',
      'currentPrice',
      'discountPercentage',
      'originalPrice',
      'productDescription'
    ];

    // Tạo chuỗi CSV với headers
    let csvRows: Array<string> = [headers.join(',')];

    // Thêm từng dòng dữ liệu
    data.forEach((product: Product) => {
      let row = headers.map(header => {
        let value = product[header as keyof Product];

        // Xử lý null/undefined
        if (value === null || value === undefined) {
          return '';
        }

        // Xử lý arrays - chuyển thành chuỗi và bọc trong ngoặc vuông
        if (Array.isArray(value)) {
          let arrayString = value
            .map(item => item.toString().replace(/"/g, '""'))
            .join('|');
          return `"[${arrayString}]"`;
        }

        // Xử lý các kiểu dữ liệu khác
        let cellValue = value.toString();

        // Escape double quotes
        cellValue = cellValue.replace(/"/g, '""');

        // Wrap in quotes if contains special characters
        if (cellValue.search(/([",\n|])/g) >= 0) {
          cellValue = `"${cellValue}"`;
        }

        return cellValue;
      }).join(',');

      csvRows.push(row);
    });

    let csvString = csvRows.join('\n');

    // Thêm BOM để Excel hiển thị đúng tiếng Việt
    let BOM = '\uFEFF';
    let blob = new Blob([BOM + csvString], {
      type: 'text/csv;charset=utf-8;'
    });

    // Download file
    let url = URL.createObjectURL(blob);
    let link = document.createElement('a');
    link.href = url;
    link.download = "import-data.csv";

    // Click an toàn
    document.body.appendChild(link);
    setTimeout(() => {
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
      }, 0
    );
  }
}
