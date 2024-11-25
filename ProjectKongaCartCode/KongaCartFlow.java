import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.Objects;

public class KongaCartFlow {
    private WebDriver driver;

    @BeforeTest()
    public void Start() throws InterruptedException {
        // locate where chromedriver is
        System.setProperty("webdriver.chrome.driver", "resource/chromedriver.exe");
        //open Chrome browser
        driver = new ChromeDriver();
        //Input Konga Url
        driver.get("https://www.konga.com/");

        //Test1: Verify that the user input the correct Url and on the right webpage
        if (Objects.requireNonNull(driver.getCurrentUrl()).contains("https://www.konga.com/"))
            // Pass
            System.out.println("Correct Webpage");
        else
             //Fail
            System.out.println("Wrong Webpage");
        Thread.sleep(2000);

        //Maximize page
        driver.manage().window().maximize();
        //click on the login/SignIn up option
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        //Test2 :Verify that when user click on the login button, he/she is directed to the login iFrame
        try {
            WebElement LoginFrame = driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[1]/div/h5"));

            Assert.assertTrue(LoginFrame.isDisplayed(), "Login iFrame should be title display");
            System.out.println("Passed : Login iFrame is displayed");
        } catch (NoSuchElementException e) {
            System.out.println("Failed: Login iFrame not displayed");
        }
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void LoginFlow() throws InterruptedException {
        // Login iFrame form with  Username/Email and password field
        WebElement usernameEmailField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        //Input  a valid email/phone number
        usernameEmailField.sendKeys("");
        //Input a valid password
        passwordField.sendKeys("");

        //Click the Login Button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")));
        loginButton.click();
        Thread.sleep(2000);
        System.out.println("Clicked on 'Login' button.");


        //Test 3: Verify that User Account is present after login

        try {
            // locate My Account

            WebElement myAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/div/a/span")));
            Assert.assertTrue(myAccount.isDisplayed(), "My Account should be displayed");
            System.out.println("Passed : Login Successful");
        } catch (TimeoutException e) {
            System.out.println("Failed: Login not successful");
        }

    }


    @Test(priority = 2)
    public void itemFilter1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click "All Categories" button
        WebElement allCategories = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[1]")));
        allCategories.click();
        System.out.println("Clicked on 'All Categories' button.");

        // Wait for the URL to contain the expected text
        String expectedUrl = "https://www.konga.com/#";
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        String actualUrl = driver.getCurrentUrl();

        // Test : Verify that All Categories are listed
        if (actualUrl != null) {
            Assert.assertTrue(actualUrl.contains(expectedUrl), "Failed: All Categories menu is not listed as expected.");
        }
        System.out.println("Passed: All Categories listed successfully.");
    }


