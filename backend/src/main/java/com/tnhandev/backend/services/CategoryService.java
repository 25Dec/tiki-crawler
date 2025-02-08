package com.tnhandev.backend.services;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.tnhandev.backend.models.Category;
import com.tnhandev.backend.pages.HomePage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    public List<Category> getCategories() {
        List<Category> result = new ArrayList<>();

        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                Page page = browser.newPage()
        ) {
            page.navigate("https://tiki.vn/");
            page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

            HomePage homePage = new HomePage(page);
            result = homePage.getCategories();
        }

        return result;
    }
}
