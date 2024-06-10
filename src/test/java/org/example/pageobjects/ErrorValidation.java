package org.example.pageobjects;

import org.example.pageobjects.testcomp.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {
    @Test(groups = "ErrorHandling", retryAnalyzer = org.example.pageobjects.testcomp.Retry.class)
    public void errorValidation() {

        lp.loginApp("burek2@burek.com", "Burekburek#1");
        Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");

    }
}
