package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Clase Base para todos los Page Objects
 * Contiene métodos reutilizables para interactuar con elementos web (clicks, escritura, selects, etc.)
 * Implementa el patrón Page Object para mantener la lógica de UI separada de la lógica de prueba
 * 
 * Responsabilidades:
 * - Inicializar WebDriver una sola vez (singleton pattern)
 * - Proporcionar métodos genéricos de interacción con elementos
 * - Manejar waits explícitos para sincronización
 * - Cerrar el navegador al finalizar las pruebas
 */
public class BasePage {

    // WebDriver estático compartido por todas las instancias de BasePage
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        if (driver != null) {
            BasePage.driver = driver;
        } else {
            initializeDriver();
        }
    }

    private static void initializeDriver() {
    if (driver == null) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        String ci = System.getenv("CI");
        if (ci != null && ci.equals("true")) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080"); // <-- esto
        }

        driver = new ChromeDriver(options); 
        driver.manage().window().maximize();
    }
}
    public static WebDriver getDriver() {
        initializeDriver();
        return driver;
    }

    /**
     * Crea un WebDriverWait de 30 segundos
     */
    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    }

    /**
     * Navega a una URL específica
     */
    public static void navigateTo(String url) {
        if (url == null) {
            throw new IllegalArgumentException("URL no puede ser null");
        }
        getDriver().get(url);
    }

    /**
     * Cierra el navegador y termina la sesión del WebDriver
     */
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Encuentra un elemento en la página usando un locator XPath
     */
    private WebElement find(String locator) {
        if (locator == null) {
            throw new IllegalArgumentException("El locator no puede ser null");
        }
        return getWait().until(
            ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    /**
     * Hace click en un elemento
     */
    public void clickElement(String locator) {
    WebElement element = find(locator);
        try {
        element.click();
        } catch (Exception e) {
        // Fallback con JavaScript si el click normal falla
        ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", element);
    }   
    }

    /**
     * Escribe texto en un campo de entrada
     */
    public void write(String locator, String keysToSend) {
        find(locator).clear();
        find(locator).sendKeys(keysToSend);
    }

    /**
     * Cambia el control del WebDriver a una nueva pestaña/ventana
     */
    public void switchToNewTab() {
        String currentTab = getDriver().getWindowHandle();
        for (String tab : getDriver().getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                getDriver().switchTo().window(tab);
                break;
            }
        }
    }

    /**
     * Selecciona una opción del dropdown por su atributo "value"
     */
    public void selectFromDropdownByValue(String locator, String value) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByValue(value);
    }

    /**
     * Selecciona una opción del dropdown por su índice
     */
    public void selectFromDropdownByIndex(String locator, Integer index) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByIndex(index);
    }

    public void selectFromDropdownByText(String locator, String index) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByVisibleText(index);
    }

    /**
     * Obtiene el número total de opciones disponibles en un dropdown
     */
    public int dropdownSize(String locator) {
        Select dropdown = new Select(find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }

    /**
     * Obtiene todos los valores (textos visibles) de las opciones de un dropdown
     */
    public List<String> getDropdownValues(String locator) {
        Select dropdown = new Select(find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement options : dropdownOptions) {
            values.add(options.getText());
        }
        return values;
    }
}