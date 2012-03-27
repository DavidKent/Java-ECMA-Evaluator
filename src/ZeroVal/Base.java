/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroVal;
import ZeroSyntaxHighlighter.SyntaxBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author Zerotension
 */
public class Base {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainForm f = new MainForm();
        f.setVisible(true);
        JTabbedPane j = f.getTabbedPane();
        j.addTab("hello", new SyntaxBox());
        j.addTab("test", new SyntaxBox());
    }
}
