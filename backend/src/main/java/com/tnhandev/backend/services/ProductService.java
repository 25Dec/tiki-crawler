package com.tnhandev.backend.services;

import com.microsoft.playwright.*;
import com.tnhandev.backend.models.Product;
import com.tnhandev.backend.pages.HomePage;
import com.tnhandev.backend.pages.SearchPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public List<Product> getProductsByKeyword(String keyword, int numberOfPagesToCrawl) {
        List<Product> result;
        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                BrowserContext context = browser.newContext();
                Page page = context.newPage()
        ) {
            page.navigate("https://tiki.vn/");

            HomePage homePage = new HomePage(page);
            SearchPage searchPage = new SearchPage(context, page);

            homePage.typeKeyword(keyword);
            result = searchPage.getProductsByKeyword(numberOfPagesToCrawl);
        }
        return result;
    }

    public List<Product> getProductsByCategory(String categoryUrl, int numberOfPagesToCrawl) {
        List<Product> result;
        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                BrowserContext context = browser.newContext();
                Page page = context.newPage()
        ) {
            page.navigate(categoryUrl);

            SearchPage searchPage = new SearchPage(context, page);

            result = searchPage.getProductsByCategory(numberOfPagesToCrawl);
        }
        return result;
    }
}
