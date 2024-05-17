package org.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingItemPage {

    protected WebDriver driver;

    public ShoppingItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public ShoppingItemPage selectQuantityOfItems(int quantity) {
        driver.findElement(By.cssSelector("#selectQuantity")).click();
        driver.findElements(By.cssSelector(".a-popover.a-dropdown ul li")).get(quantity - 1).click();
        return this;
    }

    public ShoppingItemPage addToCart() {
        driver.findElement(By.cssSelector("#add-to-cart-button")).click();
        return this;
    }

    public String getItemTitle() {
        return driver.findElement(By.cssSelector("#productTitle")).getText();
    }

    public Double getItemPrice() {
        return Double.valueOf(
                driver.findElement(By.cssSelector("#corePrice_feature_div"))
                        .getText()
                        .replace("$", "")
                        .replace("\n", "."));
    }
}
