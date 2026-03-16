package pages;

/**
 * Clase Page Object para la página de Cursos
 * Encapsula los elementos y acciones específicos de la sección de cursos
 * 
 * NOTA: Esta clase está parcialmente sin usar en el escenario actual
 * Se dejó preparada para futuros casos de prueba que requieran navegar a cursos
 */
public class PaginaCursos extends BasePage {

    // Localizador XPath del link "Introducción al Testing de Software"
    private String introduccionTestingLink = "//a[normalize-space()='Introducción al Testing de Software' and @href]";
    
    public PaginaCursos(){
        super(driver);
    }

    /**
     * Hace click en el link del curso "Introducción al Testing de Software"
     */
    public void clickOnIntroduccionTestingLink() {
        clickElement(introduccionTestingLink);
    } 
}
