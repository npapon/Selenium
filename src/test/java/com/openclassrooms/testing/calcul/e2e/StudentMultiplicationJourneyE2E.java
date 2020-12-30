package com.openclassrooms.testing.calcul.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith( SpringExtension.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class StudentMultiplicationJourneyE2E {
    @LocalServerPort
    private Integer   port;
    private WebDriver webDriver = null;
    private String    baseUrl;

    @BeforeAll
    public static void setUpFireFoxDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpWebDriver() {
        webDriver = new ChromeDriver();
        // http://localhost:8080/calculator
        baseUrl = "http://localhost:" + port + "/calculator";
    }

    @AfterEach
    public void quitWebDriver() {
        if ( webDriver != null ) {
            webDriver.quit();
        }
    }

    @Test
    public void lutilisateurUtiliseLeCalculateurPourMultiplier2par16() {
        // GIVEN
        webDriver.get( baseUrl );
        WebElement leftField = webDriver.findElement( By.id( "left" ) );
        WebElement rightField = webDriver.findElement( By.id( "right" ) );
        WebElement typeDropButton = webDriver.findElement( By.id( "type" ) );
        WebElement submitButton = webDriver.findElement( By.id( "submit" ) );

        // WHEN
        leftField.sendKeys( "2" );
        rightField.sendKeys( "16" );
        typeDropButton.sendKeys( "x" );
        submitButton.click();

        // THEN

        final WebDriverWait attendUneReponse = new WebDriverWait( webDriver, 5 );
        WebElement solutionElement = attendUneReponse.until( ExpectedConditions.presenceOfElementLocated( By.id( "solution" ) ) );
        String solution = solutionElement.getText();
        assertThat( solution ).isEqualTo( "32" );

    }

}
