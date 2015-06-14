package com.module.calculator;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.Module;

public interface ICalculator extends Module{

    /**
     * Launch Calculator.
     */
    void launchCalculator();

    /**
     * Check input.
     *
     * @throws UiObjectNotFoundException
     */
    void checkInput() throws UiObjectNotFoundException;

    /**
     * Check operator, like +, _, *, /.
     * @throws UiObjectNotFoundException
     */
    void checkMathOperator() throws UiObjectNotFoundException;

    /**
     * Check special operator.
     */
    void checkSpecialOperator() throws UiObjectNotFoundException;
    
    /**
     * Clear history
     */
    void clearHistory() throws UiObjectNotFoundException;

}
