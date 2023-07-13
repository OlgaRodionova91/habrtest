package com.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "D:\\Папка Оли\\тестировщик\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void changeLogTest() {

        WebElement userIcon = driver.findElement(By.cssSelector("svg[data-test-id='menu-toggle-guest']"));
        userIcon.click();

        WebElement rulesLink = driver.findElement(By.xpath("//*[contains(text(), \'Правила сайта\')]"));
        rulesLink.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), \'Changelog\')]")).isDisplayed(), "Changelog не найден");
    }

    @Test
    public void searchTest() {

        WebElement searchLoupe = driver.findElement(By.cssSelector("svg[class='tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search tm-header-user-menu__icon_dark']"));
        searchLoupe.click();

        WebElement inputSearchField = driver.findElement(By.cssSelector("input[placeholder=\"Поиск\"]"));
        inputSearchField.sendKeys("Selenium");

        WebElement searchLoupeInSearchField = driver.findElement(By.cssSelector("div[class=\"tm-input-text-decorated__label tm-input-text-decorated__label_after\"]"));
        searchLoupeInSearchField.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), \'Публикации\')]")).isDisplayed(), "Публикации не найдены");

    }

    @Test
    public void howBecameAuthorTest() {

        WebElement howBecameAuthorButton = driver.findElement(By.xpath("//a[contains(text(), 'Как стать автором')]"));
        howBecameAuthorButton.click();

        WebElement waitingInvitation = driver.findElement(By.xpath("//a[contains(text(),'Ожидают приглашения')]"));
        waitingInvitation.click();

        assertTrue(driver.findElement(By.cssSelector("h2[class='tm-block__title tm-block__title']")).isDisplayed(), "О песочнице не найдено");
    }
}
