package org.example.pageobjects;

import org.apache.commons.io.FileUtils;
import org.example.pageobjects.testcomp.BaseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class StandAloneTest extends BaseTest {


    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void standalonetest(HashMap<String, String> input) {


        ProductCatalog pc = lp.loginApp(input.get("email"), input.get("password"));

        pc.selectProduct(input.get("product"));

        CartPage cp = pc.goToCart();

        Assert.assertEquals(cp.verifyProduct(input.get("product")), input.get("product"));
        cp.checkout();
        cp.selectCountry("Croatia");
        CheckoutPage cko = cp.submit();


        String poruka = cko.verifyOrder();
        Assert.assertEquals(poruka, "THANKYOU FOR THE ORDER.");


    }

    @Test(dependsOnMethods = "standalonetest",dataProvider = "getData")
    public void orderHistoryTest(HashMap<String, String> input) {

        ProductCatalog pc = lp.loginApp(input.get("email"), input.get("password"));
        OrderPage op = pc.goToOrder();
        Assert.assertEquals(input.get("product"), op.verifyOrder(input.get("product")));

    }





    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +
                "//src//test//java//org//example//pageobjects//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}};

    }


}



