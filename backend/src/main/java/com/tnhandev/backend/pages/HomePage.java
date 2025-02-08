package com.tnhandev.backend.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.tnhandev.backend.models.Category;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void typeKeyword(String keyword) {
        // Tìm kiếm sản phẩm theo từ khóa
        String searchInputSelector = "//input[@data-view-id='main_search_form_input']";
        this.page.locator(searchInputSelector).fill(keyword);
        this.page.locator(searchInputSelector).press("Enter");
    }

    public List<Category> getCategories() {
        List<Category> result = new ArrayList<>();
        String categoriesSelector = "//div[contains(@class,'StyledCategoryList')]//div[contains(@class,'StyledCategory')]";
        List<Locator> categoriesLocator = this.page.locator(categoriesSelector).all();

        for (Locator categoryLocator : categoriesLocator) {
            Locator rootCategoryLocator = categoryLocator.locator("//div/a");
            List<Locator> subCategoriesLocator = categoryLocator.locator("//p/a").all();

            Category newCategory = new Category();
            newCategory.setTitle(rootCategoryLocator.textContent());
            newCategory.setUrl(rootCategoryLocator.getAttribute("href"));

            List<Category> subCategory = new ArrayList<>();
            for (Locator subCategoryLocator : subCategoriesLocator) {
                subCategory.add(new Category(
                        subCategoryLocator.textContent(),
                        subCategoryLocator.getAttribute("href"),
                        new ArrayList<>()
                ));
            }

            newCategory.setSubCategories(subCategory);
            result.add(newCategory);
        }

        return result;
    }
}
