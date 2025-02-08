package com.tnhandev.backend.models;

import java.util.List;
import java.util.Map;

public class Product {
    private String productUrl;                                  // URL sản phẩm
    private List<String> productImageUrls;                      // Danh sách URL hình ảnh sản phẩm
    private String brandOrAuthor;                               // Thương hiệu hoặc tác giả (sách)
    private String productName;                                 // Tên sản phẩm
    private Double averageRating;                               // Điểm đánh giá trung bình
    private Integer reviewCount;                                // Số lượng đánh giá
    private Integer soldQuantity;                               // Số lượng đã bán
    private Long currentPrice;                                // Giá hiện tại
    private String discountPercentage;                          // Phần trăm giảm giá
    private Long originalPrice;                               // Giá gốc
    private Map<String, String> detailedInformation;     // Thông tin chi tiết
    private List<String> productDescription;                          // Mô tả sản phẩm

    public Product() {
    }

    public Product(
//            String productUrl,
            List<String> productImageUrls,
            String brandOrAuthor,
            String productName,
            Double averageRating,
            Integer reviewCount,
            Integer soldQuantity,
            Long currentPrice,
            String discountPercentage,
            Long originalPrice,
//            Map<String, String> detailedInformation,
            List<String> productDescription
                  ) {
//        this.productUrl = productUrl;
        this.productImageUrls = productImageUrls;
        this.brandOrAuthor = brandOrAuthor;
        this.productName = productName;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.soldQuantity = soldQuantity;
        this.currentPrice = currentPrice;
        this.discountPercentage = discountPercentage;
        this.originalPrice = originalPrice;
//        this.detailedInformation = detailedInformation;
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productUrl='" + productUrl + '\'' +
                ", productImageUrls=" + productImageUrls +
                ", brandOrAuthor='" + brandOrAuthor + '\'' +
                ", productName='" + productName + '\'' +
                ", averageRating=" + averageRating +
                ", reviewCount=" + reviewCount +
                ", soldQuantity=" + soldQuantity +
                ", currentPrice=" + currentPrice +
                ", discountPercentage='" + discountPercentage + '\'' +
                ", originalPrice=" + originalPrice +
                ", detailedInformation=" + detailedInformation +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public List<String> getProductImageUrls() {
        return productImageUrls;
    }

    public void setProductImageUrls(List<String> productImageUrls) {
        this.productImageUrls = productImageUrls;
    }

    public String getBrandOrAuthor() {
        return brandOrAuthor;
    }

    public void setBrandOrAuthor(String brandOrAuthor) {
        this.brandOrAuthor = brandOrAuthor;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Map<String, String> getDetailedInformation() {
        return detailedInformation;
    }

    public void setDetailedInformation(Map<String, String> detailedInformation) {
        this.detailedInformation = detailedInformation;
    }

    public List<String> getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(List<String> productDescription) {
        this.productDescription = productDescription;
    }
}