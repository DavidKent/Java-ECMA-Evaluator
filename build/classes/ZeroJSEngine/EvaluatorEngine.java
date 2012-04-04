/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZeroJSEngine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author David Dolyniuk
 */
public class EvaluatorEngine {
    public static String Evaluate(String s) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return engine.eval(s) + "";
    }
}
