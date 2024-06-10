package org.example.pageobjects;

import abstractComponents.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".cartSection h3")
    List<WebElement> izabraniProizvodi;

    @FindBy(css = ".totalRow button")
    WebElement selectCountry;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countrySelection;


    @FindBy(css = ".ta-item")
    WebElement countryItem;

    @FindBy(css = "a[class*='submit']")
    WebElement submitButton;


    By countryResultsBy = By.cssSelector(".ta-results");

    public String verifyProduct(String imeProizvoda) {
        WebElement izbpro = izabraniProizvodi.stream().
                filter(proizvod -> proizvod.getText().
                        equals(imeProizvoda)).findFirst().orElse(null);

        return izbpro.getText();
    }


    public void checkout() {
        selectCountry.click();
    }

    public void selectCountry(String country) {
        Actions a = new Actions(driver);
        a.sendKeys(countrySelection, country).perform();
        waitForElementToAppear(countryResultsBy);
        countryItem.click();

    }


    public CheckoutPage submit() {
        submitButton.click();
        return new CheckoutPage(driver);
    }


}
