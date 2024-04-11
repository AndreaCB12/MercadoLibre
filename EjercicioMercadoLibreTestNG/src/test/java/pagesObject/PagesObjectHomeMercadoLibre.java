package pagesObject;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import mapsObject.MapsObjectHomeMercadoLibre;

public class PagesObjectHomeMercadoLibre extends MapsObjectHomeMercadoLibre {

	// INSTANCIAR CONSTRUCTOR DE LA CLASE
	public PagesObjectHomeMercadoLibre(WebDriver driver) {
		super(driver);
	}

	// BUSCAR URL
	public void urlAccess(String url) {
		driver.get(url);
	}

	// ENTRAR A FORMS
	public void buscarProducto(String producto, File rutaFile) throws Exception {
		// ENVIAR LOS COMANDOS DE ACCION AL NAVEGADOR
		
		click(btnMasTarde, rutaFile);
		
		
		click(barraDeBusqueda, rutaFile);
		sendKey(producto, barraDeBusqueda, rutaFile);
		presionarEnter(barraDeBusqueda, rutaFile);
		

	}
}