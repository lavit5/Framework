package org.example.pageobjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "userEmail")
    WebElement username;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    By errorMessageBy = By.cssSelector("[class*='flyInOut']");

    public String getErrorMessage() {
        waitForElementToAppear(errorMessageBy);
        return errorMessage.getText();

    }

    public ProductCatalog loginApp(String email, String passworde) {
        username.sendKeys(email);
        password.sendKeys(passworde);
        loginButton.click();
        return new ProductCatalog(driver);
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}

