/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroVal;
import ZeroSyntaxHighlighter.SyntaxBox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JTabbedPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Zerotension
 */
public class Base {

    /**
     * @param args the command line arguments
     */
    public static MainForm form;
    public static void main(String[] args) throws BadLocationException, FileNotFoundException, UnsupportedEncodingException, IOException {
        form = new MainForm();
        form.setVisible(true);
        JTabbedPane j = form.getTabbedPane();
       
        if(!IO.loadFile(j)) {
            IO.setStatus("Welcome to ZeroVal.");
            SyntaxBox b = new SyntaxBox();
            b.setHighlightText("//New Tab Name\n//Press Alt + Enter to evaluate");
            j.insertTab("New Tab", null, b, "Blank Tab", 0);
        } else {
            IO.setStatus("Loaded tabs successfully.");
        }
        
        j.insertTab("+", null, null, "Create New Tab", j.getTabCount());
    }
}
