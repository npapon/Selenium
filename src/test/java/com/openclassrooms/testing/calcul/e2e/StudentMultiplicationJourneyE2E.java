package com.openclassrooms.testing.calcul.e2e;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

}
