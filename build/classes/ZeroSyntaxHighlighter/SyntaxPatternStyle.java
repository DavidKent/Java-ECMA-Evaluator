/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroSyntaxHighlighter;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.*;

/**
 *
 * @author Zerotension
 */
public class SyntaxPatternStyle {

    private StyleContext m_sc;
    private DefaultStyledDocument m_doc;
    private Style m_newStyle;
    private String m_pattern;
    private int m_type;
    private static int STYLES_CREATED = 0;

    public SyntaxPatternStyle(StyleContext sc, DefaultStyledDocument doc, String pattern, Color color, int type) {
        m_sc = sc;
        m_doc = doc;
        m_pattern = pattern;
        m_type = type;
        m_newStyle = m_sc.addStyle("NewStyle" + STYLES_CREATED, null);
        StyleConstants.setForeground(m_newStyle, color);
        STYLES_CREATED++;
    }

    public void applyEffect() throws BadLocationException {
        if (m_type == 3) {
            m_doc.setCharacterAttributes(0, m_doc.getLength(), m_newStyle, true);
            return;
        }
        Pattern p = Pattern.compile(m_pattern);
        Matcher m = p.matcher(m_doc.getText(0, m_doc.getLength()));
        while (m.find()) {
            int group = m_type == 4 ? 1 : 0;
            m_doc.setCharacterAttributes(m.start(group), m.end(group) - m.start(group), m_newStyle, true);
        }
    }
}
