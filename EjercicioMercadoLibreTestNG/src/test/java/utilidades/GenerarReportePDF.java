package utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.By;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import base.ClaseBase;

public class GenerarReportePDF {
	static String nombre = "prueba";
	static String fecha;
	static Document documento;
	static FileOutputStream archivo;
	static Paragraph titulo;
	String rutaImagen;
	String hora;
	String horaIni, horaFin;

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public void crearPlantilla(String nomTest, File rutaCarpeta) {
		// INSTANCIAR DOCUMENTO
		documento = new Document();
		// TOMAR LA HORA DEL SISTEMA
		hora = ClaseBase.fechaHora();
		horaIni = ClaseBase.fechaHora2();

		try {
			// CREAR NOMBRE Y RUTA DEL PDF
			archivo = new FileOutputStream(rutaCarpeta + "\\" + "Reporte" + nomTest + "-" + hora + ".pdf");
			PdfWriter.getInstance(documento, archivo);
			// CREAR ENCABEZADO CON LA UBICACION DE LA IMAGEN
			Image header = Image.getInstance(rutaImagen);
			// DEFINIR TAMAÑO DE LA IAMGEN
			header.scaleToFit(100, 100);
			// ALINEAR IMAGEN A LA IZQUIERDA
			header.setAlignment(Chunk.ALIGN_LEFT);
			// ESTBLER ANCHO DE LA IMAGEN EN EL PDF
			header.setWidthPercentage(25);
			// CREAR TITULO DEL PDF
			titulo = new Paragraph(nomTest + "\n\n" + "Fecha inicio: " + horaIni);
			// ALINEAR TITULO
			titulo.setAlignment(1);
			// CREAR TABLA DE ENCABEZADO
			PdfPTable table = new PdfPTable(2);
			// ESTABLECER EL ANCHO DE LA TABLA EN EL PDF
			table.setWidthPercentage(100);
			// INSTANCIAR POSICIONES DE CELDAS EN EL PDF (POSICION 1)
			PdfPCell pos1 = new PdfPCell(header);
			// DEFINIR POSICION HORIZONTAL DE LA TABLA
			pos1.setHorizontalAlignment(1);
			// DEFINIR POSICION VERTICAL DE LA TABLA
			pos1.setVerticalAlignment(2);
			// INSTANCIAR POSICIONES DE CELDAS EN EL PDF (POSICION 2)
			PdfPCell pos2 = new PdfPCell(titulo);
			// DEFINIR POSICION HORIZONTAL DE LA TABLA
			pos2.setHorizontalAlignment(1);
			// DEFINIR POSICION VERTICAL DE LA TABLA
			pos2.setVerticalAlignment(2);
			// AGREGAR LAS VARIABLES POS1 Y POS2 A LA TABLA
			table.addCell(pos2);
			table.addCell(pos1);
			// ESTABLECER MARGENES EN EL DOCUMENTO
			documento.setMargins(30, 30, 30, 30);
			// ABRIR DOCUMENTO
			documento.open();
			// INSERTAR IMAGEN - TABLA
			documento.add(table);
			// AGREGAR UNA NUEVA LINEA AL PDF
			documento.add(Chunk.NEWLINE);

		}
		// MANEJO DE EXCEPCIONES
		catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (BadElementException e) {
			System.err.println(e.getMessage());
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("Error al logo de periferia" + e.getMessage());
		} catch (DocumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public void crearBody(By locator, String rutaImagen) throws DocumentException, MalformedURLException, IOException {
		// OBTENER EL NOMBRE DEL LOCALIZADOR
		String locator1 = locator.toString();
		// INSTANCIAR PARRAFO DE ITEXT
		Paragraph parrafo = new Paragraph();
		// ALINEAR A LA IZQUIERDA
		parrafo.setAlignment(Chunk.ALIGN_LEFT);
		// ESTABLECER FUENTE
		parrafo.setFont(FontFactory.getFont("Arial", 10, Font.NORMAL));
		// INDICAR DONDE SE ESTA REALIZANDO LA ACCION
		parrafo.add("Se realiza accion sobre el elemento: " + locator1);
		// ADICIONAR MENSAJE AL PDF
		documento.add(parrafo);
		// INSERTAR IMAGEN CON EL NOMBRE DE LA RUTA
		Image imagen = Image.getInstance(rutaImagen);
		// AGREGAR UN BORDE A LA IMAGEN
		imagen.setBorderColor(BaseColor.BLACK);
		// DEFINIR TAMAÑO DE LA IMAGEN
		imagen.scaleToFit(450, 450);
		// ALINEAR IMAGEN A LA IZQUIERDA
		imagen.setAlignment(Chunk.ALIGN_CENTER);
		// AGREGAR IMAGEN AL DOCUMENTO
		documento.add(imagen);
	}

	public void crearBodyError(By locator, String rutaImagen, String msnError)
			throws DocumentException, MalformedURLException, IOException {
		// OBTENER EL NOMBRE DEL LOCALIZADOR
		String locator1 = locator.toString();
		// INSTANCIAR PARRAFO DE ITEXT
		Paragraph parrafo = new Paragraph();
		// ALINEAR A LA IZQUIERDA
		parrafo.setAlignment(Chunk.ALIGN_LEFT);
		// ESTABLECER FUENTE
		parrafo.setFont(FontFactory.getFont("Arial", 10, Font.NORMAL));
		// INDICAR DONDE SE ESTA REALIZANDO LA ACCION
		parrafo.add("Se realiza accion sobre el elemento: " + locator1);
		// ADICIONAR MENSAJE AL PDF
		documento.add(parrafo);
		// INSERTAR IMAGEN CON EL NOMBRE DE LA RUTA
		Image imagen = Image.getInstance(rutaImagen);
		// AGREGAR UN BORDE A LA IMAGEN
		imagen.setBorderColor(BaseColor.BLACK);
		// DEFINIR TAMAÑO DE LA IMAGEN
		imagen.scaleToFit(150, 450);
		// ALINEAR IMAGEN A LA IZQUIERDA
		imagen.setAlignment(Chunk.ALIGN_LEFT);
		// AGREGAR IMAGEN AL DOCUMENTO
		documento.add(imagen);
		// SE CREA MENSAJE DE ERROR
		// INSTANCIAR PARRAFO DE ITEXT
		Paragraph parrafoError = new Paragraph();
		// ALINEAR A LA IZQUIERDA
		parrafoError.setAlignment(Chunk.ALIGN_LEFT);
		// ESTABLECER FUENTE
		parrafoError.setFont(FontFactory.getFont("Arial", 10, Font.NORMAL));
		// INDICAR DONDE SE ESTA REALIZANDO LA ACCION
		parrafoError.add("El mensaje de error: " + "\n" + msnError);
		// ADICIONAR MENSAJE AL PDF
		documento.add(parrafoError);
	}

	public void cerrarPlantilla() throws DocumentException {
		// AGREGAR UNA LINEA
		documento.add(Chunk.NEWLINE);
		// INSTANCIAR PARRAFO DE ITEXT
		Paragraph parrafo = new Paragraph();
		// ALINEAR A LA DERECHA
		parrafo.setAlignment(Chunk.ALIGN_RIGHT);
		// ESTABLECER FUENTE
		parrafo.setFont(FontFactory.getFont("Arial", 10, Font.NORMAL));
		// INDICAR LA HORA EN LA QUE SE COMIENZA EL PROCESO
		parrafo.add("Fecha inicio: " + horaIni + "\n");
		//INSTANCIAR HORA FIN CON EL METODO DE LA CLASE BASE (FECHAHORA2)
		horaFin = ClaseBase.fechaHora2();
		// INDICAR LA HORA EN LA QUE SE FINALIZA EL PROCESO
		parrafo.add("Fecha fin: " + horaFin);
		// ADICIONAR MENSAJE AL PDF
		documento.add(parrafo);
		//CERRAR PDF
		documento.close();
	}
}