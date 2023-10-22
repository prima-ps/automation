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

public class dashboard {
    WebDriver driver;
    String baseURL = "https://www.saucedemo.com/";

    @Given("user di halaman dashboard")
    public void user_di_halaman_dashboard() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL); //Membuka halaman login
        String loginpage = driver.findElement(By.xpath("//*[@id=\"login_credentials\"]/h4")).getText();
        Assert.assertEquals(loginpage, "Accepted usernames are:");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("user klik barang")
    public void user_klik_barang() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
    }

    @Then("keranjang bertambah")
    public void keranjang_bertambah() {
        // Write code here that turns the phrase above into concrete actions
        String item = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
        Assert.assertEquals(item, "1");
    }
    @And("user klik barang lain")
    public void user_klik_barang_lain() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();


    }
    @When("user remove barang sebelumnya")
    public void user_remove_barang_sebelumnya() {
        driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).click();
    }
    @Then("keranjang berkurang")
    public void keranjang_berkurang() {
        String item2 = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
        Assert.assertEquals(item2, "2");
    }

    @When("user klik tiga titik")
    public void user_klik_tiga_titik() {
        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @When("user klik logout")
    public void user_klik_logout() {
        driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();
    }

    @Then("user kembali ke halaman login")
    public void user_kembali_ke_halaman_login() {
        // Write code here that turns the phrase above into concrete actions
        String loginpage = driver.findElement(By.xpath("//*[@id=\"login_credentials\"]/h4")).getText();
        Assert.assertEquals(loginpage,"Accepted usernames are:");
    }

    @Then("user di halaman checkout")
    public void user_di_halaman_checkout() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String checkout = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[2]")).getText();
        Assert.assertEquals(checkout, "Description");


    }

    @And("user klik keranjang")
    public void user_klik_keranjang() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

    }

    @Then("user tetap halaman dashboard")
    public void user_tetap_halaman_dashboard() {
        // Write code here that turns the phrase above into concrete actions
        String loginpage = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(loginpage,"Products");
    }

}