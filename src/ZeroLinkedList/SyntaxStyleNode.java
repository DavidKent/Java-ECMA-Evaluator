/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ZeroLinkedList;

import ZeroSyntaxHighlighter.SyntaxPatternStyle;

/**
 * @author David Dolyniuk
 */
public class SyntaxStyleNode {
    
    private SyntaxStyleNode m_next;
    private SyntaxPatternStyle m_data;
    
    public SyntaxStyleNode(SyntaxPatternStyle data) {
        m_next = null;
        m_data = data;
    }
    
    public SyntaxStyleNode(SyntaxPatternStyle data, SyntaxStyleNode next) {
        m_next = next;
        m_data = data;
    }
    
    public SyntaxPatternStyle getData() {
        return m_data;
    }
    
    public void setData(SyntaxPatternStyle data) {
        m_data = data;
    }
    
    public SyntaxStyleNode getNext() {
        return m_next;
    }
    
    public void setNext(SyntaxStyleNode n) {
        m_next = n;
    }
    
}
