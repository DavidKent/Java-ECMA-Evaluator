/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ZeroVal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zerotension
 */
public class Tab {
    private String m_name;
    private String m_text = "";

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_text() {
        return m_text;
    }

    public void setM_text(String m_text) {
        this.m_text = m_text;
    }
    public Tab(String name) {
        m_name = name;
    }
    public void appendTextData(String text) {
        m_text += text + "\n";
    }
    public static boolean isNewTab(String text) {
        Pattern p = Pattern.compile("<tab name=\"[\\w\\W\\s\\S]*\">");
        Matcher m = p.matcher(text);
        return m.find();
    }
    public static String getTabName(String text) {
        Pattern p = Pattern.compile("<tab name=\"([^\\n\\r]*)\">");
        Matcher m = p.matcher(text);
        while(m.find()) {
            return m.group(1);
        }
        return "";
    }
    public String toString() {
        return "<tab name=\"" + m_name + "\">\n" + m_text + "\n";
    }
}
