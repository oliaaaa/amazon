package org.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
    protected WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchResultPage openSearchResultItemByNumber(int number) {
        driver.findElements(By.cssSelector("[data-component-type='s-search-results'] h2 a")).get(number - 1).click();
        return this;
    }
}
