package com.sp.score;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ScorePdfView extends AbstractPdfView{
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = (String)model.get("filename");
		
		List<String> labels = (List<String>)model.get("labels");
		List<String[]> values = (List<String[]>)model.get("values");
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename="+filename);
		
		// 폰트 생성
		BaseFont baseFont = BaseFont.createFont("c:\\windows\\fonts\\gulim.ttc,0"
				,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(baseFont);
		
		font.setSize(20);
		Paragraph p = new Paragraph("성적처리", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		
		font.setSize(10);
		PdfPTable table = new PdfPTable(labels.size());
		table.setWidths(new int[] {50, 100, 80, 80, 80});
		table.setSpacingBefore(10);
		
		PdfPCell cell;
		for(int idx=0; idx<labels.size(); idx++) {
			cell = new PdfPCell(new Paragraph(labels.get(idx),font));
			cell.setBackgroundColor(new Color(212, 244, 250));
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			cell.setPadding(3);
			table.addCell(cell);
		}
		
		for(int idx=0; idx<values.size(); idx++) {
			String[] ss = values.get(idx);
			for(int col=0; col<ss.length; col++) {
				cell = new PdfPCell(new Paragraph(ss[col],font));
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setPadding(3);
				table.addCell(cell);
			}
		}
		document.add(table);
	}

}
