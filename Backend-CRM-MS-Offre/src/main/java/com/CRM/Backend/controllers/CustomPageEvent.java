package com.CRM.Backend.controllers;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class CustomPageEvent extends PdfPageEventHelper {
    private Image background;

    public CustomPageEvent() {
        try {
            background = Image.getInstance("C:\\Users\\Asus\\Downloads\\fond.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEndPage(PdfWriter writer, Document document) {
        try {
            PdfContentByte canvas = writer.getDirectContentUnder();
            background.scaleAbsolute(document.getPageSize());
            background.setAbsolutePosition(0, 0);
            canvas.addImage(background);

            PdfPTable headerTable = new PdfPTable(5);
            headerTable.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 17);

            PdfPCell referenceCell = createCell("Référence", headerFont);
            PdfPCell produitCell = createCell("Nom du Produit", headerFont);
            PdfPCell quantiteCell = createCell("Quantité", headerFont);
            PdfPCell prixUnitaireCell = createCell("Prix unitaire", headerFont);
            PdfPCell montantCell = createCell("Montant", headerFont);

            headerTable.addCell(referenceCell);
            headerTable.addCell(produitCell);
            headerTable.addCell(quantiteCell);
            headerTable.addCell(prixUnitaireCell);
            headerTable.addCell(montantCell);

            PdfPCell lineCell = createLineCell();
            headerTable.addCell(lineCell);

            Rectangle rect = new Rectangle(document.left(), document.top(), document.right(), document.top() - 1);
            headerTable.setTotalWidth(rect.getWidth());
            headerTable.writeSelectedRows(0, -1, rect.getLeft(), rect.getTop(), writer.getDirectContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell createLineCell() {
        PdfPCell lineCell = new PdfPCell();
        lineCell.setColspan(5);
        lineCell.setBorder(Rectangle.BOTTOM);
        lineCell.setBorderColor(BaseColor.GRAY);
        lineCell.setPaddingBottom(5);
        return lineCell;
    }
}
