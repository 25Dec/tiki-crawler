import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  apiUrl = 'http://localhost:8080/api/v1/category';

  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<Array<Category>> {
    return this.http.get<Array<Category>>(
      this.apiUrl
    );
  }
}
