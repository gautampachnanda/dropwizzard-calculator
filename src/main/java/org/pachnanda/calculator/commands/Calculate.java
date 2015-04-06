package org.pachnanda.calculator.commands;

import org.pachnanda.calculator.core.CalculationResult;
import org.pachnanda.calculator.core.ToAdd;

/**
 * Created by gautampachnanda on 04/04/15.
 */
public class Calculate {

    CalculationResult result;
    public Calculate(String first, String second) {
        ToAdd firstToAdd= new ToAdd();
        firstToAdd.setNumber(Integer.parseInt(first));
        ToAdd secondToAdd= new ToAdd();
        secondToAdd.setNumber(Integer.parseInt(second));
        result = new CalculationResult();
        result.setFirstNumber(firstToAdd.getNumber());
        result.setSecondNumber(secondToAdd.getNumber());

    }
    public CalculationResult run() throws Exception {
        result.setResult(result.getFirstNumber()+result.getSecondNumber());
        return result;
    }
}
