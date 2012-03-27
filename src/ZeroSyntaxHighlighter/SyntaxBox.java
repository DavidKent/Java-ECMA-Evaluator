package ZeroSyntaxHighlighter;

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
        SyntaxPatternHandler h = new SyntaxPatternHandler(m_styleContext, m_doc);
        h.addStyle("", SyntaxColors.DEFAULT, 3);
        h.addStyle(new ReservedTokens().getKeyWords(), SyntaxColors.DEFAULT, 1);
        h.addStyle(SyntaxPatterns.SINGLE_DOUBLE_STRING, SyntaxColors.STRING, 2);
        h.addStyle(SyntaxPatterns.COMMENT_TO_EOL, SyntaxColors.COMMENT, 1);
        h.addStyle(SyntaxPatterns.COMMENT_TO_END_TOKEN, SyntaxColors.COMMENT, 1);
    }
}
