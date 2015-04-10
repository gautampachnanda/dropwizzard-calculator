package org.pachnanda.calculator.views;

import io.dropwizard.views.View;
import org.pachnanda.calculator.core.Result;

/**
 * Created by gautampachnanda on 09/04/15.
 */
public class ProtectedResultView extends View {


    private Result result;

    public enum Template{
        FREEMARKER("freemarker/protectedResult.ftl"),
        MUSTACHE("mustache/protectedResult.mustache");

        private String templateName;
        private Template(String templateName){
            this.templateName = templateName;
        }

        public String getTemplateName(){
            return templateName;
        }
    }


    public ProtectedResultView(Template template, Result result) {
        super(template.getTemplateName());
        this.result=result;
    }

    public Result getResult() {
        return this.result;
    }
}
