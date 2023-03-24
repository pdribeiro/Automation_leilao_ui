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

    private LoginPage paginaDeLogin;


    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();

    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();


        Assert.assertFalse(paginaDeLogin.isPaginaDelogin());
       // Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        paginaDeLogin.preencheFormularioDeLogin("invalido", "123");
        paginaDeLogin.efetuaLogin();

        Assert.assertTrue(paginaDeLogin.isPaginaDeloginComDadosInvalidos());
        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));


    }

    @Test

    public void naoDeveriaAcessarPaginaRestritaSemEstarlogado(){

        paginaDeLogin.navegaParaPaginaDeLances();
        Assert.assertTrue(paginaDeLogin.isPaginaDelogin());
        Assert.assertTrue(paginaDeLogin.contemTexto(""));



    }
}