/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ZeroLinkedList;

import ZeroSyntaxHighlighter.SyntaxPatternStyle;

/**
 * @author David Dolyniuk
 */
public class SyntaxStyleLinkedList {
    private SyntaxStyleNode m_head;
    private int m_size;
    public SyntaxStyleLinkedList() {
        m_head = new SyntaxStyleNode(null);
        m_size = 0;
    }
    
    public void append(SyntaxPatternStyle sps) {
        SyntaxStyleNode temp = new SyntaxStyleNode(sps);
        SyntaxStyleNode current = m_head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(temp);
        m_size++;
    }
    
    public SyntaxPatternStyle get(int index) {
        if(index < 0) return null;
        SyntaxStyleNode current = m_head.getNext();
        for(int i = 0; i < index; i++) {
            if(current.getNext() == null)
                return null;
            current = current.getNext();
        }
        return current.getData();
    }
    
    public int getSize() {
        return m_size;
    }
} 
