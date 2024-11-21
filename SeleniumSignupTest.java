import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class SeleniumSignupTest {
    private WebDriver driver;

    @BeforeTest ()
    public void setUp() throws InterruptedException {
        //locate where the chromedriver is
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        // 1. open Chrome browser
        driver = new ChromeDriver();
        // 2. Input url
        driver.get("https://selenium-blog.herokuapp.com/");

        //Test1: Verify the user input the right url and on the right webpage
        if(Objects.requireNonNull(driver.getCurrentUrl()).contains("https://selenium-blog.herokuapp.com/"))
            //pass
            System.out.println("Correct Webpage");
        else
            //fail
            System.out.println("Wrong Webpage");
            Thread.sleep(5000);

        // 3. Maximize page
        driver.manage().window().maximize();

        // 4. click on signup button
        driver.findElement(By.xpath("/html/body/div[2]/div/a[2]")).click();
        //Test2: verify that when user clicks on the signup button, the user is directed to the signup page
        String expectedUrl = "https://selenium-blog.herokuapp.com/signup";
        String actualUrl = driver.getCurrentUrl();
                if(actualUrl.contains(expectedUrl))
                    System.out.println("Correct Webpage");
                else
                    //fail
                    System.out.println("Wrong Webpage");
     Thread.sleep(5000);
    }


      @Test (priority = 1)
      public void negativeSignup1() throws InterruptedException {
          // Fill SignUp form with username less than 3 characters (minimum)
          WebElement usernameField = driver.findElement(By.id("user_username"));
          WebElement emailField = driver.findElement(By.id("user_email"));
          WebElement passwordField = driver.findElement(By.id("user_password"));


          usernameField.sendKeys("ha");
          emailField.sendKeys("habbiab@mailinator.com");
          passwordField.sendKeys("october11");

          WebElement signupButton = driver.findElement(By.id("submit"));
          signupButton.click();
          try {
              WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/ul/li"));
              //Test3 : Verify that user cannot signUp with username less than 3 characters
              Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
              System.out.println("Passed : Error message is displayed");
          } catch (NoSuchElementException e) {
              System.out.println("Failed: Error Message not found");
          } finally {
              clearField(driver);
          }
          Thread.sleep(5000);
       }


    @Test (priority = 1)
    public void negativeSignup2() throws InterruptedException {
        // leave username field blank
        WebElement usernameField = driver.findElement(By.id("user_username"));
        WebElement emailField = driver.findElement(By.id("user_email"));
        WebElement passwordField = driver.findElement(By.id("user_password"));


        usernameField.sendKeys("");
        emailField.sendKeys("habbiab@mailinator.com");
        passwordField.sendKeys("october11");

        WebElement signupButton = driver.findElement(By.id("submit"));
        signupButton.click();
        try {
            WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/ul/li"));
            //Test3 : Verify that user cannot signUp with blank username field
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
            System.out.println("Passed : Error message is displayed");
        } catch (NoSuchElementException e) {
            System.out.println("Failed: Error Message not found");
        } finally {
            clearField(driver);
        }
        Thread.sleep(5000);
    }

    @Test (priority = 2)
    public void negativeSignup3() throws InterruptedException {
        // Fill SignUp form with wrong email format
        WebElement usernameField = driver.findElement(By.id("user_username"));
        WebElement emailField = driver.findElement(By.id("user_email"));
        WebElement passwordField = driver.findElement(By.id("user_password"));


        usernameField.sendKeys("bagghh");
        emailField.sendKeys("ivanny@mailinator");
        passwordField.sendKeys("october11");

        WebElement signupButton = driver.findElement(By.id("submit"));
        signupButton.click();
        try {
            WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/ul/li[1]"));
            //Test4 : Verify that user cannot signUp with wrong email format
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
            System.out.println("Passed : Error message is displayed");
        } catch (NoSuchElementException e) {
            System.out.println("Failed: Error Message not found");
        } finally {
            clearField(driver);
        }
        Thread.sleep(5000);
    }

      private void clearField(WebDriver driver) {
        driver.findElement(By.id("user_username")).clear();
        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_password")).clear();
    }


    @Test (priority = 3)
    public void negativeSignup4() throws InterruptedException {
        // Fill SignUp form with blank password
        WebElement usernameField = driver.findElement(By.id("user_username"));
        WebElement emailField = driver.findElement(By.id("user_email"));
        WebElement passwordField = driver.findElement(By.id("user_password"));


        usernameField.sendKeys("bagghh");
        emailField.sendKeys("habbiab@mailinator.com");
        passwordField.sendKeys("");

        WebElement signupButton = driver.findElement(By.id("submit"));
        signupButton.click();
        try {
            WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/ul/li"));
            //Test5 : Verify that user cannot signUp with blank password
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
            System.out.println("Passed : Error message is displayed");
        } catch (NoSuchElementException e) {
            System.out.println("Failed: Error Message not found");
        } finally {
            clearField(driver);
        }
        Thread.sleep(5000);
    }

    @Test (priority = 4)
    public void positiveSignUp() throws InterruptedException  {
              // fill signup form
        WebElement usernameField = driver.findElement(By.id("user_username"));
        WebElement emailField = driver.findElement(By.id("user_email"));
        WebElement passwordField = driver.findElement(By.id("user_password"));


        usernameField.sendKeys("Raphael" + System.currentTimeMillis());
        emailField.sendKeys("Tester" + System.currentTimeMillis() + "@mailinator.com");
        passwordField.sendKeys("october11");

        WebElement signupButton = driver.findElement(By.id("submit"));
        signupButton.click();

              try {
                  // locate welcome message
                  WebElement welcomeMessage = driver.findElement(By.id("flash_success"));

                  //Test6: verify the welcome message is as expected
                  Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message should be displayed");
                  System.out.println("Passed : Welcome message is displayed");
              } catch (NoSuchElementException e) {
                  System.out.println("Failed: Welcome message not found");
              }

           Thread.sleep(5000);
          }



    @Test (priority = 5)
    public  void checkMeaghanItem() throws InterruptedException {
        // confirm "Meaghan" Item is present
        WebElement meaghanItems = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/div/div/li[1]/a"));

        //test7 : verify that "Meaghan" item is present
        Assert.assertTrue(meaghanItems.isDisplayed(), "The item 'Meaghan' should be present on the list.");
        System.out.println("Meaghan is present");





        Thread.sleep(5000);

    }

    @Test (priority = 6)
    public  void clickMeaghanItem() throws InterruptedException {
        //Test8 : Verify that "Meaghan" item can be selected on the list page
        driver.get("https://selenium-blog.herokuapp.com/users/1");

        if(Objects.requireNonNull(driver.getCurrentUrl()).contains("https://selenium-blog.herokuapp.com/"))
            //pass
            System.out.println("Meaghan is clickable");
            else
                //fail
            System.out.println("Meaghan is not clickable");
            Thread.sleep(5000);


          }
        @Test (priority = 7)
        public void verifyItem() throws InterruptedException {
            // Test 9. search for an item (Using Python with Selenium) and confirm if it is present and clickable
            driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[1]/a")).click();

            String expectedUrl = "https://selenium-blog.herokuapp.com/articles/9";
            String actualUrl = driver.getCurrentUrl();

            if(Objects.requireNonNull(driver.getCurrentUrl()).contains("https://selenium-blog.herokuapp.com/"))
                //pass
                System.out.println("Meaghan is clickable");
            else
                //fail
                System.out.println("Meaghan is not clickable");
            Thread.sleep(5000);


            Thread.sleep(5000);
        }

        @Test (priority = 8)
        public void clickLogout() throws InterruptedException {
             //Test10. click on logout
             driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[3]/a")).click();

             String expectedTitle = "AlphaBlog";
             String actualTitle = driver.getTitle();
            if(Objects.requireNonNull(driver.getTitle()).contains("AlphaBlog"))
                //pass
                System.out.println("Logged Out");
            else
                //fail
                System.out.println("Wrong Webpage");

             Thread.sleep(5000);

          }





     @AfterTest
     public void closeBrowser() {
            // quit the browser
            driver.quit();
       }
}
