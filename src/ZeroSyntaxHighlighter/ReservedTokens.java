/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroSyntaxHighlighter;

/**
 *
 * @author Zerotension
 */
public class ReservedTokens {
    public String getKeyWords(){ 
        String s = "true|false|break|continue|do|for|import|new|this|void|case|default|else|"
                + "function|in|return|typeof|while|comment|";
        s+= "delete|export|if|label|switch|var|with|catch|class|const|debugger|enum|extends|";
        s+= "finally|super|throw|try|alert|eval|Link|outerHeight|scrollTo|Anchor|FileUpload|"
                + "location|outerWidth|Select";
        s+= "prototype";
        return s;
    }
}
