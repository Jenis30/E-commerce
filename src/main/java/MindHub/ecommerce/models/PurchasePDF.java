package MindHub.ecommerce.models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import org.hibernate.mapping.Value;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PurchasePDF {
    private LocalDateTime localDateTime;
    private Purchase purchase;
    private static final String LOGO_PATH = "C:/Users/harme/OneDrive/Escritorio/MindHub/e-commerce/src/main/resources/static/web/assets/images/logo.png";

    DecimalFormat currencyFormatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);

    private Image getLogoImage() throws IOException, BadElementException {
        return Image.getInstance(LOGO_PATH);
    }

    public PurchasePDF(Purchase purchase) {
        this.purchase = purchase;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setPhrase(new Phrase("Product", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Subtotal (US$)", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

        List<PurchaseCream> purchaseCreamList = new ArrayList<>(purchase.getPurchaseCreams());
        List<PurchaseFlavoring> purchaseFlavoringList = new ArrayList<>(purchase.getPurchaseFlavorings());
        List<PurchaseFragrance> purchaseFragranceList = new ArrayList<>(purchase.getPurchaseFragances());

        if (purchaseCreamList != null) {
            for (PurchaseCream cream : purchaseCreamList) {
                table.addCell(createCell(cream.getCream().getName()));
                table.addCell(createCell(String.valueOf(cream.getQuantity())));
                table.addCell(createCell(currencyFormatter.format(cream.getSubtotal())));
            }

        }
        if (purchaseFragranceList != null) {
            for (PurchaseFragrance fragance : purchaseFragranceList) {
                table.addCell(createCell(fragance.getFragance().getName()));
                table.addCell(createCell(String.valueOf(fragance.getQuantity())));
                table.addCell(createCell(currencyFormatter.format(fragance.getSubtotal())));
            }
        }
        if (purchaseFlavoringList != null) {
            for (PurchaseFlavoring flavoring : purchaseFlavoringList) {
                table.addCell(createCell(flavoring.getFlavoring().getName()));
                table.addCell(createCell(String.valueOf(flavoring.getQuantity())));
                table.addCell(createCell(currencyFormatter.format(flavoring.getSubtotal())));
            }
        }
        PdfPCell emptyRowCell = new PdfPCell(new Phrase(""));
        emptyRowCell.setColspan(3);
        emptyRowCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptyRowCell);

        PdfPCell emptyCell = new PdfPCell(new Phrase(""));
        emptyCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptyCell);


        PdfPCell totalLabelCell = new PdfPCell(new Phrase("Total (US$)",FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        totalLabelCell.setBorder(Rectangle.NO_BORDER);  // Sin bordes
        totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalLabelCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(totalLabelCell);

        PdfPCell totalAmountCell = new PdfPCell(new Phrase(currencyFormatter.format(purchase.getTotalPurchases()), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        totalAmountCell.setBorder(Rectangle.NO_BORDER);
        totalAmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalAmountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(totalAmountCell);
    }

    private PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));

        // Establecer el padding para todas las celdas
        cell.setPadding(5);

        // Configurar la alineación del texto en el centro
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        return cell;
    }
    public void usePDFExport(OutputStream outputStream) throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, outputStream);
        doc.open();

        // Estilos de fuentes
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font detailsFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        Image logoImage = getLogoImage();
        logoImage.scaleAbsolute(150f, 150f);
        doc.add(logoImage);

        // Título
        Paragraph title = new Paragraph("Your purchase in Velvet Fragances", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(10);
        doc.add(title);

        doc.add(new Paragraph("Purchase Id: " + purchase.getId(), detailsFont));
        // Formatear la fecha y la hora
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        doc.add(new Paragraph("Purchase date and time: " + date + " - " + time, detailsFont));


        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        // Encabezado de la tabla
        writeTableHeader(table);

        // Contenido de la tabla
        writeTableData(table);

        doc.add(table);
        doc.close();
    }

}