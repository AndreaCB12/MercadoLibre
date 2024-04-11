package mapsObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.ClaseBase;

public class MapsObjectVistaPreviaProducto extends ClaseBase {

	//INSTANCIAR CONSTRUCTOR DE LA CLASE
	public MapsObjectVistaPreviaProducto(WebDriver driver) {
		super(driver);
	}
	
	//MAPEAR ELEMENTOS DE VISTA PREVIA PRODUCTO
	protected By btnComprarAhora = By.xpath("(//*[@class='andes-button__content' and text()='Comprar ahora'])[1]");
	
}