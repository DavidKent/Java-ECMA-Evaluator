package ZeroSyntaxHighlighter;

import ZeroJSEngine.EvaluatorEngine;
import ZeroSyntaxHighlighter.Globals.StyleType;
import ZeroSyntaxHighlighter.Globals.SyntaxColors;
import ZeroSyntaxHighlighter.Globals.SyntaxPatterns;
import Main.ZeroVal;
import IO.IO;
import Main.MainForm;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptException;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.*;

/*
 * 
 */

/**
 *
 * @author David Dolyniuk
 */
public final class SyntaxBox extends JTextPane {
    private StyleContext m_styleContext;
    private DefaultStyledDocument m_doc; 
    private Style m_default;
    private SyntaxPatternHandler m_patternHandler;
    
    public SyntaxBox() {
        m_styleContext = new StyleContext();
        m_doc = new DefaultStyledDocument();
        m_patternHandler = new SyntaxPatternHandler(m_styleContext, m_doc);
        
        Style defaultStyle = m_styleContext.getStyle(StyleContext.DEFAULT_STYLE);
        m_default = m_styleContext.addStyle("MainStyle", defaultStyle);
        StyleConstants.setLeftIndent(m_default, 16);
        StyleConstants.setRightIndent(m_default, 16);
        StyleConstants.setFirstLineIndent(m_default, 16);
        StyleConstants.setFontFamily(m_default, "serif");
        StyleConstants.setFontSize(m_default, 14);
        
        
        this.setDocument(m_doc);
        
        m_doc.setLogicalStyle(0, m_default);
        
        addSyntaxHandlers();
        final SyntaxBox self = this;
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 9) {
                    e.consume();
                    self.insertText(self.getCaretPosition(), "    ");
                }
            }
            public void keyReleased(KeyEvent e) {
                try {
                    doOnKeyReleased(e);
                } catch (BadLocationException ex) {
                    Logger.getLogger(SyntaxBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertText(int index, String text) {
        StringBuffer s1 = new StringBuffer(this.getText());
        s1.insert(index, text);
        this.setText(s1.toString());
        this.setCaretPosition(index + text.length());
    }
    
    public void setHighlightText(String text) throws BadLocationException {
        this.setText(text);
        m_patternHandler.renderSyntaxHighlighting();
    }
    
    public void doOnKeyReleased(KeyEvent e) throws BadLocationException {
        if(e.getKeyCode() == 10 && e.isAltDown()) {
            evaluateSyntaxBox();
            try {
                IO.saveFile(ZeroVal.m_form.getTabbedPane());
            } catch (IOException ex) {
                Logger.getLogger(SyntaxBox.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        else if(e.getKeyCode() == 10 || e.getKeyCode() == 86 || e.getKeyCode() >= 37 && e.getKeyCode() <= 40 || e.getKeyCode() == 8) {
            ZeroVal.setLineNums();
        }
        if(!ZeroVal.canSave) ZeroVal.canSave = true;
        int length = this.getText().length();
        int headerSize = length > 50 ? 50 : length;
        if(this.getCaretPosition() <= 50) {
            sendTitleChange(this.getText(0, headerSize));
        }
    }
    public SyntaxPatternHandler getPatternHandler() {
        return m_patternHandler;
    }
    public void addSyntaxHandlers() {
        m_patternHandler.addStyle("", SyntaxColors.DEFAULT, StyleType.IGNORE_REGEX);
        m_patternHandler.addStyle(new ReservedTokens().getKeyWords(), SyntaxColors.KEYWORD, StyleType.CHECK_WHOLE_WORD);
        m_patternHandler.addStyle(SyntaxPatterns.NUMBER, SyntaxColors.NUMBER, StyleType.DEFAULT);
        m_patternHandler.addStyle(SyntaxPatterns.OPERATORS, SyntaxColors.OPERATOR, StyleType.DEFAULT);
        m_patternHandler.addStyle(SyntaxPatterns.CONTAINERS, SyntaxColors.CONTAINER, StyleType.DEFAULT);
        m_patternHandler.addStyle(SyntaxPatterns.COMMENT_TO_EOL, SyntaxColors.COMMENT, StyleType.DEFAULT);
        m_patternHandler.addStyle(SyntaxPatterns.SINGLE_DOUBLE_STRING, SyntaxColors.STRING, StyleType.QUOTES);
        m_patternHandler.addStyle(SyntaxPatterns.COMMENT_TO_END_TOKEN, SyntaxColors.COMMENT, StyleType.DEFAULT);
    }
    
    public void evaluateSyntaxBox() {
        try {
            String text = this.getText();
            String eval = EvaluatorEngine.Evaluate(text);
            try {
                this.setHighlightText(text + "\n//Output: " + eval);
            } catch (BadLocationException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ScriptException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendTitleChange(String input) {
        try {
            JTabbedPane p = (JTabbedPane) this.getParent();
            
            p.setTitleAt(p.getSelectedIndex(), getTitle(input));
        } catch(Exception e) {
        
        }
    }
    
    private String getTitle(String input) {
        String pattern = "\\/\\/([^\\n\\r]*)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        String title = "";
        while(m.find()) {
            title = m.group(1);
            break;
        }
        return title;
    }
}
