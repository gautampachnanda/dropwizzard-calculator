package org.pachnanda.calculator.views;

import io.dropwizard.views.View;
import org.pachnanda.calculator.core.ProtectedResults;

/**
 * Created by gautampachnanda on 09/04/15.
 */
public class ProtectedResultsView extends View {


    private ProtectedResults protectedResults;

    public enum Template {
        FREEMARKER("freemarker/protectedResults.ftl"),
        MUSTACHE("mustache/protectedResults.mustache");

        private String templateName;

        private Template(String templateName) {
            this.templateName = templateName;
        }

        public String getTemplateName() {
            return templateName;
        }
    }


    public ProtectedResultsView(Template template, ProtectedResults protectedResults) {
        super(template.getTemplateName());
        this.protectedResults = protectedResults;
    }

    public ProtectedResults getProtectedResults() {
        return this.protectedResults;
    }
}
