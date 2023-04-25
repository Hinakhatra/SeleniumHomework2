package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuit {
    static String expectedRegistrationCompleteMsg ="Registration is not working";
    static String expectedSentEmailMsg ="Your Message has not been sent";
    static String expectedPollingResult ="Voting is not working";
    static String expectedResult = "Your Message has not been sent";
    static String expectedCompareProductMessage = "There are still 2-Product in ClearList";
    static String expectedConfirmProductMessage = "Product Name is not Match";
    static String expectedCommunityPollMessage = "Every user can vote";
    protected static WebDriver driver;
    public static void clickOnElement(By by){
        driver.findElement(by).click();
    }
    public static void typeText(By by,String text){
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();}
        @BeforeMethod
        public static void openBrowser () {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://demo.nopcommerce.com/");
            driver.manage().window().maximize();
        }
        @AfterMethod
        public static void closeBrowser () {
            driver.close();
        }
        // Verify User Should Be Able To Register Successfully //
        @Test
        public static void VerifyUserShouldBeAbleToRegisterSuccessfully(){
            openBrowser();
            clickOnElement(By.className("ico-register"));
            //type firstname
            typeText(By.id("FirstName"),"TestFirstName");
            //type lastname
            typeText(By.id("LastName"),"TestLastName");
            //type email address
            typeText(By.name("Email"),"testemail+"+timestamp() +"@gmail.com");
            //type password
            typeText(By.id("Password"),"test246");
            //type confirm password
            typeText(By.id("ConfirmPassword"),"test246");
            //click on register submit button
            clickOnElement(By.id("register-button"));
            //gettext from Web element
            String actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
            System.out.println("My message: "+actualMessage);
            Assert.assertEquals(actualMessage, expectedRegistrationCompleteMsg,"Registration is not working");
            //for close the browser
            closeBrowser();
        }
           // Verify Registered User Should Be Able To Refer A Product Successfully //
        @Test
        public static void VerifyRegisteredUserShouldBeAbleToReferAProductSuccessfully(){
            //open browser
            //openBrowser();
            //click on register
            clickOnElement(By.className("ico-register"));
            //type firstname
            typeText(By.id("FirstName"),"TestFirstName");
            //type lastname
            typeText(By.id("LastName"),"TestLastName");
            //type email address
            typeText(By.name("Email"),"hk123@gmail.com");
            //type password
            typeText(By.id("Password"),"test246");
            //type confirm password
            typeText(By.id("ConfirmPassword"),"test246");
            //click on register submit button
            clickOnElement(By.id("register-button"));

            //click on login
            clickOnElement(By.className("ico-login"));
            //type email
            typeText(By.name("Email"),"hk123@gmail.com");
            //type password
            typeText(By.id("Password"),"test246");
            //click on login button
            clickOnElement(By.xpath("//button[@class='button-1 login-button']"));
            //click on apple-macbook-pro-13-inch to refer a product
            clickOnElement(By.xpath("//a[@href=\"/apple-macbook-pro-13-inch\"]"));
            //click on email a friend
            clickOnElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"][text()=\"Email a friend\"]"));
            //type friend's email
            typeText(By.id("FriendEmail"),"rj12"+timestamp()+"@gmail.com");
            //type personal message
            typeText(By.id("PersonalMessage"),"Hi,I recommended this product to you,I used it,it's very good!");
            //clink on send email
            clickOnElement(By.xpath("//button[@class='button-1 send-email-a-friend-button']"));
            //print message
            String actualMessage;
            actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
            System.out.println("Actual Sent Email Message: "+actualMessage);
            Assert.assertEquals(actualMessage,expectedSentEmailMsg,"Your Message has not been sent");
            //for close the browser
            // closeBrowser();
    }
            // Verify Registered User Should Be Able To Vote Successfully //
        @Test
        public static void VerifyRegisteredUserShouldBeAbleToVoteSuccessfully(){
                //open browser
                //openBrowser();
                //click on register
                clickOnElement(By.className("ico-register"));
                //type firstname
                typeText(By.id("FirstName"),"TestFirstName");
                //type lastname
                typeText(By.id("LastName"),"TestLastName");
                //type email address
                typeText(By.name("Email"),"nk123@gmail.com");
                //type password
                typeText(By.id("Password"),"test246");
                //type confirm password
                typeText(By.id("ConfirmPassword"),"test246");
                //click on register submit button
                clickOnElement(By.id("register-button"));
                // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                //click on login
                clickOnElement(By.className("ico-login"));
                //type email
                typeText(By.name("Email"),"nk123@gmail.com");
                //type password
                typeText(By.id("Password"),"test246");

                //click on login
                clickOnElement(By.xpath("//button[@class='button-1 login-button']"));
                //click on Good
                clickOnElement(By.xpath("//input[@id='pollanswers-2']"));
                //click on vote
                clickOnElement(By.xpath("//button[@id='vote-poll-1']"));
                //print message
                String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
                System.out.println(" polling Result: "+actualMessage);
                Assert.assertEquals(actualMessage, expectedPollingResult,"Voting is not working");
                //for close the browser
                // closeBrowser();

    }
               // Verify Non Registered User Should Not Be Able To Email A Friend Successfully //
    @Test
    public static void VerifyNonRegisteredUserShouldNotBeAbleToEmailAFriendSuccessfully(){
        //open browser
        //openBrowser();
        //click on ADD TO CART BUTTON
        clickOnElement(By.xpath("//div[@class=\\\"item-grid\\\"]/div[2]/div/div[2]/div[3]/div[2]/button[1]\")).click();"));
        //click on Email a Friend
        clickOnElement(By.xpath("//div[@class='email-a-friend']"));
        //Type Friend's Email
        typeText(By.xpath("//input[@class='friend-email']"),"Test123@gmail.com");
        //Type Your Email address
        typeText(By.className("your-email"),"hk29@gmail.com");
        //Type personal Message
        typeText(By.id("PersonalMessage"),"product is very good");
        //click on send Email
        clickOnElement(By.name("send-email"));
        String errorMessage = getTextFromElement(By.xpath("//div[@class='message-error validation-summary-errors']/ul/li"));
        System.out.println("Error Message is: "+errorMessage);
        Assert.assertEquals(errorMessage,expectedResult,"Your Message has not been sent");
        //for close browser
        // closeBrowser();
    }
        // Verify User Should Be  Able To Compare 2 products Successfully //
    @Test
    public static void VerifyUserShouldBeAbleToCompare2productsSuccessfully(){
        //open browser
        //openBrowser();
        //click on HTC one M8 Android L 5.0 Lollipop compare button
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[3]/div[1]/div[2]/div[3]/div[2]/button[2]"));
        clickOnElement(By.xpath("//span[@class= \"close\"]"));
        //click on $25 virtual gift card compare button
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[4]/div[1]/div[2]/div[3]/div[2]/button[2]"));
        clickOnElement(By.xpath("//span[@class= \"close\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]")));
        //click on product comparison on green line
        clickOnElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]"));
        String actualMessage = getTextFromElement(By.xpath("//tr[@class=\"product-name\"]/td[2]/a"));
        System.out.println("Product Name: "+actualMessage);
        String actualMessage1 = getTextFromElement(By.xpath("//tr[@class='product-name']/td[3]/a"));
        System.out.println("Product Name: "+actualMessage1);
        //click on clear list

        clickOnElement(By.className("clear-list"));
        String message = getTextFromElement(By.className("no-data"));
        System.out.println("My message: "+message);
        Assert.assertEquals(message,expectedCompareProductMessage,"There are still 2-Product in ClearList");
        //for close browser
        // closeBrowser();
    }
       //Verify User Should Be  Able To See Product In ShoppingCart Successfully //
    @Test
    public static void VerifyUserShouldBeAbleToSeeProductInShoppingCartSuccessfully(){
        //open browser
        //openBrowser();
        //click on Electronics
        clickOnElement(By.xpath("//div[@class='master-wrapper-page']/div[2]/ul/li[2]/a"));
        //click on camera & photo
        clickOnElement(By.xpath("//a[@title='Show products in category Camera & photo']"));
        //Get product name
        String productName = getTextFromElement(By.xpath("(//h2[@class=\'product-title\'])[3]"));
        //print product name
        System.out.println("Print Product Name: "+productName);
        //click on ADD TO CART BUTTON
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[3]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //click on Shopping cart
        clickOnElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/cart\"]"));
        //Get same product name in shopping cart
        String productName1 = getTextFromElement(By.className("product-name"));
        //print same product name
        System.out.println("Print product name: "+productName1);
        Assert.assertEquals(productName1,expectedConfirmProductMessage,"Product Name is not Match");
        //for close browser
        // closeBrowser();

    }
       // Verify Non Registered User Should Not Be Able To Vote Successfully //
    @Test
    public static void VerifyNonRegisteredUserShouldNotBeAbleToVoteSuccessfully(){
        //open browser
        //openBrowser();
        //click on Good radio button
        clickOnElement(By.id("pollanswers-2"));
        //click on VOTE
        clickOnElement(By.id("vote-poll-1"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='poll-vote-error']")));
        String actualMessage = getTextFromElement(By.xpath("//div[@class='poll-vote-error']"));
        System.out.println("My message: "+actualMessage);
        Assert.assertEquals(actualMessage,expectedCommunityPollMessage,"Every user can vote");
        //for close browser
        // closeBrowser();
    }

    }










