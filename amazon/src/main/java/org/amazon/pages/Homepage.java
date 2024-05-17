package org.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
    protected WebDriver driver;
    private static final String HOMEPAGE_URL = "https://www.amazon.com";

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public Homepage open() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public Homepage enterSearchPhrase(String searchPhrase) {
        WebElement input = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        input.clear();
        input.sendKeys(searchPhrase);
        return this;
    }

    public Homepage submitSearch() {
        driver.findElement(By.cssSelector("#nav-search-submit-button")).click();
        return this;
    }
}
