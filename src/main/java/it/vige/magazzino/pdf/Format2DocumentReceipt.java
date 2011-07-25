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
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * The maker for pdf documents using format 2
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Model
public class Format2DocumentReceipt implements DocumentReceipt {

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
		
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(105);
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(650);
        
		PdfContentByte canvas = writer.getDirectContentUnder();
		Image image1 = Image.getInstance("/Users/flashboss/Desktop/logo.gif");
		image1.setAbsolutePosition(36, 742);
		image1.scalePercent(60);
		document.add(image1);

		Phrase phrase1 = new Phrase(bundle.getString("receipt_number"));
		Phrase phrase2 = new Phrase(receipt.getNumber());
		Phrase phrase3 = new Phrase(bundle.getString("receipt_date"));
		Phrase phrase4 = new Phrase(receipt.getDate());
		Phrase phrase5 = new Phrase(receipt.getCause());
		Phrase phrase6 = new Phrase(receipt.getDescription());
		Phrase phrase7 = new Phrase("prova 3: prova 3");
		Phrase phrase8 = new Phrase("prova 4: prova 4");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 286,
				797, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase2, 386,
				797, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase3, 286,
				777, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase4, 386,
				777, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase5, 286,
				757, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase6, 386,
				757, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase7, 286,
				737, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase8, 386,
				737, 0);

		Phrase phrase9 = new Phrase("prova 4: prova 4");
		Phrase phrase10 = new Phrase("prova 2: prova 2");
		Phrase phrase11 = new Phrase("prova 3: prova 3");
		Phrase phrase12 = new Phrase("prova 4: prova 4");
		Phrase phrase13 = new Phrase("jjijijijijij");
		Phrase phrase14 = new Phrase("prova 2: prova 2");
		Phrase phrase15 = new Phrase("prova 3: prova 3");
		Phrase phrase16 = new Phrase("prova 4: prova 4");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase9, 36,
				718, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase10, 136,
				718, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase11, 36,
				698, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase12, 136,
				698, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase13, 36,
				678, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase14, 136,
				678, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase15, 36,
				658, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase16, 136,
				658, 0);

		Phrase phrase17 = new Phrase("Ditta");
		Phrase phrase18 = new Phrase("prova 2: prova 2");
		Phrase phrase19 = new Phrase("prova 3: prova 3");
		Phrase phrase20 = new Phrase("prova 4: prova 4");
		Phrase phrase21 = new Phrase("prova 4: prova 4");
		Phrase phrase22 = new Phrase("Dittaaaaa");
		Phrase phrase23 = new Phrase("prova 2: ");
		Phrase phrase24 = new Phrase("prova 2: ");
		Phrase phrase25 = new Phrase("prova 2: ");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase17, 236,
				736, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase18, 236,
				708, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase19, 236,
				688, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase20, 236,
				668, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase21, 386,
				668, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase22, 236,
				648, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase23, 306,
				648, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase24, 366,
				648, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase25, 416,
				648, 0);

		Phrase phrase26 = new Phrase("dsadasd");
		Phrase phrase27 = new Phrase("dgbsbb");
		Phrase phrase28 = new Phrase("323232");
		Phrase phrase29 = new Phrase("bbgdbdfbdb");
		Phrase phrase30 = new Phrase("wefwew");
		Phrase phrase31 = new Phrase("ewrew");
		Phrase phrase32 = new Phrase("ewr5");
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase26, 59,
				618, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase27, 136,
				618, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase28, 196,
				618, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase29, 266,
				618, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase30, 351,
				618, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase31, 416,
				618, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase32, 496,
				618, 0);

		Phrase phrase33 = null;
		Phrase phrase34 = null;
		Phrase phrase35 = null;
		Phrase phrase36 = null;
		Phrase phrase37 = null;
		Phrase phrase38 = null;
		Phrase phrase39 = null;

		int i = 0;
		for (i = 0; i < 70; i = i + 15) {
			phrase33 = new Phrase("dsadasd");
			phrase34 = new Phrase("dgbsbb");
			phrase35 = new Phrase("323232");
			phrase36 = new Phrase("bbgdbdfbdb");
			phrase37 = new Phrase("wefwew");
			phrase38 = new Phrase("ewrew");
			phrase39 = new Phrase("ewr5");
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase33,
					59, 598 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase34,
					136, 598 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase35,
					196, 598 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase36,
					266, 598 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase37,
					351, 598 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase38,
					416, 598 - i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase39,
					496, 598 - i, 0);
		}

		if (i - 298 < 0)
			i = 298;
		else
			i = 588 - i;
		Phrase phrase40 = new Phrase("dsadasd");
		Phrase phrase41 = new Phrase("dgbsbb");
		Phrase phrase42 = new Phrase("323232");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase40, 99,
				i, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase41, 216,
				i, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase42, 306,
				i, 0);

		Phrase phrase43 = new Phrase("opoppp");
		Phrase phrase44 = new Phrase("2ws");
		Phrase phrase45 = new Phrase("78900");
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase43, 59,
				i - 20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase44, 186,
				i - 20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase45, 276,
				i - 20, 0);

		Phrase phrase46 = new Phrase("lalpaopdje");
		Phrase phrase47 = new Phrase("fjkvfodf");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase46, 99,
				i - 40, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase47, 246,
				i - 40, 0);

		Phrase phrase48 = new Phrase("78900");
		Phrase phrase49 = new Phrase("opoppp");
		Phrase phrase50 = new Phrase("2ws");
		Phrase phrase51 = new Phrase("78900");
		Phrase phrase52 = new Phrase("opoppp");
		Phrase phrase53 = new Phrase("2ws");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase48, 416,
				i, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase49, 526,
				i, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase50, 416,
				i - 20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase51, 526,
				i - 20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase52, 416,
				i - 50, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase53, 526,
				i - 50, 0);
				
        table.addCell(cell);
        document.add(table);
		document.close();

		HttpServletResponse response = (HttpServletResponse) extCtx
				.getResponse();
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition",
				"attachment; filename=\""
						+ bundle.getString("receipt") + "-"
						+ receipt.getDate() + ".pdf\"");

		ServletOutputStream os = response.getOutputStream();
		os.write(bytesOS.toByteArray());
		os.flush();
		os.close();

		facesContext.responseComplete();
	}

}
