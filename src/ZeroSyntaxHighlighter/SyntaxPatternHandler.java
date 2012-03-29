/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroSyntaxHighlighter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

/**
 *
 * @author Zerotension
 */
public class SyntaxPatternHandler {
    List<SyntaxPatternStyle> m_patternStyles = new ArrayList<>();
    private StyleContext m_sc;
    private DefaultStyledDocument m_doc;
    
    public SyntaxPatternHandler(StyleContext sc, DefaultStyledDocument doc) {
        m_doc = doc;
        m_sc = sc;
    }
    
    public void addStyle(String pattern, Color c, int type) {
        m_patternStyles.add(new SyntaxPatternStyle(m_sc, m_doc, pattern, c, type));
    }
    
    public void addStyle(String pattern, String hex, int type) {
        m_patternStyles.add(new SyntaxPatternStyle(m_sc, m_doc, pattern, Color.decode(hex), type));
    }
    
    public void renderSyntaxHighlighting() throws BadLocationException {
        for(int i = 0; i < m_patternStyles.size(); i++) {
            m_patternStyles.get(i).applyEffect();
        }
    }
}
