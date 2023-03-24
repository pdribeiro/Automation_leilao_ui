package login;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    public static final String HTTP_LOCALHOST_8080_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    public LoginPage() {

        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        this.browser = new ChromeDriver();
        browser.navigate().to(HTTP_LOCALHOST_8080_LOGIN);

    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencheFormularioDeLogin(String username, String password) {
        browser.findElement(By.name("username")).sendKeys("username");
        browser.findElement(By.name("password")).sendKeys("password");
    }

    public void efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();

    }

    public boolean isPaginaDelogin() {
        return browser.getCurrentUrl().equals(HTTP_LOCALHOST_8080_LOGIN);
    }

    public void navegaParaPaginaDeLances() {
        browser.navigate().to("http://localhost:8080/leiloes/2");

    }

    public boolean contemTexto(String texto) {

        return browser.getPageSource().contains(texto);

    }

    public boolean isPaginaDeloginComDadosInvalidos() {

        return browser.getCurrentUrl().equals(HTTP_LOCALHOST_8080_LOGIN + "?error");

    }
}
