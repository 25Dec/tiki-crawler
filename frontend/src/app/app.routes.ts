import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'kw'
  },
  {
    path: 'kw',
    loadComponent: () =>
      import(
        './pages/get-products-by-keyword/get-products-by-keyword.component'
        ).then((m) => m.GetProductsByKeywordComponent),
  },
  {
    path: 'ct',
    loadComponent: () =>
      import(
        './pages/get-products-by-category/get-products-by-category.component'
        ).then((m) => m.GetProductsByCategoryComponent),
  },
  {
    path: '**',
    redirectTo: 'kw'
  },
];
