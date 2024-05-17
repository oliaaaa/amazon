import org.amazon.config.TestRunner;
import org.amazon.pages.CartPage;
import org.amazon.pages.Homepage;
import org.amazon.pages.SearchResultPage;
import org.amazon.pages.ShoppingItemPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

public class AmazonTests extends TestRunner {
    DecimalFormat df = new DecimalFormat("#.##");

    @Test
    public void verifyAbilityToAddItemsToCartWithSpecificQuantities() {
        Homepage homepage = new Homepage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        ShoppingItemPage shoppingItemPage = new ShoppingItemPage(driver);
        CartPage cartPage = new CartPage(driver);

        homepage.open()
                .enterSearchPhrase("hats for men")
                .submitSearch();

        searchResultPage
                .openSearchResultItemByNumber(1);

        Double firstItemPrice = shoppingItemPage.getItemPrice();

        shoppingItemPage
                .selectQuantityOfItems(2)
                .addToCart();

        cartPage.open();

        Integer firstItemQuantityInCart = cartPage.getItemQuantity(1);

        Double subtotal = cartPage.getSubtotal();

        Assertions.assertEquals(2, firstItemQuantityInCart, "Quantity of items in Cart is incorrect." +
                "Expected quantity: '2'. " +
                "Actual quantity: '" + firstItemQuantityInCart + "'.");
        Assertions.assertEquals(Double.valueOf(df.format(firstItemPrice * 2)), subtotal, "Subtotal in Cart is incorrect." +
                "Expected subtotal: '" + Double.valueOf(df.format(firstItemPrice * 2)) + "'. " +
                "Actual subtotal: '" + subtotal + "'.");

        homepage.open()
                .enterSearchPhrase("hats for women")
                .submitSearch();

        searchResultPage
                .openSearchResultItemByNumber(1);

        Double secondItemPrice = shoppingItemPage.getItemPrice();

        shoppingItemPage
                .addToCart();

        cartPage.open();

        Integer secondItemQuantityInCart = cartPage.getItemQuantity(1);

        Double subtotalAfterAddingSecondItem = cartPage.getSubtotal();

        Assertions.assertEquals(1, secondItemQuantityInCart,
                "Quantity of items in Cart is incorrect." +
                        "Expected quantity: '1'. " +
                        "Actual quantity: '" + secondItemQuantityInCart + "'.");
        Assertions.assertEquals(Double.valueOf(df.format(firstItemPrice * 2 + secondItemPrice)), subtotalAfterAddingSecondItem,
                "Subtotal in Cart is incorrect." +
                        "Expected subtotal: '" + Double.valueOf(df.format(firstItemPrice * 2 + secondItemPrice)) + "'. " +
                        "Actual subtotal: '" + subtotal + "'.");
    }

    @Test
    public void verifyAbilityToChangeItemQuantityInCart() {
        Homepage homepage = new Homepage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        ShoppingItemPage shoppingItemPage = new ShoppingItemPage(driver);
        CartPage cartPage = new CartPage(driver);

        homepage.open()
                .enterSearchPhrase("hats for men")
                .submitSearch();

        searchResultPage
                .openSearchResultItemByNumber(1);

        Double itemPrice = shoppingItemPage.getItemPrice();

        shoppingItemPage
                .selectQuantityOfItems(2)
                .addToCart();

        cartPage.open();

        Integer itemQuantityInCart = cartPage.getItemQuantity(1);

        Double subtotal = cartPage.getSubtotal();

        Assertions.assertEquals(2, itemQuantityInCart, "Quantity of items in Cart is incorrect." +
                "Expected quantity: '2'. " +
                "Actual quantity: '" + itemQuantityInCart + "'.");
        Assertions.assertEquals(Double.valueOf(df.format(itemPrice * 2)), subtotal, "Subtotal in Cart is incorrect." +
                "Expected subtotal: '" + Double.valueOf(df.format(itemPrice * 2)) + "'. " +
                "Actual subtotal: '" + subtotal + "'.");

        cartPage
                .updateQuantityOfItemByNumber(1, 1)
                .open();

        Integer itemQuantityInCartAfterUpdate = cartPage.getItemQuantity(1);

        Double subtotalAfterQuantityUpdate = cartPage.getSubtotal();

        Assertions.assertEquals(1, itemQuantityInCartAfterUpdate,
                "Quantity of items in Cart is incorrect." +
                        "Expected quantity: '1'. " +
                        "Actual quantity: '" + itemQuantityInCartAfterUpdate + "'.");
        Assertions.assertEquals(itemPrice, subtotalAfterQuantityUpdate,
                "Subtotal in Cart is incorrect." +
                        "Expected subtotal: '" + itemPrice + "'. " +
                        "Actual subtotal: '" + subtotalAfterQuantityUpdate + "'.");
    }
}
