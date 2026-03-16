package steps;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PaginaAutSand;
import pages.PaginaCursos;
import pages.PaginaRecursos;
import pages.PaginaPrincipal;

/**
 * Clase que define los pasos (steps) ejecutables del escenario BDD en Gherkin.
 * Cada método con anotación @Given, @When, @And, @Then mapea a un paso en el archivo .feature
 * 
 * Flujo del caso de prueba actual:
 * 1. Navega a freerangetesters.com (GIVEN)
 * 2. Selecciona la sección "Recursos" (WHEN)
 * 3. Abre "Automation Sandbox" en una nueva pestaña (AND)
 * 4. Valida que el dropdown contenga las opciones correctas (THEN)
 */
public class steps {

    // Instancias de las clases Page Object para interactuar con cada página
    PaginaPrincipal landingPage = new PaginaPrincipal();
    PaginaCursos cursosPage = new PaginaCursos();
    PaginaRecursos recursosPage = new PaginaRecursos();
    PaginaAutSand automationSandbox = new PaginaAutSand();

    @Given("I navigate to freerangetesters.com")
    public void iNavigateBLK(){
        // Navegar a la página principal de freerangetesters.com
        landingPage.navigateToUrl();
    }
        
    @When("I go to {word} using the navigation bar")
    public void navigationBarUse(String section) {
        // Hacer click en una sección específica de la barra de navegación
        // El parámetro {word} captura el nombre de la sección (Academia, Cursos, Mentorías, etc.)
        landingPage.clickOnSectionNavigationBar(section);
    }

        //lo que esta dentro de los when and etc.. debe ser igual que en el feature para que no se caiga la prueba ejemplo and
    @And("I Select Introducción al Testing de Software")  
        public void  navigateToIntro(){
            cursosPage.clickOnIntroduccionTestingLink();

        }

     @When("I select Recursos")
    public void elegirRecursos() {
        // Hacer click en el botón/link "Recursos" de la página principal
        landingPage.clickOnElegirRecursosButton();
    }

    @And("^(?:I|The client) select Automation Sandbox$")  
        public void navigateToAutSan(){
            // Hacer click en el link "Automation Sandbox" en la página de Recursos
            // Esto abre la página en una nueva pestaña del navegador
            recursosPage.clickOnAutomationSandboxLink();
            
            // Cambiar el control del WebDriver a la nueva pestaña
            recursosPage.switchToNewTab();
            
            // Esperar a que el contenido de la página se cargue completamente
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
    @Then("^(?:I|The client) can validate the options in the dropdown$")
        public void validateDropDown(){
            // Obtener todos los valores (opciones) del dropdown desde la página Automation Sandbox
            // El método returnDropdownValues() lee el elemento SELECT y extrae el texto de todas sus opciones
            List<String> Lista = automationSandbox.returnDropdownValues();
            
            // Definir la lista de valores esperados que debe contener el dropdown
            // Estos son los valores correctos que esperamos encontrar en el mismo orden
            List<String> ListaEsperada = Arrays.asList("Seleccioná un deporte", "Fútbol", "Tennis", "Basketball");
            
            // Comparar las dos listas: la obtenida del dropdown vs la esperada
            // Si son idénticas (mismo contenido y orden) -> La prueba PASA (✓)
            // Si son diferentes -> La prueba FALLA (✗) y muestra un error de aserción
            Assert.assertEquals(ListaEsperada, Lista);
        }
    
    
    
}   
