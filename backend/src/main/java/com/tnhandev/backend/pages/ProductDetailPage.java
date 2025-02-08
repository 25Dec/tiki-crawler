package com.tnhandev.backend.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.tnhandev.backend.models.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailPage {
    private Page page;

    public ProductDetailPage(Page page) {
        this.page = page;
    }

    public Product getDetail() {
        return new Product(
//                getProductUrl(),
                getProductImageUrls(),
                getBrandOrAuthor(),
                getProductName(),
                getAverageRating(),
                getReviewCount(),
                getSoldQuantity(),
                getCurrentPrice(),
                getDiscountPercentage(),
                getOriginalPrice(),
//                getDetailedInformation(),
                getProductDescription()
        );
    }

    public String getProductUrl() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.page.url();
    }

    public List<String> getProductImageUrls() {
        String thumbnailListSelector = "//div[contains(@class,'ThumbnailListStyled')]//a[@data-view-id='pdp_main_view_photo']";
        String bigImageSelector = "//div[contains(@class,'image-frame')]//picture[@class='webpimg-container']//img";
        String iconNextSelector = "//div[contains(@class,'ThumbnailListStyled')]//span[contains(@class,'icon-next')]";
        List<String> result = new ArrayList<>();

        List<Locator> thumbnails = this.page.locator(thumbnailListSelector).all();
        for (int i = 0; i < thumbnails.size(); i++) {
            thumbnails.get(i).click();
            this.waitForSelector(bigImageSelector);

            if (this.page.locator(bigImageSelector).isVisible()) {
                String bigImage = this.page.locator(bigImageSelector).getAttribute("srcset");
                bigImage = bigImage.split(",")[0].split(" ")[0];
                result.add(bigImage);
            }

            if (i == 5 && this.page.locator(iconNextSelector).isVisible())
                this.page.locator(iconNextSelector).click();
        }

        return result;
    }

    public String getBrandOrAuthor() {
        String brandOrAuthorSelector = "//h6[contains(text(),'Thương hiệu') or contains(text(),'Tác giả')]/a";
        if (this.page.locator(brandOrAuthorSelector).isVisible())
            return this.page.locator(brandOrAuthorSelector).textContent();
        return "";
    }

    public String getProductName() {
        String productNameSelector = "//h1[contains(@class,'TitledStyled')]";
        if (this.page.locator(productNameSelector).isVisible())
            return this.page.textContent(productNameSelector);
        return "";
    }

    public Double getAverageRating() {
        String averageRatingSelector = "//div[contains(@class,'StyledReview')]/div[1]";
        if (this.page.locator(averageRatingSelector).isVisible())
            return Double.parseDouble(this.page.textContent(averageRatingSelector));
        return 0.0;
    }

    public Integer getReviewCount() {
        String reviewCountSelector = "//a[@data-view-id='pdp_main_view_review']";
        if (this.page.locator(reviewCountSelector).isVisible()) {
            String reviewCount = this.page.textContent(reviewCountSelector);
            return Integer.parseInt(reviewCount.substring(1, reviewCount.length() - 1));
        }
        return 0;
    }

    public Integer getSoldQuantity() {
        String soldQuantitySelector = "//div[contains(@class,'StyledQuantitySold')]";
        if (this.page.locator(soldQuantitySelector).isVisible()) {
            String soldQuantity = this.page.textContent(soldQuantitySelector);
            String sq = soldQuantity.split(" ")[2];
            if (sq.contains("k"))
                return Integer.parseInt(sq.substring(0, sq.length() - 1));
            return Integer.parseInt(sq);
        }
        return 0;
    }

    public Long getCurrentPrice() {
        String currentPriceSelector = "//div[contains(@class,'current-price')]";

        if (this.page.locator(currentPriceSelector).isVisible()) {
            String currentPrice = this.page.locator(currentPriceSelector).textContent();
            String cleanPrice = currentPrice.replace(".", "");

            if (cleanPrice.endsWith("đ") || cleanPrice.endsWith("₫"))
                cleanPrice = cleanPrice.substring(0, cleanPrice.length() - 1);

            return Long.parseLong(cleanPrice.trim());
        }

        return 0L;
    }

    public String getDiscountPercentage() {
        String discountPercentageSelector = "//div[contains(@class,'discount-rate')]";

        if (this.page.locator(discountPercentageSelector).isVisible())
            return this.page.locator(discountPercentageSelector).textContent();

        return "";
    }

    public Long getOriginalPrice() {
        String originalPriceSelector = "//div[contains(@class,'original-price')]";
        if (this.page.locator(originalPriceSelector).isVisible()) {
            String originalPrice = this.page.locator(originalPriceSelector).textContent();
            String cleanPrice = originalPrice.replace(".", "");

            if (cleanPrice.endsWith("đ") || cleanPrice.endsWith("₫"))
                cleanPrice = cleanPrice.substring(0, cleanPrice.length() - 1).trim();

            return Long.parseLong(cleanPrice);
        }
        return 0L;
    }

    public Map<String, String> getDetailedInformation() {
        String detailedInformationSelector = "//div[contains(text(),'Thông tin chi tiết')]//following-sibling::div//div[contains(@class,'WidgetContentRowStyled')]";
        this.scrollAndWait();

        List<Locator> detailedInformation = this.page.locator(detailedInformationSelector).all();
        Map<String, String> result = new LinkedHashMap<>();

        for (Locator info : detailedInformation) {
            String attribute = info.locator("//span[1]").textContent();
            String value = info.locator("//span[2]").textContent();
            result.put(attribute, value);
        }

        return result;
    }

    public List<String> getProductDescription() {
        String productDescriptionSelector = "//div[contains(text(),'Mô tả sản phẩm')]//following-sibling::div//p";
        String btnMoreSelector = "//a[@class='btn-more']";
        this.scrollAndWait();

        if (this.page.locator(btnMoreSelector).isVisible())
            this.page.locator(btnMoreSelector).click();

        List<Locator> productDescription = this.page.locator(productDescriptionSelector).all();
        List<String> result = new ArrayList<>();

        for (Locator desc : productDescription) {
            Locator imgLocator = desc.locator("img");

            if (imgLocator.count() > 0) {
                String src = imgLocator.first().getAttribute("src");
                result.add(src);
            }
            else {
                String text = desc.textContent().trim();
                if (!text.equals(" "))
                    result.add(text);
            }
        }

        return result;
    }


    public void waitForSelector(String selector) {
        this.page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(60000));
    }

    public void scrollAndWait() {
        try {
            this.page.mouse().wheel(0, 1080);
            Thread.sleep(1000);
            this.page.mouse().wheel(0, 1080);
            Thread.sleep(1000);
            this.page.mouse().wheel(0, 1080);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
