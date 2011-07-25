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
		
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(105);
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(650);
        
		PdfContentByte canvas = writer.getDirectContentUnder();
		Image image1 = Image.getInstance("/Users/flashboss/Desktop/logo.gif");
		image1.setAbsolutePosition(166, 738);
		image1.scalePercent(60);
		document.add(image1);
		Image image2 = Image.getInstance("/Users/flashboss/Desktop/logo.gif");
		image2.setAbsolutePosition(326, 748);
		image2.scalePercent(40);
		document.add(image2);
		Phrase phrase1 = new Phrase("Provaaaaaaaaa");
		Phrase phrase2 = new Phrase("prova 1: prova 1");
		Phrase phrase3 = new Phrase("prova 2: prova 2");
		Phrase phrase4 = new Phrase("prova 3: prova 3");
		Phrase phrase5 = new Phrase("prova 4: prova 4");
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

		Phrase phrase6 = new Phrase("prova 2: prova 2");
		Phrase phrase7 = new Phrase("prova 3: prova 3");
		Phrase phrase8 = new Phrase("prova 4: prova 4");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase6, 36,
				724, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase7, 36,
				714, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase8, 36,
				704, 0);

		Phrase phrase9 = new Phrase("prova 2: prova 2");
		Phrase phrase10 = new Phrase("prova 3: prova 3");
		Phrase phrase11 = new Phrase("prova 4: prova 4");
		Phrase phrase12 = new Phrase("prova 2: prova 2");
		Phrase phrase13 = new Phrase("prova 3: prova 3");
		Phrase phrase14 = new Phrase("prova 4: prova 4");
		Phrase phrase15 = new Phrase("prova 4: prova 4");
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

		Phrase phrase16 = new Phrase("prova 2: prova 2");
		Phrase phrase17 = new Phrase("prova 3: prova 3");
		Phrase phrase18 = new Phrase("prova 4: prova 4");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase16, 206,
				644, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase17, 206,
				624, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase18, 206,
				604, 0);

		Phrase phrase19 = new Phrase("prova");
		Phrase phrase20 = new Phrase("prova");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase19, 316,
				694, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase20, 356,
				694, 0);

		Phrase phrase21 = new Phrase("dfgdfsgdsfg");
		Phrase phrase22 = new Phrase("fnsdjfndjsf");
		Phrase phrase23 = new Phrase("rewrewr");
		Phrase phrase24 = new Phrase(bundle.getString("receipt_number"));
		Phrase phrase25 = new Phrase(bundle.getString("receipt_date"));
		Phrase phrase26 = new Phrase("ddjdkd");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase21, 36,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase22, 106,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase23, 166,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase24, 226,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase25, 316,
				540, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase26, 386,
				540, 0);
		
		Phrase phrase27 = new Phrase("aaqaqaq");
		Phrase phrase28 = new Phrase("cddcddcd");
		Phrase phrase29 = new Phrase("cnjcndkd");
		Phrase phrase30 = new Phrase(receipt.getNumber());
		Phrase phrase31 = new Phrase(receipt.getDate());
		Phrase phrase32 = new Phrase("tgsbsfgnffn");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase27, 36,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase28, 106,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase29, 166,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase30, 226,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase31, 316,
				530, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase32, 386,
				530, 0);

		Phrase phrase33 = new Phrase("aaqaqaq");
		Phrase phrase34 = new Phrase("cddcddcd");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase33, 36,
				510, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase34, 226,
				510, 0);
		
		Phrase phrase35 = new Phrase("cnjcndkd");
		Phrase phrase36 = new Phrase("dddedreqq");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase35, 36,
				500, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase36, 226,
				500, 0);

		Phrase phrase37 = new Phrase("dsadasd");
		Phrase phrase38 = new Phrase("dgbsbb");
		Phrase phrase39 = new Phrase("323232");
		Phrase phrase40 = new Phrase("bbgdbdfbdb");
		Phrase phrase41 = new Phrase("wefwew");
		Phrase phrase42 = new Phrase("ewrew");
		Phrase phrase43 = new Phrase("ewr5");
		Phrase phrase44 = new Phrase("ewr5");
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase37, 59,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase38, 126,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase39, 186,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase40, 256,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase41, 326,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase42, 376,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase43, 426,
				480, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase44, 486,
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
			phrase45 = new Phrase("dgbsbb");
			phrase46 = new Phrase("323232");
			phrase47 = new Phrase("bbgdbdfbdb");
			phrase48 = new Phrase("wefwew");
			phrase49 = new Phrase("ewrew");
			phrase50 = new Phrase("ewr5");
			phrase51 = new Phrase("dsadasd");
			phrase52 = new Phrase("dsadasd");
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase45,
					59, 460-i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase46,
					126, 460-i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase47,
					176, 460-i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase48,
					256, 460-i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase49,
					326, 460-i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase50,
					376, 460-i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase51,
					426, 460-i, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase52,
					476, 460-i, 0);
		}
		
		if (i - 298 < 0)
			i = 298;
		else
			i = 460 - i;

		Phrase phrase81 = new Phrase(receipt.getCause());
		Phrase phrase82 = new Phrase(receipt.getDescription());
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase81, 59,
				i, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase82, 326,
				i, 0);
		
		Phrase phrase53 = new Phrase("dsadasd");
		Phrase phrase54 = new Phrase("dgbsbb");
		Phrase phrase55 = new Phrase("323232");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase53, 36,
				i-20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase54, 116,
				i-20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase55, 206,
				i-20, 0);

		Phrase phrase56 = new Phrase("opoppp");
		Phrase phrase57 = new Phrase("2ws");
		Phrase phrase58 = new Phrase("78900");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase56, 106,
				i - 40, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase57, 186,
				i - 40, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase58, 276,
				i - 40, 0);

		Phrase phrase59 = new Phrase("lalpaopdje");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase59, 36,
				i - 60, 0);

		Phrase phrase60 = new Phrase("78900");
		Phrase phrase61 = new Phrase("opoppp");
		Phrase phrase62 = new Phrase("2ws");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase60, 186,
				i - 80, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase61, 186,
				i - 100, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase62, 186,
				i - 120, 0);
		
		Phrase phrase63 = new Phrase("78900");
		Phrase phrase64 = new Phrase("opoppp");
		Phrase phrase65 = new Phrase("2ws");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase63, 276,
				i - 80, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase64, 276,
				i - 100, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase65, 276,
				i - 120, 0);

		Phrase phrase66 = new Phrase("2w1");
		Phrase phrase67 = new Phrase("78910");
		Phrase phrase68 = new Phrase("op1ppp");
		Phrase phrase69 = new Phrase("21s");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase66, 296,
				i - 20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase67, 346,
				i - 20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase68, 416,
				i - 20, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase69, 476,
				i - 20, 0);

		Phrase phrase70 = new Phrase("2ws");
		Phrase phrase71 = new Phrase("78900");
		Phrase phrase72 = new Phrase("opoppp");
		Phrase phrase73 = new Phrase("2ws");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase70, 336,
				i - 40, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase71, 396,
				i - 40, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase72, 466,
				i - 40, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase73, 526,
				i - 40, 0);

		Phrase phrase74 = new Phrase("78900");
		Phrase phrase75 = new Phrase("opoppp");
		Phrase phrase76 = new Phrase("2ws");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase74, 296,
				i - 60, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase75, 376,
				i - 60, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase76, 466,
				i - 60, 0);

		Phrase phrase77 = new Phrase("2ws");
		Phrase phrase78 = new Phrase("78900");
		Phrase phrase79 = new Phrase("opoppp");
		Phrase phrase80 = new Phrase("2ws");
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase77, 346,
				i - 80, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase78, 376,
				i - 80, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase79, 456,
				i - 80, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase80, 516,
				i - 100, 0);
				
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
