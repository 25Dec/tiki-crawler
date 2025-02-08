package com.tnhandev.backend.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.tnhandev.backend.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private BrowserContext context;
    private Page page;
    private int maxConcurrentTabs = 20;

    public SearchPage(BrowserContext context, Page page) {
        this.context = context;
        this.page = page;
    }

    public List<Product> getProductsByKeyword(int numberOfPagesToCrawl) {
        List<Product> results = new ArrayList<>();

        // Kiểm tra số lượng trang
        String paginationBlockSelector = "//div[@class='pagination-block']/ul/li[last()]";
        this.waitForSelector(paginationBlockSelector);
        numberOfPagesToCrawl = Math.min(
                numberOfPagesToCrawl,
                Integer.parseInt(this.page.textContent(paginationBlockSelector))
                                       );

        // Duyệt qua từng trang
        for (int currentPage = 0; currentPage < numberOfPagesToCrawl; currentPage++) {
            // Lấy URLs của sản phẩm
            String productCardSelector = "//div[contains(@class,'ProductItemContainerStyled')]";
            this.waitForSelector(productCardSelector);

            for (Locator locator : this.page.locator(productCardSelector).all()) {
                locator.click();

                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Product product = new ProductDetailPage(this.page).getDetail();
                results.add(product);
                this.page.goBack();
            }

            // Chuyển đến trang tiếp theo
            if (currentPage < numberOfPagesToCrawl - 1) {
                this.scrollAndWait();
                this.page.click("//div[@class='pagination-block']/div[last()]");
                this.page.waitForLoadState();
            }
        }

        return results;
    }

    public List<Product> getProductsByCategory(int numberOfPagesToCrawl) {
        String productCardSelector = "//div[contains(@class,'ProductItemContainerStyled')]";
        String seeMoreBtnSelector = "//div[@data-view-id='category_infinity_view.more']";
        List<Product> result = new ArrayList<>();

        for (int currentPage = 0; currentPage < numberOfPagesToCrawl; currentPage++) {
            this.page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
            if (this.page.locator(seeMoreBtnSelector).count() > 0) {
                this.page.locator(seeMoreBtnSelector).click();
                this.waitForSelector(productCardSelector);
            }
            else break;
        }

        for (Locator locator : this.page.locator(productCardSelector).all()) {
            locator.click();

            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Product product = new ProductDetailPage(this.page).getDetail();
            result.add(product);
            this.page.goBack();
        }

        return result;
    }

    public void waitForSelector(String selector) {
        this.page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(60000));
    }

    public void scrollAndWait() {
        try {
            this.page.mouse().wheel(0, 1080);
            Thread.sleep(500);
            this.page.mouse().wheel(0, 1080);
            Thread.sleep(500);
            this.page.mouse().wheel(0, 1080);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
