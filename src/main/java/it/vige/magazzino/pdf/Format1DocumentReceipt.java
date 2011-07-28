/*
 * Vige, Home of Professional Open Source
 * Copyright 2010, Vige, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.vige.magazzino.pdf;

import it.vige.magazzino.model.Receipt;

import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * The maker for pdf documents using format 1
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Model
public class Format1DocumentReceipt implements DocumentReceipt {

	@Inject
	private ExternalContext extCtx;

	@Inject
	FacesContext facesContext;

	@Model
	public void build(Receipt receipt) throws Exception {
		ResourceBundle bundle = ResourceBundle.getBundle("messages");

		Document document = new Document();
		ByteArrayOutputStream bytesOS = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, bytesOS);
		document.open();

		Font normalFont = new Font();
		Font headerFont = FontFactory.getFont(FontFactory.TIMES, 9);

		PdfContentByte canvas = writer.getDirectContentUnder();
		Image image1 = Image.getInstance("/Users/flashboss/Desktop/logo.gif");
		image1.setAbsolutePosition(166, 738);
		image1.scalePercent(60);
		document.add(image1);
		Image image2 = Image.getInstance("/Users/flashboss/Desktop/logo.gif");
		image2.setAbsolutePosition(326, 748);
		image2.scalePercent(40);
		document.add(image2);
		Phrase phrase1 = new Phrase("Provaaaaaaaaa", normalFont);
		Phrase phrase2 = new Phrase("prova 1: prova 1", normalFont);
		Phrase phrase3 = new Phrase("prova 2: prova 2", normalFont);
		Phrase phrase4 = new Phrase("prova 3: prova 3", normalFont);
		Phrase phrase5 = new Phrase("prova 4: prova 4", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 36,
				784, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase2, 36,
				774, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase3, 36,
				764, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase4, 36,
				754, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase5, 36,
				744, 0);

		Phrase phrase6 = new Phrase("prova 2: prova 2", normalFont);
		Phrase phrase7 = new Phrase("prova 3: prova 3", normalFont);
		Phrase phrase8 = new Phrase("prova 4: prova 4", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase6, 36,
				724, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase7, 36,
				714, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase8, 36,
				704, 0);

		Phrase phrase9 = new Phrase("prova 2: prova 2", normalFont);
		Phrase phrase10 = new Phrase("prova 3: prova 3", normalFont);
		Phrase phrase11 = new Phrase("prova 4: prova 4", normalFont);
		Phrase phrase12 = new Phrase("prova 2: prova 2", normalFont);
		Phrase phrase13 = new Phrase("prova 3: prova 3", normalFont);
		Phrase phrase14 = new Phrase("prova 4: prova 4", normalFont);
		Phrase phrase15 = new Phrase("prova 4: prova 4", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase9, 36,
				664, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase10, 36,
				654, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase11, 36,
				644, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase12, 36,
				634, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase13, 36,
				624, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase14, 36,
				614, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase15, 36,
				604, 0);

		Image image3 = Image.getInstance("/Users/flashboss/Desktop/logo.gif");
		image3.setAbsolutePosition(212, 664);
		image3.scalePercent(40);
		document.add(image3);

		Phrase phrase16 = new Phrase("prova 2: prova 2", normalFont);
		Phrase phrase17 = new Phrase("prova 3: prova 3", normalFont);
		Phrase phrase18 = new Phrase("prova 4: prova 4", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase16, 206,
				644, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase17, 206,
				624, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase18, 206,
				604, 0);

		Phrase phrase19 = new Phrase("prova", normalFont);
		Phrase phrase20 = new Phrase("prova", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase19, 316,
				694, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase20, 356,
				694, 0);

		Phrase phrase21 = new Phrase(bundle.getString("customer_code")
				.toUpperCase()
				+ " "
				+ bundle.getString("customer").toUpperCase(), headerFont);
		Phrase phrase22 = new Phrase(bundle.getString("pdf_partita_iva").toUpperCase(), headerFont);
		Phrase phrase23 = new Phrase(bundle.getString("pdf_agent").toUpperCase(), headerFont);
		Phrase phrase24 = new Phrase(bundle.getString("pdf_number_receipt").toUpperCase(), headerFont);
		Phrase phrase25 = new Phrase(bundle.getString("receipt_date")
				.toUpperCase()
				+ " "
				+ bundle.getString("receipt").toUpperCase(), headerFont);
		Phrase phrase26 = new Phrase(bundle.getString("pdf_number_page").toUpperCase(), headerFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase21, 24,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase22, 100,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase23, 176,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase24, 390,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase25, 466,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase26, 542,
				540, 0);

		Phrase phrase27 = new Phrase("aaqaqaq", normalFont);
		Phrase phrase28 = new Phrase("cddcddcd", normalFont);
		Phrase phrase29 = new Phrase("cnjcndkd", normalFont);
		Phrase phrase30 = new Phrase(receipt.getNumber(), normalFont);
		Phrase phrase31 = new Phrase(receipt.getDate(), normalFont);
		Phrase phrase32 = new Phrase("tgsb", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase27, 36,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase28, 106,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase29, 176,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase30, 396,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase31, 470,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase32, 546,
				530, 0);

		Phrase phrase33 = new Phrase("aaqaqaq", headerFont);
		Phrase phrase34 = new Phrase("cddcddcd", headerFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase33, 24,
				513, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase34, 264,
				513, 0);

		Phrase phrase35 = new Phrase("cnjcndkd", normalFont);
		Phrase phrase36 = new Phrase("dddedreqq", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase35, 36,
				503, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase36, 276,
				503, 0);

		Phrase phrase37 = new Phrase(bundle.getString("article_code")
				.toUpperCase(), headerFont);
		Phrase phrase38 = new Phrase(bundle.getString("article_description")
				.toUpperCase(), headerFont);
		Phrase phrase39 = new Phrase(bundle.getString("article_um")
				.toUpperCase(), headerFont);
		Phrase phrase40 = new Phrase(bundle.getString("pdf_number_articles").toUpperCase(), headerFont);
		Phrase phrase41 = new Phrase(bundle.getString("article_prize")
				.toUpperCase(), headerFont);
		Phrase phrase42 = new Phrase(bundle.getString("pdf_reduction").toUpperCase(), headerFont);
		Phrase phrase43 = new Phrase(bundle.getString("pdf_amount").toUpperCase(), headerFont);
		Phrase phrase44 = new Phrase(bundle.getString("pdf_iva").toUpperCase(), headerFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase37, 47,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase38, 126,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase39, 286,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase40, 324,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase41, 373,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase42, 440,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase43, 488,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase44, 552,
				480, 0);

		Phrase phrase45 = null;
		Phrase phrase46 = null;
		Phrase phrase47 = null;
		Phrase phrase48 = null;
		Phrase phrase49 = null;
		Phrase phrase50 = null;
		Phrase phrase51 = null;
		Phrase phrase52 = null;

		int i = 0;
		for (i = 0; i < 70; i = i + 15) {
			phrase45 = new Phrase("dgbsbb", normalFont);
			phrase46 = new Phrase("323232", normalFont);
			phrase47 = new Phrase("bbg", normalFont);
			phrase48 = new Phrase("wefwe", normalFont);
			phrase49 = new Phrase("ewrew", normalFont);
			phrase50 = new Phrase("ewr5", normalFont);
			phrase51 = new Phrase("dsadasd", normalFont);
			phrase52 = new Phrase("ds", normalFont);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase45,
					59, 460 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase46,
					126, 460 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase47,
					280, 460 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase48,
					306, 460 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase49,
					368, 460 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase50,
					436, 460 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase51,
					480, 460 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase52,
					556, 460 - i, 0);
		}

		int j = 298;
		if (i - 298 < 0)
			i = 298;
		else {
			j = i;
			i = 460 - i;
		}

		Phrase phrase81 = new Phrase(receipt.getCause(), normalFont);
		Phrase phrase82 = new Phrase(receipt.getDescription(), normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase81, 59, i,
				0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase82, 326,
				i, 0);

		Phrase phrase53 = new Phrase(bundle.getString("pdf_total_goods").toUpperCase(), headerFont);
		Phrase phrase54 = new Phrase(bundle.getString("pdf_reduction").toUpperCase(), headerFont);
		Phrase phrase55 = new Phrase(bundle.getString("pdf_total_net").toUpperCase(), headerFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase53, 26,
				i - 30, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase54, 104,
				i - 30, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase55, 182,
				i - 30, 0);

		Phrase phrase56 = new Phrase("opoppp", normalFont);
		Phrase phrase57 = new Phrase("2ws", normalFont);
		Phrase phrase58 = new Phrase("78900", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase56, 96,
				i - 50, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase57, 176,
				i - 50, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase58, 252,
				i - 50, 0);

		Phrase phrase59 = new Phrase(bundle.getString("pdf_expiries").toUpperCase(), headerFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase59, 36,
				i - 70, 0);

		Phrase phrase60 = new Phrase("78900", normalFont);
		Phrase phrase61 = new Phrase("opoppp", normalFont);
		Phrase phrase62 = new Phrase("2ws", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase60, 166,
				i - 90, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase61, 166,
				i - 110, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase62, 166,
				i - 130, 0);

		Phrase phrase63 = new Phrase("78900", normalFont);
		Phrase phrase64 = new Phrase("opoppp", normalFont);
		Phrase phrase65 = new Phrase("2ws", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase63, 256,
				i - 90, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase64, 256,
				i - 110, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase65, 256,
				i - 130, 0);

		Phrase phrase66 = new Phrase(bundle.getString("pdf_transport").toUpperCase(), headerFont);
		Phrase phrase67 = new Phrase(bundle.getString("pdf_caching").toUpperCase(), headerFont);
		Phrase phrase68 = new Phrase(bundle.getString("pdf_various_costs").toUpperCase(), headerFont);
		Phrase phrase69 = new Phrase(bundle.getString("pdf_stamps").toUpperCase(), headerFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase66, 260,
				i - 30, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase67, 340,
				i - 30, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase68, 418,
				i - 30, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase69, 496,
				i - 30, 0);

		Phrase phrase70 = new Phrase("2ws", normalFont);
		Phrase phrase71 = new Phrase("78900", normalFont);
		Phrase phrase72 = new Phrase("opoppp", normalFont);
		Phrase phrase73 = new Phrase("2ws", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase70, 300,
				i - 50, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase71, 390,
				i - 50, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase72, 468,
				i - 50, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase73, 546,
				i - 50, 0);

		Phrase phrase74 = new Phrase(bundle.getString("article_imponible")
				.toUpperCase(), headerFont);
		Phrase phrase75 = new Phrase(bundle.getString("pdf_tax").toUpperCase(), headerFont);
		Phrase phrase76 = new Phrase(bundle.getString("pdf_total_receipt").toUpperCase(), headerFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase74, 260,
				i - 70, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase75, 352,
				i - 70, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase76, 484,
				i - 70, 0);

		Phrase phrase77 = new Phrase("2ws", normalFont);
		Phrase phrase78 = new Phrase("78900", normalFont);
		Phrase phrase79 = new Phrase("opoppp", normalFont);
		Phrase phrase80 = new Phrase("2ws", normalFont);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase77, 310,
				i - 90, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase78, 352,
				i - 90, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase79, 450,
				i - 90, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase80, 536,
				i - 110, 0);

		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setPadding(50);
		table.setWidthPercentage(105);
		PdfPCell cell = new PdfPCell();
		cell.setPadding(127);
		table.addCell(cell);
		document.add(table);

		table = new PdfPTable(6);
		table.getDefaultCell().setPadding(5);
		table.setWidthPercentage(105);
		table.setWidths(new float[] { 7, 7, 20, 7, 7, 3 });
		cell = new PdfPCell();
		cell.setPadding(14);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		document.add(table);

		table = new PdfPTable(2);
		table.getDefaultCell().setPadding(5);
		table.setWidthPercentage(105);
		table.setWidths(new float[] { 15.5f, 20 });
		cell = new PdfPCell();
		cell.setPadding(14);
		table.addCell(cell);
		table.addCell(cell);
		document.add(table);

		table = new PdfPTable(1);
		table.getDefaultCell().setPadding(50);
		table.setWidthPercentage(105);
		cell = new PdfPCell();
		cell.setPadding(3);
		table.addCell(cell);
		document.add(table);

		table = new PdfPTable(7);
		table.getDefaultCell().setPadding(100);
		table.setWidths(new float[] { 23.5f, 2, 5, 6, 4, 7, 3 });
		table.setWidthPercentage(105);
		cell = new PdfPCell();
		cell.setPadding(j*8-2279);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		document.add(table);

		table = new PdfPTable(7);
		table.getDefaultCell().setPadding(5);
		table.setWidthPercentage(105);
		cell = new PdfPCell();
		cell.setPadding(17);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		document.add(table);

		table = new PdfPTable(4);
		table.getDefaultCell().setPadding(5);
		table.setWidths(new float[] { 10.5f, 4, 6, 4 });
		table.setWidthPercentage(105);
		cell = new PdfPCell();
		cell.setPadding(48);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		table.addCell(cell);
		document.add(table);

		document.close();

		HttpServletResponse response = (HttpServletResponse) extCtx
				.getResponse();
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition", "attachment; filename=\""
				+ bundle.getString("receipt") + "-" + receipt.getDate()
				+ ".pdf\"");

		ServletOutputStream os = response.getOutputStream();
		os.write(bytesOS.toByteArray());
		os.flush();
		os.close();

		facesContext.responseComplete();
	}

}
