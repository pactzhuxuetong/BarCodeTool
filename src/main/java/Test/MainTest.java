package Test;


import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by YuanXiang on 2017/11/23.
 */
public class MainTest {

    public static final String DEST = "results/f03_embed1d.pdf";
    public static final String FONT = "results/Univers.ttf";

    public void createPdf(String dest) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        Font font = FontFactory.getFont(FONT, "utf-8", BaseFont.EMBEDDED);
        document.add(new Paragraph("qwewq jste?", font));
        document.add(new Paragraph("Uvid\u00edme se za chvilku. M\u011bj se.", font));
        document.add(new Paragraph("Dovolte, abych se p\u0159edstavil.", font));
        document.add(new Paragraph("To je studentka.", font));
        document.add(new Paragraph("V\u0161echno v po\u0159\u00e1dku?", font));
        document.add(new Paragraph("On je in\u017een\u00fdr. Ona je l\u00e9ka\u0159.", font));
        document.add(new Paragraph("Toto je okno.", font));
        document.add(new Paragraph("Zopakujte to pros\u00edm.", font));
        document.close();
    }


    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MainTest().createPdf(DEST);
    }

}
