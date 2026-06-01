package com.universidad.tareas.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TareasE2ETest {

    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8080";

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless");
        opts.addArguments("--no-sandbox");
        opts.addArguments("--disable-dev-shm-usage");
        opts.addArguments("--disable-gpu");
        opts.addArguments("--window-size=1280,720");

        driver = new ChromeDriver(opts);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    @DisplayName("paginaTareas_cargaUrl_retornaTituloEsperado")
    void paginaTareas_cargaUrl_retornaTituloEsperado() {
        driver.get(BASE_URL + "/tareas");
        TareasPage page = new TareasPage(driver);

        assertThat(page.estaCargada()).isTrue();
        assertThat(driver.getTitle()).containsIgnoringCase("Tareas");
    }

    @Test
    @Order(2)
    @DisplayName("paginaTareas_navegarUrl_urlContieneRutaEsperada")
    void paginaTareas_navegarUrl_urlContieneRutaEsperada() {
        driver.get(BASE_URL + "/tareas");
        TareasPage page = new TareasPage(driver);

        assertThat(page.obtenerUrl()).contains("/tareas");
    }


    @Test
    @Order(3)
    @DisplayName("apiTareas_getEndpoint_paginaCargaSinErrores")
    void apiTareas_getEndpoint_paginaCargaSinErrores() {
        driver.get(BASE_URL + "/api/tareas");

        String source = driver.getPageSource();
        assertThat(source).doesNotContain("Whitelabel Error Page");
    }


    @Test
    @Order(4)
    @DisplayName("actuatorHealth_endpoint_retornaStatusUp")
    void actuatorHealth_endpoint_retornaStatusUp() {
        driver.get(BASE_URL + "/actuator/health");

        String source = driver.getPageSource();
        assertThat(source).contains("UP");
    }
}
