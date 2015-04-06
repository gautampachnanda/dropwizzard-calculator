package org.pachnanda.calculator.views;

import io.dropwizard.views.View;
import org.pachnanda.calculator.core.CalculationResult;

public class CalculationResultView extends View {
    private final CalculationResult calculationResult;
    public enum Template{
    	FREEMARKER("freemarker/calculationResult.ftl"),
    	MUSTACHE("mustache/calculationResult.mustache");
    	
    	private String templateName;
    	private Template(String templateName){
    		this.templateName = templateName;
    	}
    	
    	public String getTemplateName(){
    		return templateName;
    	}
    }

    public CalculationResultView(Template template, CalculationResult calculationResult) {
        super(template.getTemplateName());
        this.calculationResult = calculationResult;
    }

    public CalculationResult getCalculationResult() {
        return calculationResult;
    }
}