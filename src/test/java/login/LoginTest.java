package login;

import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @BeforeEach
    public void beforeEach(){


    }

    @Test
    public void  deveriaEfetuarLoginComDadosValidos(){
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.name("username")).sendKeys("fulano");
        browser.findElement(By.name("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        //Assert.assertEquals("fulano",browser.findElement(By.id("usuario-logado")).getText());

        browser.quit();

    }

    @Test

    public void naoDeveriaLogarComDadosInvalidos(){

        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.name("username")).sendKeys("teste");
        browser.findElement(By.name("password")).sendKeys("teste");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue("fulano",browser.getPageSource().contains("Usuário e senha inválidos."));


        browser.quit();


    }


}
