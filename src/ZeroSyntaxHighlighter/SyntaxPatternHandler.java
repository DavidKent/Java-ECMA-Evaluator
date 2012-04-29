/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroSyntaxHighlighter;

import ZeroLinkedList.SyntaxStyleLinkedList;
import java.awt.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

/**
 *
 * @author David Dolyniuk
 */
public class SyntaxPatternHandler {
    private SyntaxStyleLinkedList m_patternStyles = new SyntaxStyleLinkedList();
    private StyleContext m_sc;
    private DefaultStyledDocument m_doc;
    
    public SyntaxPatternHandler(StyleContext sc, DefaultStyledDocument doc) {
        m_doc = doc;
        m_sc = sc;
    }
    
    public void addStyle(String pattern, Color c, int type) {
        m_patternStyles.append(new SyntaxPatternStyle(m_sc, m_doc, pattern, c, type));
    }
    
    public void addStyle(String pattern, String hex, int type) {
        m_patternStyles.append(new SyntaxPatternStyle(m_sc, m_doc, pattern, Color.decode(hex), type));
    }
    
    public void renderSyntaxHighlighting() throws BadLocationException {
        for(int i = 0; i < m_patternStyles.getSize(); i++) {
            m_patternStyles.get(i).applyEffect();
        }
    }
}
