package pagesObject;

import java.io.File;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import mapsObject.MapsObjectCuenta;


public class PagesObjectCuenta extends MapsObjectCuenta{
	
	//INSTANCIAR CONSTRUCTOR DE LA CLASE
	public PagesObjectCuenta(WebDriver driver) {
		super(driver);
	}
		
	//ENTRAR A PRACTICE
	public void crearCuenta(String correo, File rutaFile) throws Exception{
		//ENVIAMOS LOS COMANDOS DE ACCION AL NAVEGADOR
		click(btnCrearCuenta, rutaFile);
		click(btnCrearCuentaPersonal, rutaFile);
		click(btnAgregarEmail, rutaFile);
		click(checkAutorizarDatos, rutaFile);
		sendKey(correo, fieldIngresarCorreo, rutaFile);
		tiempoEspera(2000);
		WebElement elemento = driver.findElement(btnContinuar);;
		Assert.assertEquals(true, elemento.isDisplayed());
	}
}