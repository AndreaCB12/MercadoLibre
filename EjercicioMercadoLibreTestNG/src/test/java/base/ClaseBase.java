package base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import com.itextpdf.text.DocumentException;

import utilidades.GenerarReportePDF;

public class ClaseBase {
	protected static WebDriver driver;

	// CLASS BUILDER
	public ClaseBase(WebDriver driver) {
		super();
	}

	// METHOD OF THE BROWSER
	public WebDriver chromeDriverConnection() {

		// SET OPTIONS BROWSER
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

		// SET BROWSER PROPERTIES
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();

		// MAXIMIZE BROWSER
		driver.manage().window().maximize();
		return driver;
	}

	// METHOD CLICK
	public void click(By locator, File rutaCarpeta) throws InterruptedException {
		try {
			driver.findElement(locator).click();
			captureScreen(rutaCarpeta, locator, "Si");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD CLICK2
	public void click2(By locator, File rutaCarpeta) throws InterruptedException {
		try {
			captureScreen(rutaCarpeta, locator, "Si");
			driver.findElement(locator).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD DELETE
	public void delete(By locator, File rutaCarpeta) {
		try {
			driver.findElement(locator).clear();
			captureScreen(rutaCarpeta, locator, "Si");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		 //METODO PARA TRAER EL TEXTO DE UN ELEMENTO
	
		
		
		
	}

	// METHOD SEND TEXT
	public void sendKey(String inputText, By locator, File rutaCarpeta) {
		try {
			driver.findElement(locator).sendKeys(inputText);
			captureScreen(rutaCarpeta, locator, "Si");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD PRESIONAR ENTER
	public void presionarEnter(By locator, File rutaCarpeta) {
		try {
			driver.findElement(locator).sendKeys(Keys.ENTER);
			captureScreen(rutaCarpeta, locator, "Si");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD LISTAR MES
	public void listarTexto(String inputText, By locator) {
		try {
			Select lista = new Select(driver.findElement(locator));
			lista.selectByVisibleText(inputText);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD SUBMIT
	public void submit(By locator, File rutaCarpeta) {
		try {
			driver.findElement(locator).submit();
			captureScreen(rutaCarpeta, locator, "Si");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// METHOD STAND BY TIME
	public void tiempoEspera(long time) throws InterruptedException {
		Thread.sleep(time);
	}

	// METHOD GET DATE FOR SCREENSHOT
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	// METODO SCROLL (SUBIR Y BAJAR CON LA BARRA ESPACIADORA LATERAL DEL NAVEGADOR)
	public static void scrollWeb(int y, int numMovimiento) throws InterruptedException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i <= numMovimiento; i++) {
				js.executeScript("window.scrollBy(0," + y + ")");
				// standByTime(i);
				// ScreenShot(rutaCarpeta, generarEvidencia, steps);
			}
		} catch (Exception e) {
			throw new InterruptedException();
		}
	}

	// METODO OBTENER FECHA Y HORA PARA LOS PANTALLAZOS
	public static String fechaHora() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}

	// METODO PARA OBTENER LA HORA DEL SISTEMA PARA LOS PANTALLAZOS
	public String HoraSistema() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalTime horaSistema = LocalTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("HHmmss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String hora = fecha.format(horaSistema);
		return hora;
	}

	// METODO PARA TOMAR PANTALLAZOS
	public void captureScreen(File rutaCarpeta, By locator, String generarEvidencia) throws Exception {
		if (generarEvidencia.equals("Si")) {
			String hora = HoraSistema();
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(rutaCarpeta + "\\" + hora + ".png"));
			String rutaImagen = new File(rutaCarpeta + "\\" + hora + ".png").toString();
			// INSTANCIAR LA CLASE GENERAR PDF
			GenerarReportePDF informePDF = new GenerarReportePDF();
			// INSERTAR LOCALIZADOR DE LA IMAGEN EN EL PDF
			informePDF.crearBody(locator, rutaImagen);
			// ELIMINAR IMAGEN CREADA
			eliminarArchivo(rutaImagen);
		}
	}

	// METODO PARA CREAR CARPETA PARA GUARDAR LOS PANTALLAZOS
	public File crearCarpeta(Properties propiedades, String nomTest, String path) {
		// ALMACENAMOS LA FECHA DEL SISTEMA
		String fecha = fechaHora();
		// CREAMOS EL NOMBRE DE LA CARPETA
		String nomCarpeta = nomTest + "-" + fecha;
		// OBTENIENDO LA RUTA DE ALOJAMIENTO DE SALIDA Y EL NOMBRE DEL TEST A EJECUTAR
		File directorio = new File(path + nomCarpeta);
		// CREAMOS LA CARPETA
		directorio.mkdir();
		return directorio;
	}

	// METODO OBTENER FECHA Y HORA PARA EL PDF
	public static String fechaHora2() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}

	// METODO ELIMINAR PANTALLAZOS
	public void eliminarArchivo(String rutaImagen) {
		// SE INSTANCIA LA CLASE FILE DE JAVA UTIL
		File fichero = new File(rutaImagen);
		// SE DEFINE EL METODO BORRAR
		fichero.delete();
	}

	public boolean elementoVisible(By locator) {
		boolean elementoVisible = false;
		// Localizar el elemento que deseas verificar si es clickeable
		WebElement elemento = null;
		try {
			elemento = driver.findElement(locator);
			if (elemento.isEnabled() && elemento.isDisplayed()) {
				// elemento.click();
				elementoVisible = true;
			}
		} catch (Exception fallo) {

		}
		System.out.println("Elemento : " + elementoVisible + " " + locator.toString());
		return elementoVisible;
	}

//	@Rule
//	public TestRule watcher = new TestWatcher() {
//		@Override
//		protected void failed(Throwable throwable, Description description) {
//			File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			try {
//				FileUtils
//			} catch (Exception e) {
//				// System.out.println(e.getMessage());
//			}
//		}
//	};

}
