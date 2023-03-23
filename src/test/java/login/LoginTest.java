package login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

public class LoginTest {

    public static final String HTTP_LOCALHOST_8080_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new ChromeDriver();
        browser.navigate().to(HTTP_LOCALHOST_8080_LOGIN);

    }

    @AfterEach
    public void afterEach(){
        this.browser.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        browser.findElement(By.name("username")).sendKeys("fulano");
        browser.findElement(By.name("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals(HTTP_LOCALHOST_8080_LOGIN));
       // Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        browser.findElement(By.name("username")).sendKeys("invalido");
        browser.findElement(By.name("password")).sendKeys("123123");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        //Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    @Test

    public void naoDeveriaAcessarPaginaRestritaSemEstarlogado(){

        browser.navigate().to("http://localhost:8080/leiloes/2");
        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));






    }
}