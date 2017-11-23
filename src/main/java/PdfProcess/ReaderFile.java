package PdfProcess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuanXiang on 2017/11/21.
 */
public class ReaderFile {

    public static List<String> readerFile(String fileName) throws IOException {

        FileReader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        List<String> ans = new ArrayList<String>();
        while((str = br.readLine()) != null) {
            ans.add(str.trim());
        }
        if(ans.size() != 0){
            return ans;
        }else {
            return null;
        }
    }

}
