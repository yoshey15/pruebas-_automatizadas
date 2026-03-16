package pages;

/**
 * Clase Page Object para la página de Recursos
 * Encapsula los elementos y acciones específicos de la sección Recursos del sitio
 */
public class PaginaRecursos extends BasePage {
    
    // Localizador XPath del link "Automation Sandbox"
    // Este link abre la página de pruebas en una nueva pestaña
    private String automationSandboxLink = "//a[normalize-space()='Automation Sandbox' and @href]";
    
    public PaginaRecursos(){
        super(driver);
    }

    /**
     * Hace click en el link "Automation Sandbox"
     * Abre la página en una nueva pestaña del navegador
     */
    public void clickOnAutomationSandboxLink() {
        clickElement(automationSandboxLink);
    }
}
