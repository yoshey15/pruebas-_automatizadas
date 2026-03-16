package pages;

/**
 * Clase Page Object para la página principal de freerangetesters.com
 * Encapsula los elementos y acciones de la landing page
 */
public class PaginaPrincipal extends BasePage {

    // Localizador XPath genérico para cualquier link de sección en la barra de navegación
    // %s será reemplazado por el nombre de la sección (Academia, Cursos, etc.)
    private String sectionLink = "//a[normalize-space()='%s' and @href]";
    
    // Localizador XPath específico para el button/link "Recursos"
    private String elegirRecursosButton = "//a[normalize-space()='Recursos' and @href]";
    
    public PaginaPrincipal(){
        super(driver);
    }

    /**
     * Navega a la URL principal de freerangetesters.com
     */
    public void navigateToUrl(){
        navigateTo("https://www.freerangetesters.com/");
    } 

    /**
     * Hace click en el button/link "Recursos"
     * Navega a la sección de recursos del sitio
     */
    public void clickOnElegirRecursosButton(){
        clickElement(elegirRecursosButton);
    }
    
    /**
     * Hace click en una sección específica de la barra de navegación
     * @param section Nombre de la sección (Academia, Cursos, Mentorías, Talleres, etc.)
     */
    public void clickOnSectionNavigationBar(String section) {
        // Reemplaza el marcador %s en el XPath con el nombre de la sección proporcionada
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);
    } 
}
