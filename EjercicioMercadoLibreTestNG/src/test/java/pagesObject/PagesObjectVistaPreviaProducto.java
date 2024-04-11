package pagesObject;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import mapsObject.MapsObjectHomeMercadoLibre;

public class PagesObjectVistaPreviaProducto extends MapsObjectHomeMercadoLibre {

	// INSTANCIAR CONSTRUCTOR DE LA CLASE
	public PagesObjectVistaPreviaProducto(WebDriver driver) {
		super(driver);
	}

	// BUSCAR URL
	public void urlAccess(String url) {
		driver.get(url);
	}

	// ENTRAR A FORMS
	public void agregarAlCarritoDeCompras(File rutaFile) throws Exception {
		//AGREGAR EL PRODUCTO AL CARRITO DE COMPRAS
		click(btnComprarAhora, rutaFile);
	}
}