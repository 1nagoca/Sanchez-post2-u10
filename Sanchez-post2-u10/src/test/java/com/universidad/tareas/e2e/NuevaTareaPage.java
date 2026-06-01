package com.universidad.tareas.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NuevaTareaPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By CAMPO_TITULO      = By.id("titulo");
    private static final By CAMPO_DESCRIPCION = By.id("descripcion");
    private static final By BTN_GUARDAR       = By.id("btn-guardar");
    private static final By BTN_CANCELAR      = By.id("btn-cancelar");
    private static final By MENSAJE_ERROR     = By.cssSelector(".field-error");

    public NuevaTareaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public NuevaTareaPage ingresarTitulo(String titulo) {
        WebElement campo = driver.findElement(CAMPO_TITULO);
        campo.clear();
        campo.sendKeys(titulo);
        return this;
    }

    public NuevaTareaPage ingresarDescripcion(String descripcion) {
        WebElement campo = driver.findElement(CAMPO_DESCRIPCION);
        campo.clear();
        campo.sendKeys(descripcion);
        return this;
    }

    public TareasPage guardar() {
        driver.findElement(BTN_GUARDAR).click();
        return new TareasPage(driver);
    }

    public TareasPage cancelar() {
        driver.findElement(BTN_CANCELAR).click();
        return new TareasPage(driver);
    }

    public boolean tieneErrorDeValidacion() {
        return !driver.findElements(MENSAJE_ERROR).isEmpty();
    }


    public String obtenerUrl() {
        return driver.getCurrentUrl();
    }
}
