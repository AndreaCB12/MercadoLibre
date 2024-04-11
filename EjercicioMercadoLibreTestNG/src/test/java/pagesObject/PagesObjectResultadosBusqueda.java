package pagesObject;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import mapsObject.MapsObjectHomeMercadoLibre;

public class PagesObjectResultadosBusqueda extends MapsObjectHomeMercadoLibre {

	// INSTANCIAR CONSTRUCTOR DE LA CLASE
	public PagesObjectResultadosBusqueda(WebDriver driver) {
		super(driver);
	}

	// BUSCAR URL
	public void urlAccess(String url) {
		driver.get(url);
	}

	// ENTRAR A FORMS
	public void seleccionarUnProducto(File rutaFile) throws Exception {

		//presionarEnter(primerResultadoDeBusqueda, rutaFile);
		// Rango de valores para li[x]
		int inicio = 1;
		int fin = 10;
		By prueba = null;
		// Método para revisar la existencia de elementos web en el rango dado
		for (int i = inicio; i <= fin; i++) {
			prueba = By.xpath("/html/body/main/div/div[3]/section/ol/li["+i+"]/div/div/div[2]/div/a[1]");
			boolean visible = elementoVisible(prueba);
			if(visible) {
				break;
			}
		}
		click(prueba, rutaFile);

	}
}