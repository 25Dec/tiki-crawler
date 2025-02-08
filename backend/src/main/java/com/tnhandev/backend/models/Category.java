package com.tnhandev.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Category {
    @JsonProperty("label")
    private String title;

    @JsonProperty("data")
    private String url;

    @JsonProperty("children")
    private List<Category> subCategories;

    public Category() {
    }

    public Category(String title, String url, List<Category> subCategories) {
        this.title = title;
        this.url = url;
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
