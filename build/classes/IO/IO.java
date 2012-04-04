/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IO;

import ZeroSyntaxHighlighter.SyntaxBox;
import Main.ZeroVal;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.text.BadLocationException;

/**
 * @author David Dolyniuk
 */
public class IO {
    public static boolean loadFile(JTabbedPane p) throws FileNotFoundException, UnsupportedEncodingException, IOException, BadLocationException {
        List<Tab> tabList = new ArrayList<>();
        String path = getPath();
        File mainFile = new File(path);
        if(!mainFile.exists()) {
            return false;
        }
        FileInputStream fs = new FileInputStream(path);
        DataInputStream in = new DataInputStream(fs);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String st;
        while((st = br.readLine()) != null) {
            if(Tab.isNewTab(st)) {
                Tab t = new Tab(Tab.getTabName(st));
                tabList.add(t);
            } else {
                if(tabList.isEmpty()) 
                    break;
                Tab last = tabList.get(tabList.size() - 1);
                last.appendTextData(st);
            }
        }
        
        for(int i = 0; i < tabList.size(); i++) {
            SyntaxBox b = new SyntaxBox();
            b.setHighlightText(tabList.get(i).getM_text());
            p.insertTab(tabList.get(i).getM_name(), null, b, tabList.get(i).getM_name(), i);
        }
        
        return !tabList.isEmpty();
    }
    
    public static void saveFile(JTabbedPane p) throws IOException {
        List<Tab> tabList = new ArrayList<>();
        for(int i = 0; i < p.getTabCount(); i++) {
            try {
                if(p.getTitleAt(i).equals("+"))
                    break;
                Tab t = new Tab(p.getTitleAt(i));
                SyntaxBox b = (SyntaxBox) p.getComponentAt(i);
                t.setM_text(b.getText());
                tabList.add(t);
            } catch (Exception e) {;
                continue;
            }
        }
        String path = getPath();
        FileWriter fw = new FileWriter(path);
        BufferedWriter out = new BufferedWriter(fw);
        for(int i = 0; i < tabList.size(); i++) {
            out.write(tabList.get(i).toString());
        }
        out.close();
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("h:mm:ss");
        IO.setStatus("Saved file at " + dateFormatLocal.format(new Date()) + ".");
    }
    
    private static String getPath() throws UnsupportedEncodingException {
         String path = System.getProperty("user.dir") + "\\properties.file";
        return URLDecoder.decode(path, "UTF-8");
    }
    
    public static void setStatus(String status) {
        ZeroVal.m_form.setTitle(status);
    }
}
