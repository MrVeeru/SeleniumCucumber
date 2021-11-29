package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BorrowingCalciSteps {

    WebDriver driver;
    String url = "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/";
    WebDriverWait wait;

    //Background
    @Given("Browser is launched")
    public void browser_is_launched() {
        System.setProperty("webdriver.chrome.driver","C://Users//mveer//IdeaProjects//LoanCalculator//drivers//chromedriver.exe");
        driver = new ChromeDriver();  //Initialising chrome browser instance
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("Loan calculator page is opened")
    public void loan_calculator_page_is_opened() {
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
    }

    //Scenario 1
    @When("The User provides the requested info")
    public void the_user_provides_the_requested_info() {

        //WebElement for Application type
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='application_type_single']"))).click();

        //No. of dependants
        WebElement drop1 = driver.findElement(By.xpath("//select[@title='Number of dependants']"));
        Select dd1 = new Select(drop1);
        dd1.selectByVisibleText("0");

        //Property type
        driver.findElement(By.id("borrow_type_home")).click();

        //Income textbox
        driver.findElement(By.xpath("//input[@aria-labelledby='q2q1']")).sendKeys("80000");

        //Other Income
        driver.findElement(By.xpath("//input[@aria-labelledby='q2q2']")).sendKeys("10000");

        //Living Expenses
        driver.findElement(By.xpath("//input[@aria-labelledby='q3q1']")).sendKeys("500");

        //Current home loan repayment
        driver.findElement(By.xpath("//input[@aria-labelledby='q3q2']")).sendKeys("");

        //Other Loan Repayments
        driver.findElement(By.xpath("//input[@aria-labelledby='q3q3']")).sendKeys("100");

        //Other Commitments
        driver.findElement(By.xpath("//input[@aria-labelledby='q3q4']")).sendKeys("");

        //Total Credit Card Limits
        driver.findElement(By.xpath("//input[@aria-labelledby='q3q5']")).sendKeys("10000");
    }

    @Then("Clicks on borrow estimation")
    public void Clicks_on_borrow_estimation() {
        driver.findElement(By.id("btnBorrowCalculater")).click();
    }

    // Scenario 2
    @And("Clicks the Start over button")
    public void clicks_the_start_over_button() {
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@id='borrowResultTextAmount']"))));
        driver.findElement(By.cssSelector("div[class='borrow__result text--white clearfix'] span[class='icon icon_restart']")).click();
    }

// Scenario 3
    @When("User enters incomplete details")
    public void user_enters_incomplete_details() {
        //Living Expenses
        driver.findElement(By.xpath("//input[@aria-labelledby='q3q1']")).sendKeys("10");
    }

    @Then("Gets an error message")
    public void gets_an_error_message() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='borrow__error__text']")).isDisplayed());
    }

    }
