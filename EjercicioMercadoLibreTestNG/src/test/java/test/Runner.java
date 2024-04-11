package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.WebDriver;

import base.ClaseBase;
import pagesObject.PagesObjectCuenta;
import pagesObject.PagesObjectHomeMercadoLibre;
import pagesObject.PagesObjectResultadosBusqueda;
import pagesObject.PagesObjectVistaPreviaProducto;
import utilidades.ExcelUtilidades;
import utilidades.GenerarReportePDF;
import utilidades.MyScreenRecorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Runner {

	// DECLARAR LAS VARIABLES GLOBALES
	private WebDriver driver;
	PagesObjectHomeMercadoLibre homeMercadoLibre;
	PagesObjectResultadosBusqueda resultadosBusqueda;
	PagesObjectVistaPreviaProducto vistaPreviaProducto;
	PagesObjectCuenta cuenta;

	Properties propiedades;
	ExcelUtilidades excelUtilidades;
	ClaseBase claseBase;

	public String nomTest;
	public File routeFolder;
	public String path;

	GenerarReportePDF generarReportePDF;

	public String name;
	public String lastname;
	public String email;
	public String phone;
	public String adress;
	public String state;
	public String city;

	@BeforeClass
	public void beforeClass() {
		// INSTANCIAR CLASE BASE
		claseBase = new ClaseBase(driver);
		// INSTANCIAR PAGINA DE HOME
		homeMercadoLibre = new PagesObjectHomeMercadoLibre(driver);
		// ISNTANCIAR CONEXION A GOOGLE
		driver = homeMercadoLibre.chromeDriverConnection();
		//INSTANCIAR RESULTADOS DE LA BUSQUEDA
		resultadosBusqueda = new PagesObjectResultadosBusqueda(driver);
		//INSTANCIAR VISTA PREVIA DEL PRODUCTO
		vistaPreviaProducto = new PagesObjectVistaPreviaProducto(driver);
		// INSTANCIAR PAGESFORM
		cuenta = new PagesObjectCuenta(driver);
		// INSTANCIAR LAS CLASES DE EXCEL
		//leer = new ReadExcelFile();
		//escribir = new WriteExcelFile();
		// INSTANCIAR PROPIEDADES-PROPERTIES
		propiedades = new Properties();
		InputStream entrada = null;
		try {
			entrada = new FileInputStream("./propiedades.properties");
			propiedades.load(entrada);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// ACCEDER A LA URL
		homeMercadoLibre.urlAccess(propiedades.getProperty("url"));
		// INSTANCIAR LA CLASE DE GENERAR REPORTES PDF
		generarReportePDF = new GenerarReportePDF();
		// ESTABLECER RUTA DE EVIDENCIAS
		path = propiedades.getProperty("outputFile");
		// EXTABLECER DONDE SE VA A GUARDAR EL PDF
		generarReportePDF.setRutaImagen(propiedades.getProperty("rutaImagenReporte"));

	}

	@DataProvider(name = "ListaProductos")
	public Object [][] listaProductos() throws IOException{
		Object [][] arregloDeProductos = ExcelUtilidades.getTableArray("./data/mercadoLibre.xlsx", "ListaDeProductos");
		return arregloDeProductos;
	}
	
	@Test(dataProvider = "ListaProductos")
	public void buscarProductoML(String producto, String correo) throws Exception {
		// OBTENER EL NOMBRE DEL METODO PARA NOMBRAR LA CARPETA DEL VIDEO
		String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		// CREAR LA CARPETA DONDE VAMOS A GUARDAR EL VIDEO
		File rutaFile = claseBase.crearCarpeta(propiedades, nomTest, path);
		// INICAR GRABADORA DE VIDEO
		MyScreenRecorder.startRecording(nomTest, rutaFile);
		// INICAR EVIDENCIA PDF
		generarReportePDF.crearPlantilla(nomTest, rutaFile);
		// ENTRAR A HOME MERCADO LIBRE
		homeMercadoLibre.buscarProducto(producto, rutaFile);
		//SELECCIONAR UN PRODUCTO DE LA BUSQUEDA
		resultadosBusqueda.seleccionarUnProducto(rutaFile);
		//AGREGAR AL CARRITO DE COMPRAS
		vistaPreviaProducto.agregarAlCarritoDeCompras(rutaFile);
		// CREAR CUENTA
		cuenta.crearCuenta(correo, rutaFile);
		// FINALIZAR EVIDENCIA PDF
		generarReportePDF.cerrarPlantilla();
		// FINALIZAR GRABADORA DE VIDEO
		MyScreenRecorder.stopRecording();
	}
	
	@AfterClass
	public void afterClass() {
		// CERRAR EL DRIVER CHROME
		driver.quit();
	}
}
