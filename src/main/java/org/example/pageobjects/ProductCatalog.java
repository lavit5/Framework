package org.example.pageobjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'mb-3')]")
    List<WebElement> lista;

    @FindBy(id = "toast-container")
    WebElement toster;

    By ImeProizvoda = By.xpath(".//b");

    By addToCart = By.cssSelector(".card-body button:last-of-type");




    public List<WebElement> getLista() {

        return lista;
    }

    public void selectProduct(String imeProizvoda) {
        WebElement kaput = getLista().stream().filter(proizvod -> proizvod.findElement(ImeProizvoda)
                .getText().equals(imeProizvoda)).findFirst().orElse(null);
        kaput.findElement(addToCart).click();
        waitForElementToDisappear(toster);

    }

}

