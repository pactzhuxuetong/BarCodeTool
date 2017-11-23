package PowerProcess;

import BarCode.BarCoder;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by YuanXiang on 2017/11/23.
 */
public class ProcessEngine {


    public String resourceFile = "model.pdf";
    private String outPut = "./result/";
    public static final String FONT = "./font/Univers.ttf";


    public String processPdf(String barCode)  {
        Font font= null;
        try {
             font = FontFactory.getFont(FONT, "utf-8", BaseFont.EMBEDDED);
        }catch (Exception e){
            return e.getMessage();
        }

        InputStream ino = this.getClass().getClassLoader().getResourceAsStream(resourceFile);

        PdfReader reader = null;
        try {
            reader = new PdfReader(ino);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, new FileOutputStream(outPut+barCode+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PdfContentByte over = stamper.getOverContent(1);

        // 1. insert text
        over.beginText();
        over.setFontAndSize(font.getBaseFont(), 8.5f);
        over.setTextMatrix(218, 161);
        over.showText(barCode);
        over.endText();

        //2. insert image
        Image image = null;
        try {
            image = Image.getInstance(BarCoder.genBarCodeJpg(barCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        image.setAbsolutePosition(203,143);
        image.scaleAbsolute(90,15);
        try {
            over.addImage(image);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            stamper.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public static void main(String[] args) throws Exception {
        //new ProcessEngine().execute("123123");
        ProcessEngine processEngine = new ProcessEngine();
        processEngine.processPdf("AS9H213123DJKJ0");
    }


}
