package UserInterface;

import PdfProcess.MainProcessor;
import PdfProcess.ReaderFile;
import PowerProcess.ProcessEngine;
import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YuanXiang on 2017/11/21.
 */
public class MainFrame extends JFrame implements ActionListener{

    JButton jButton = new JButton("生成");
    JButton jButton1 = new JButton("批量文件");
    JTextField jTextField = new JTextField("");
    JTextArea jTextArea = new JTextArea();
    public MainFrame(){

        this.setTitle("PDF快速修改工具");
        jButton.setBounds(100,20,100,30);
        jButton1.setBounds(100,60,100,30);
        jTextField.setBounds(400,20,100,30);
        jTextArea.setBounds(100,100,400,150);

        jButton.addActionListener(this);
        jButton1.addActionListener(this);

        this.setLayout(null);

        this.add(jButton);
        this.add(jTextField);
        this.add(jTextArea);
        this.add(jButton1);

        this.setBounds(500,500,600,300);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        ProcessEngine mainProcessor = new ProcessEngine();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println();// new Date()为获取当前系统时间
        if(e.getSource() == jButton){
            String message = jTextField.getText();
            if(message.trim().length() == 0){
                jTextArea.setText("请输入条形码的代码");
            }else{
                String dir = mainProcessor.processPdf(message);
                jTextArea.append(dir);
                jTextArea.append(df.format(new Date())+", 条码 "+message+", PDF文件处理成功."+"\n");
            }
        }else if(e.getSource() == jButton1){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setBounds(200,20,100,30);
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jFileChooser.showDialog(new JLabel(), "选择");

            List<String> list = null;
            try {
                File file = jFileChooser.getSelectedFile();
                list = ReaderFile.readerFile(file.getAbsolutePath());
            } catch (IOException e1) {
                jTextArea.setText("未选择任何文件！");
            }
            for(int i=0; i<list.size(); i++){
                mainProcessor.processPdf(list.get(i));
                jTextArea.append(df.format(new Date())+", 第" + i+1 + "个条码 "+list.get(i)+", PDF文件处理成功.\n");
            }
        }


    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }



}