    @Test(priority = 3)
    public void itemFilter2() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Click Computer and accessories option on the list
        WebElement ComputerAndAccessories = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"subFixId\"]/div/div[1]/ul/li[1]")));
        ComputerAndAccessories.click();
        System.out.println("Clicked on 'Computer and Accessories' button.");

        // Wait for the URL to contain the expected text
        String expectedUrl = "https://www.konga.com/category/computers-accessories-5227";
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        String actualUrl = driver.getCurrentUrl();

        // Test : Verify that when user click all Computer and accessories category are listed
        if (actualUrl != null) {
            Assert.assertTrue(actualUrl.contains(expectedUrl), "Failed: All Computer and accessories Categories menu is not listed as expected.");
        }
        System.out.println("Passed: All Computer and accessories Categories listed successfully.");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void itemFilter3() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // click on the Laptop option in the list
        WebElement LaptopsList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[4]/a/label/span")));
        LaptopsList.click();


        Thread.sleep(10000);
        System.out.println("Clicked on 'Laptop Category' button.");


     try {
            //Test : Verify that when user click on Laptops,A menu and a list of options is displayed
            WebElement Laptops = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[1]/div/div[1]/div[2]/h1"));
            Assert.assertTrue(Laptops.isDisplayed(), "Laptops category should be listed");
            Thread.sleep(5000);
            System.out.println("Passed : Laptops category listed successfully");
        } catch (TimeoutException e) {
            System.out.println("Failed: Laptops category not listed successfully");
        Thread.sleep(7000);
        }


    }

    @Test(priority = 5)
    public void itemFilter4() throws InterruptedException {

        // click on the Apple Macbook category  in the list
        WebElement AppleMacbook = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/div/section/div[2]/div[2]/ul/li[4]/a/ul/li[1]/a/label"));
        AppleMacbook.click();
        System.out.println("Clicked on 'MacBook Category' button.");
        Thread.sleep(5000);
        //Test : Verify that when user click on Laptops,A menu and a list of options is displayed
        try {
            WebElement AppleMacBook = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[1]/div/div[1]/div[2]/h1"));
            Assert.assertTrue(AppleMacBook.isDisplayed(), "Apple MacBook menu should be displayed");
            Thread.sleep(5000);
            System.out.println("Passed : Apple MacBook menu displayed");
        } catch (TimeoutException e) {
            System.out.println("Failed: Apple MacBook menu not displayed");
        }
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void CartFlow1() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // select a Macbook to add to cart
        WebElement MacbookPro = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/article/div[2]/a/h3")));
        MacbookPro.click();
        Thread.sleep(7000);
        System.out.println("Clicked on MacbookPro item");


        //Test : verify that when user click on Macbook Air 13, user is redirected to Macbook Air page
        try {

            WebElement MacBookPro = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[4]/div[1]/div/h1"));
            Assert.assertTrue(MacBookPro.isDisplayed(), "MacBookAir should be displayed");
            System.out.println("Passed : MacBook Pro is displayed");
        } catch (TimeoutException e) {
            System.out.println("Failed: Macbook Pro is not displayed");
        }
    }

    @Test(priority = 7)
    public void CartFlow2() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        //add Macbook Air 13 to cart
        WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[4]/div[3]/div[1]/div[1]/div/div[2]/div[2]/form/div[5]/div[1]/button"));
        AddToCart.click();
        Thread.sleep(7000);
        System.out.println("Clicked on 'Add To Cart' button");


        //Test : Verify that when user click on Add To cart button, the item is added to cart
        try {
            WebElement ShoppingCart = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[1]/div/h1"));
            Assert.assertTrue(ShoppingCart.isDisplayed(), "Shopping Cart overview should be displayed");
            System.out.println("Passed : Item added to cart");
        } catch (TimeoutException e) {
            System.out.println("Failed: Item not added to cart");
        }
        Thread.sleep(5000);


        // driver.findElement(By.xpath("//*[@id=\"js-cart-items-wrapper\"]/div/div/div[3]/form/div/button[1]/span")).click();

    }

    @Test(priority = 8)
    public void CartFlow3() throws InterruptedException {
        //Test : Verify that the Order Summary section is present
        try {
            WebElement Checkout = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/div/div[2]/div[4]/div/div[1]"));
            Assert.assertTrue(Checkout.isDisplayed(), "Order Summary should be displayed");
            System.out.println("Passed : Order Summary is displayed");
        } catch (TimeoutException e) {
            System.out.println("Failed: Order Summary is not displayed");

            Thread.sleep(5000);
        }
    }

    @Test(priority = 9)
    public void CartFlow4() throws InterruptedException {
        //click on "Continue to Checkout" button
        WebElement ContinueToCheckout = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/div/div[2]/div[4]/div/div[6]/div/a"));
        ContinueToCheckout.click();
        Thread.sleep(5000);

        //Test : Verify that when user click on Add To cart button, the item is added to cart
        try {
            WebElement Checkout = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[2]/section/main/div/div[1]/nav/h1"));
            Assert.assertTrue(Checkout.isDisplayed(), "Checkout page should open");
            System.out.println("Passed : Checkout page opened");
        } catch (TimeoutException e) {
            System.out.println("Failed: Checkout page not opened");
        }

        Thread.sleep(2000);
    }

    @Test(priority = 10)
    public void deliveryAddress1() throws InterruptedException {
        //click on "Change Address" button
        WebElement ChangeAddress = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button"));
        ChangeAddress.click();
        System.out.println("Clicked Change Address Button");
        Thread.sleep(5000);

        //Test : Verify that when user click on Change button, user is redirected to change address
        try {
            WebElement DeliverToMe = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[1]/h1"));
            Assert.assertTrue(DeliverToMe.isDisplayed(), "Add Address option should be displayed");
            System.out.println("Passed: Deliver to Me is visible");
        } catch (TimeoutException e) {
            System.out.println("Failed: Deliver to me not visible");
        }

        Thread.sleep(2000);
    }

    @Test(priority = 11)
    public void deliveryAddress2() throws InterruptedException {
        //click on "Add Delivery Address" button
        WebElement AddDeliveryAddress = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button"));
        AddDeliveryAddress.click();
        System.out.println("Clicked Add Delivery address Button");
        Thread.sleep(5000);

        //Test : Verify that when user click on Add Delivery Address button, Address Book iFrame is opened
        try {
            WebElement AddressBook = driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[1]/div/h5"));
            Assert.assertTrue(AddressBook.isDisplayed(), "Add Address option should be displayed");
            System.out.println("Passed: Address Book iFrame Opened ");
        } catch (TimeoutException e) {
            System.out.println("Failed: Address Book iFrame not Opened");
        }

        Thread.sleep(2000);
    }

    @Test(priority = 12)
    public void deliveryAddress3() throws InterruptedException {
        //Select already existing address
        WebElement SelectAddress = driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div[1]/form/button"));
        SelectAddress.click();
        System.out.println("Delivery Address Selected");
        Thread.sleep(5000);


        //Click on 'Use this Address button'
        WebElement UseAddress = driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a"));
        UseAddress.click();
        System.out.println("Clicked on 'Use this Address' button");
        Thread.sleep(5000);

        //Test : Verify that when user click on Use this Address button, the Address is selected
        try {
            WebElement DeliveryAddress = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]"));
            Assert.assertTrue(DeliveryAddress.isDisplayed(), "Delivery Address should be displayed");
            System.out.println("Passed: Delivery Address is displayed ");
        } catch (TimeoutException e) {
            System.out.println("Failed: Delivery Address is not displayed");
        }

        Thread.sleep(2000);
    }

    @Test(priority = 13)
    public void PaymentOptions1() throws InterruptedException {
        //Select PayNow option
        WebElement PayNow = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input"));
        PayNow.click();
        System.out.println("clicked PayNow option ");
        Thread.sleep(2000);

        //Click on Continue to payment button
        WebElement ContinuePayment = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button"));
        ContinuePayment.click();
        System.out.println("clicked on 'Continue to Payment' button ");
        Thread.sleep(30000);


        // Switch to iFrame
        WebElement PayMethod = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame("kpg-frame-component");
        Thread.sleep(7000);

        //Test : Verify that when user click on Continue to Payment button, the select payment method iFrame is opened
        try {
            WebElement DeliveryAddress = driver.findElement(By.xpath("//*[@id=\"channel-template\"]/div[1]"));
            Assert.assertTrue(DeliveryAddress.isDisplayed(), "Select payment method iframe should be opened");
            System.out.println("Passed: Select payment method iframe is opened");
        } catch (TimeoutException e) {
            System.out.println("Failed: Passed: Select payment method iframe is not opened");
        }

        Thread.sleep(3000);
    }


    @Test(priority = 14)
    public void PaymentOptions2() throws InterruptedException {

        //select card payment method
        WebElement cardPayment = driver.findElement(By.className("Card"));
        cardPayment.click();
        Thread.sleep(5000);
        System.out.println("Clicked on card payment option");

        //Test : Verify that when user click on card Payment option, the input card details form is opened
        try {
            WebElement CardDetailsForm = driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[3]/div/div/span[2]"));
            Assert.assertTrue(CardDetailsForm.isDisplayed(), "Enter Card Details form should be displayed");
            System.out.println("Passed: 'Enter Card Details' form is Displayed");
        } catch (TimeoutException e) {
            System.out.println("Failed: 'Enter Card Details' form is not Displayed");
        }

        Thread.sleep(2000);

    }

    @Test(priority = 15)
    public void inputCardDetails() throws InterruptedException {
        // Fill the Card Details form with an Invalid Card number
        WebElement cardDigit = driver.findElement(By.id("card-number"));
        Thread.sleep(3000);
        cardDigit.sendKeys("5566334");
        Thread.sleep(3000);

        //10b input date in its field
        WebElement dateDigit = driver.findElement(By.id("expiry"));
        Thread.sleep(3000);
        dateDigit.sendKeys("10/25");
        Thread.sleep(3000);

        // Input CVV in its field
        WebElement cvvDigit = driver.findElement(By.id("cvv"));
        cvvDigit.sendKeys("910");
        Thread.sleep(5000);
        System.out.println("input card details");

        //Click on Pay Now Button
        WebElement PayNowButton = driver.findElement(By.id("validateCardForm"));
        PayNowButton.click();
        Thread.sleep(2000);
        System.out.println("Clicked on Pay Now Button");

        //Test : Verify that when user click on card Pay Now button, error Message is displayed
        try {
            WebElement errorMessage = driver.findElement(By.id("card-number_unhappy"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error Message should be displayed");
            System.out.println("Passed: 'Invalid Card Number' is Displayed");
        } catch (TimeoutException e) {
            System.out.println("Failed: Error Message not found");
        }

        Thread.sleep(2000);
    }


    @Test(priority = 16)
    public void closePaymentModal() throws InterruptedException {
        WebElement ClosePaymentModal = driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[1]/aside"));
        ClosePaymentModal.click();
        Thread.sleep(7000);
        System.out.println("Clicked on close Card Details Modal");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Test : Verify that when user closed card details Modal, user is redirected back to Check Out page
        String expectedUrl = "https://www.konga.com/checkout/complete-order";
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl != null) {
            Assert.assertTrue(actualUrl.contains(expectedUrl), "Failed: Order placement made");
        }
        System.out.println("Passed: Order placement failed, place order again");
    }


    @AfterTest
    public void closerBrowser() {
        // quit browser
        driver.quit();
    }
}





