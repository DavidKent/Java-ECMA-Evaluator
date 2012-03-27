/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroSyntaxHighlighter.Globals;

/**
 *
 * @author Zerotension
 */
public class SyntaxPatterns {
    public static final String SINGLE_DOUBLE_STRING = "[\\\"\\'][^\\\"\\']*[\\\"\\']";
    public static final String COMMENT_TO_EOL = "[\\/]{2}[^\\n\\r]*";
    public static final String COMMENT_TO_END_TOKEN = "\\/\\*[^\\*\\/]*\\*\\/";
    
    
}
