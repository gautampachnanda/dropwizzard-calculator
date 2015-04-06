package org.pachnanda.calculator.resources;


import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.views.View;
import org.pachnanda.calculator.commands.Calculate;
import org.pachnanda.calculator.core.CalculationResult;
import org.pachnanda.calculator.core.Template;
import org.pachnanda.calculator.db.CalculationResultDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by gautampachnanda on 03/04/15.
 */
@Path("/calculate")
//@Produces(MediaType.APPLICATION_JSON)
//@Produces("text/html;charset=UTF-8")
public class CalculationResource {


    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationResource.class);

    private final CalculationResultDAO calculationResultDAO;
    private final Template template;
    private final AtomicLong counter;

    public CalculationResource(CalculationResultDAO calculationResultDAO, Template template) {
        this.calculationResultDAO = calculationResultDAO;
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/add")
    @UnitOfWork
    public View add(@QueryParam("first") String first, @QueryParam("second") String second) {
        LOGGER.info("Received a first: {} second:{}", first, second);
        Calculate calculate = new Calculate(first, second);
        CalculationResult result = new CalculationResult();
        try {
            result = calculate.run();

            this.calculationResultDAO.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Optional<String> templateName = Optional.of("calculationResult");
        LOGGER.info("Result:{}",template.render(templateName));
        return new View("/view_mustache/"+result.getId(), Charsets.UTF_8) {
        };
       // new PathRedirect("/old", "/new");
    }
}
