export interface Category {
  key: string | null;
  label: string;
  data: string;
  children: Array<Category>;
}

