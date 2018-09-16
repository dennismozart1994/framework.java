package utility;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import properties.Config;

public class PDFCreator {
	private static Document document;
	private static PdfWriter writer;
	
	public static void createPDF(String ProjectName, String TestCaseName, String Objective, String Environment, String Sprint, String ExecutionDate, String Result) throws DocumentException, MalformedURLException, IOException
	{
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		String time = new SimpleDateFormat("hh_MM_ss").format(new Date());
		String filePath = System.getProperty("user.dir") + "/evidences/" + TestCaseName + "_date_" + date + "_time_" + time + ".pdf";
		document = new Document();
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		document.open();
		document.add(PDFCreator.createTestHeader(ProjectName, TestCaseName, Objective, Environment, Sprint, ExecutionDate, Result));
		Constants.logger.error("Arquivo de evidência: " + TestCaseName + "_date_" + date + "_time_" + time + ".pdf criado com sucesso!");	
		}
	
	public static PdfPTable createTestHeader(String ProjectName, String TestCaseName, String Objective, String Environment, String Sprint, String ExecutionDate, String Result) throws MalformedURLException, IOException, DocumentException
	{
		// add image
		Image logo = Image.getInstance(System.getProperty("user.dir") + "/target/logo/" + Config.readConfig("Logo"));
		logo.scalePercent(Float.parseFloat(Config.readConfig("Logo-Scaling")));
		logo.setAlignment(Element.ALIGN_LEFT);
		document.add(logo);
		// Create table with the numbers of collums informed.
		PdfPTable table = new PdfPTable(14);
		PdfPCell cell;
		Phrase phrase;
		Font tstyle = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		Font oStyle = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
		
		// Title
		tstyle = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
		phrase = new Phrase();
		phrase.add(new Chunk(ProjectName, tstyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(14);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(
				new BaseColor(
					Integer.parseInt(Config.readConfig("HCR")),
					Integer.parseInt(Config.readConfig("HCG")),
					Integer.parseInt(Config.readConfig("HCB"))
					)
				);
		table.addCell(cell);
		
		// Testname
		tstyle = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		phrase = new Phrase();
		phrase.add(new Chunk("Test Case:", tstyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(3);
		table.addCell(cell);		
		phrase = new Phrase();
		phrase.add(new Chunk("  " + TestCaseName, oStyle));
		cell = new PdfPCell(phrase);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(11);
		table.addCell(cell);
		
		// Objective
		tstyle = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		phrase = new Phrase();
		phrase.add(new Chunk("Objective:", tstyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(3);
		table.addCell(cell);		
		phrase = new Phrase();
		phrase.add(new Chunk("  " + Objective, oStyle));
		cell = new PdfPCell(phrase);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(11);
		table.addCell(cell);
		
		// Environment
		phrase = new Phrase();
		phrase.add(new Chunk("Environment:", tstyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(3);
		table.addCell(cell);
		phrase = new Phrase();
		phrase.add(new Chunk("  " + Environment, oStyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		// Sprint
		phrase = new Phrase();
		phrase.add(new Chunk(" Sprint:", tstyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(2);
		table.addCell(cell);
		phrase = new Phrase();
		phrase.add(new Chunk(Sprint, oStyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		// ExecutionDate
		phrase = new Phrase();
		phrase.add(new Chunk("Execution Date:", tstyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(3);
		table.addCell(cell);
		phrase = new Phrase();
		phrase.add(new Chunk(ExecutionDate, oStyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		// Expect Result
		phrase = new Phrase();
		phrase.add(new Chunk("Expected Result:", tstyle));
		cell = new PdfPCell(phrase);
		cell.setColspan(3);
		table.addCell(cell);		
		phrase = new Phrase();
		phrase.add(new Chunk("  " + Result, oStyle));
		cell = new PdfPCell(phrase);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(11);
		table.addCell(cell);
		
		// Table Alignment
		table.setTotalWidth(PageSize.A4.getWidth() - 50f);
		table.setLockedWidth(true);
		table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
		
		return table;		
	}
	
	public static void addStep(String Step) throws DocumentException
	{
		Paragraph StepName = new Paragraph(Step);
		StepName.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 17f));
		document.add(StepName);
	}
	
	public static void addStepWithScreenshot(String Step, BufferedImage screenshot) throws DocumentException, IOException, InterruptedException
	{
		Paragraph StepName = new Paragraph(Step);
		StepName.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 17f));
		document.add(StepName);
		addMobileScreenshot(Config.readConfig("Device"), screenshot);
	}
	
	public static void addJSON(String JSON) throws DocumentException
	{
		Font oStyle = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE);
		Paragraph AddJSON = new Paragraph();
		AddJSON.add(new Chunk(JSON, oStyle));
		document.add(AddJSON);
	}
	
	public static void addScreenshot(BufferedImage screenshot) throws DocumentException, IOException
	{
		PdfContentByte pdfCB = new PdfContentByte(writer);
		Image scr = Image.getInstance(pdfCB, screenshot, 1);
		scr.scalePercent(40f);
		document.add(scr);
	}
	
	public static void addMobileScreenshot(String Device, BufferedImage screenshot) throws DocumentException, IOException
	{
		PdfContentByte pdfCB = new PdfContentByte(writer);
		Image scr = Image.getInstance(pdfCB, screenshot, 1);
		if(Device.toLowerCase().contains("ipad") || Device.toLowerCase().contains("tablet"))
			scr.scaleToFit(500.f, 700.f);
		else
			scr.scaleToFit(200.f, 400.f);
		
		document.add(scr);
	}
	
	public static void logFatal(String Error) throws DocumentException
	{
		Font tstyle = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.RED);
		
		Paragraph error = new Paragraph();
		error.add(new Chunk(Error, tstyle));
		document.add(error);
	}

	public static void quitPDF()
	{
		document.close();
	}
}
