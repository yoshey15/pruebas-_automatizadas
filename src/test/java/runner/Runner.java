package runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import pages.BasePage;

/**
 * Clase Runner para ejecutar las pruebas BDD con Cucumber
 * 
 * @RunWith(Cucumber.class) - Indica que esta clase ejecutará pruebas con Cucumber
 * 
 * @CucumberOptions - Configura dónde encontrar archivos .feature y steps:
 *   - features: directorio con los archivos .feature (escenarios BDD)
 *   - glue: paquete donde están los métodos que implementan los steps
 *   - plugin: define reportes a generar (pretty para consola, html para reporte web)
 */
@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",     // Directorio de archivos .feature
    glue = "steps",                        // Paquete con las clases de steps (Given, When, Then)
    plugin = { "pretty", "html:target/cucumber-reports" }) // Reportes en consola y HTML

public class Runner {
    
    /**
     * Método que se ejecutad DESPUÉS de todas las pruebas
     * Cierra el navegador y termina la sesión de Selenium
     */
    @AfterClass
    public static void cleanDriver() throws InterruptedException {
        Thread.sleep(4000); // Pausa para ver la prueba completada
        if (BasePage.getDriver() != null) {
            BasePage.closeBrowser();
        }
    }
}

