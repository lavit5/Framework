package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageobjects.CartPage;
import org.example.pageobjects.CheckoutPage;
import org.example.pageobjects.LoginPage;
import org.example.pageobjects.ProductCatalog;
import org.example.pageobjects.testcomp.BaseTest;
import org.testng.Assert;

import java.io.IOException;


public class StepDefine extends BaseTest {

    public LoginPage loginPage;
    public ProductCatalog pc;
    public CartPage cp;
    public CheckoutPage cko;

    @Given("I land on page")
    public void iLandOnPage() throws IOException {
      loginPage = launchApplication();
    }

    @Given("Logged in with {} and {}")
    public void loggedInWithAndPassword(String username, String password) {
        pc = lp.loginApp(username, password);
    }

    @When("I add the {} to Cart")
    public void iAddTheToCart(String product) {
        pc.selectProduct(product);
        cp = pc.goToCart();

    }

    @And("Checkout {} and submit order")
    public void andCheckoutAndSubmitOrder(String product) {
        Assert.assertEquals(cp.verifyProduct(product),product);
        cp.checkout();
        cp.selectCountry("Croatia");
        cko = cp.submit();
    }

    @Then("{string} should appear")
    public void thenThankYouForTheOrderShouldAppear(String message) {
        String poruka = cko.verifyOrder();
        Assert.assertEquals(poruka, message);
        driver.close();
    }

    @Then("{string} should be displayed")
    public void thenShouldBeDisplayed(String message) {
        Assert.assertEquals(lp.getErrorMessage(), message);
        driver.close();
    }




}
