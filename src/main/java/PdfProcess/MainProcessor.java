package PdfProcess;


import BarCode.BarCoder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import sun.applet.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by YuanXiang on 2017/11/21.
 */
public class MainProcessor {

    public String processPdf(String message){
        String file1 = "model.pdf";
        InputStream ino = this.getClass().getClassLoader().getResourceAsStream(file1);
    //    System.out.println(File.separator+file1);
      //  String name = this.getClass().getResource("/model.pdf").getPath();
        System.out.println(File.separator+file1);
        PDDocument document = null;


            // 1.insert barcode
            try {
                document = PDDocument.load(ino);
            }catch (Exception e){
                e.printStackTrace();
                return e.getMessage();
            }
        try{
            PDPage page = document.getPage(0);
            ByteArrayInputStream in = new ByteArrayInputStream(BarCoder.genBarCodeJpg(message));
            BufferedImage image = ImageIO.read(in);
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

            // contentStream.drawImage(ximage, 20, 20 );
            // better method inspired by http://stackoverflow.com/a/22318681/535646
            // reduce this value if the image is too large
            float scale = 1f;
            contentStream.drawImage(pdImage, 203, 143, 90, 15);
            contentStream.close();

            // 2.insert message
            contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 10);
            contentStream.setTextMatrix(Matrix.getTranslateInstance(218,161));
            contentStream.showText(message);
            contentStream.endText();
            contentStream.close();
            String saveDir = "./result";
            File file = new File(saveDir);
            if(!file.exists()){
                file.mkdir();
            }
            String saveFile = saveDir+"/" + message + ".pdf";
            document.save(saveFile);
            return saveFile;

        }catch (Exception e){
            e.printStackTrace();
            return "资源处理错误";
        }

    }

    public static void main(String[] args) {
        try {
            String File = "model.pdf";
            String fileName = MainProcessor.class.getClassLoader().getResource("").getPath();
            String name = MainProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
