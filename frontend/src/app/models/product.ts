export interface Product {
  productUrl: string;
  productImageUrls: Array<string>;
  brandOrAuthor: string;
  productName: string;
  averageRating: number;
  reviewCount: number;
  soldQuantity: number;
  currentPrice: number;
  discountPercentage: string;
  originalPrice: number;
  // detailedInformation: Map<string, string>;
  productDescription: Array<string>;
}
