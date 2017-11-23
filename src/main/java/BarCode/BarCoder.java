package BarCode;

import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by YuanXiang on 2017/11/21.
 */
public class BarCoder {
    public static byte[] genBarCodeJpg(String barCode) throws Exception{
        Code39Bean bean = new Code39Bean();
        final int dpi = 500;
        //样式
        bean.setModuleWidth(0.35);
        bean.setBarHeight(2);
        bean.doQuietZone(false);
        bean.setQuietZone(2);
        //两边空白区
        bean.setFontName("Helvetica");
        bean.setFontSize(3);
        bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);

        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,
                    "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY
                    , true, 0);

            bean.generateBarcode(canvas, barCode);
            canvas.finish();
            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
