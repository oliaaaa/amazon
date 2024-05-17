package org.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    protected WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage open() {
        driver.findElement(By.cssSelector("#nav-cart")).click();
        return this;
    }

    public String getItemTitleByNumber(int number) {
        return driver
                .findElements(By.cssSelector(".sc-list-item-content .a-spacing-mini.sc-item-product-title-cont"))
                .get(number - 1)
                .getText();
    }

    public Integer getItemQuantity(int number) {
        return Integer.valueOf(driver
                .findElements(By.cssSelector(".sc-list-item-content [data-a-class='quantity'] .a-dropdown-prompt"))
                .get(number - 1)
                .getText());
    }

    public Double getSubtotal() {
        return Double.valueOf(driver
                .findElement(By.cssSelector("#sc-subtotal-amount-activecart"))
                .getText()
                .replace("$", ""));
    }

    public CartPage updateQuantityOfItemByNumber(int number, int quantity) {
        driver
                .findElements(By.cssSelector("[data-feature-id='sc-update-quantity-select']"))
                .get(number - 1)
                .click();

        driver
                .findElement(By.cssSelector("#quantity_" + quantity)).click();

        return this;
    }


}
