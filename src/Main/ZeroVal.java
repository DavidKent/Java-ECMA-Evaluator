/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import IO.IO;
import ZeroSyntaxHighlighter.SyntaxBox;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;

/**
 *
 * @author David Dolyniuk
 */
public class ZeroVal {

    /**
     * @param args the command line arguments
     */
    public static MainForm m_form;
    private static Timer m_timer;
    public static boolean canSave = false;
    public static void main(String[] args) throws BadLocationException, FileNotFoundException, UnsupportedEncodingException, IOException {
        m_form = new MainForm();
        m_form.setVisible(true);
        JTabbedPane j = m_form.getTabbedPane();
        m_form.setTitle("ZeroVal");
        if(!IO.loadFile(j)) {
            IO.setStatus("Welcome to ZeroVal.");
            SyntaxBox b = new SyntaxBox();
            b.setHighlightText("//New Tab Name\n//Press Alt + Enter to evaluate");
            j.insertTab("New Tab", null, b, "Blank Tab", 0);
        } else {
            IO.setStatus("Loaded tabs successfully.");
        }
        
        m_timer = new Timer(60, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ((SyntaxBox) m_form.getTabbedPane().getSelectedComponent()).getPatternHandler().renderSyntaxHighlighting();
                } catch (BadLocationException ex) {
                    Logger.getLogger(ZeroVal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        m_timer.start();
        j.insertTab("+", null, null, "Create New Tab", j.getTabCount());
    }
}
