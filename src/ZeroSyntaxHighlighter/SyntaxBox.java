package ZeroSyntaxHighlighter;

import ZeroSyntaxHighlighter.Globals.StyleType;
import ZeroSyntaxHighlighter.Globals.SyntaxColors;
import ZeroSyntaxHighlighter.Globals.SyntaxPatterns;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.*;

/*
 * 
 */

/**
 *
 * @author Zerotension
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
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    doOnKeyReleased(e);
                } catch (BadLocationException ex) {
                    Logger.getLogger(SyntaxBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void doOnKeyReleased(KeyEvent e) throws BadLocationException {
        m_patternHandler.renderSyntaxHighlighting();
    }
    
    public void addSyntaxHandlers() {
        m_patternHandler.addStyle("", SyntaxColors.DEFAULT, StyleType.IGNORE_REGEX);
        m_patternHandler.addStyle(new ReservedTokens().getKeyWords(), SyntaxColors.KEYWORD, StyleType.DEFAULT);
        m_patternHandler.addStyle(SyntaxPatterns.SINGLE_DOUBLE_STRING, SyntaxColors.STRING, StyleType.QUOTES);
        m_patternHandler.addStyle(SyntaxPatterns.COMMENT_TO_EOL, SyntaxColors.COMMENT, StyleType.DEFAULT);
        m_patternHandler.addStyle(SyntaxPatterns.COMMENT_TO_END_TOKEN, SyntaxColors.COMMENT, StyleType.DEFAULT);
    }
}
