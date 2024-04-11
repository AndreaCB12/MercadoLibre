package mapsObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.ClaseBase;

public class MapsObjectHomeMercadoLibre extends ClaseBase {

	//INSTANCIAR CONSTRUCTOR DE LA CLASE
	public MapsObjectHomeMercadoLibre(WebDriver driver) {
		super(driver);
	}
	
	//MAPEAR ELEMENTOS DE HOME
	protected By barraDeBusqueda = By.xpath("//*[@id='cb1-edit']");
	protected By btnMasTarde = By.xpath("(//*[@class='andes-button__content'])[5]");
	protected By btnComprarAhora = By.xpath("(//*[@class='andes-button__content' and text()='Comprar ahora'])[1]");
	
}