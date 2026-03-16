package pages;

import java.util.List;

/**
 * Clase Page Object para la página Automation Sandbox
 * Encapsula los elementos y acciones específicos de la página de pruebas Automation Sandbox
 */
public class PaginaAutSand extends BasePage {

    // Localizador XPath del elemento SELECT que contiene las opciones del dropdown
    // Este dropdown permite seleccionar un deporte
    private String dropDown ="//select[@id='formBasicSelect']";

    public PaginaAutSand(){
        super(driver);
    }
    
    /**
     * Obtiene todos los valores (opciones) disponibles en el dropdown
     * @return Lista de strings con el texto visible de cada opción del dropdown
     */
    public List<String> returnDropdownValues(){
         return getDropdownValues(dropDown);
    }
}
