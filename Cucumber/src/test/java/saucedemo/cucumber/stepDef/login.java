package saucedemo.cucumber.stepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class login {
    WebDriver driver;
    String baseURL = "https://www.saucedemo.com/";


    @Given("user di halaman login")
    public void user_di_halaman_login() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL); //Membuka halaman login
        String loginpage = driver.findElement(By.xpath("//*[@id=\"login_credentials\"]/h4")).getText();
        Assert.assertEquals(loginpage,"Accepted usernames are:");
    }

    @When("input username")
    public void input_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //throw new io.cucumber.java.PendingException();
    }

    @And("input password")
    public void input_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //throw new io.cucumber.java.PendingException();
    }

    @And("klik login button")
    public void klik_login_button() {
        driver.findElement(By.id("login-button")).click();
        //throw new io.cucumber.java.PendingException();
    }

    @Then("user masuk ke dashboard")
    public void user_masuk_ke_dashboard() {
        String Success = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(Success,"Products");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.close();

    }

    @And("input invalid password")
    public void input_invalid_password() {
        driver.findElement(By.id("password")).sendKeys("secret_saus");
    }

    @Then("user get error message")
    public void user_get_error_message() {
        String fail_message = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(fail_message, "Epic sadface: Username and password do not match any user in this service");
        //throw new io.cucumber.java.PendingException();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.close();
    }

    @When("input username diblock")
    public void input_username_diblock() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
    }

    @Then("user dapat error")
    public void user_dapat_error() {
        String error_message = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(error_message, "Epic sadface: Sorry, this user has been locked out.");
        //throw new io.cucumber.java.PendingException();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.close();
    }
}
