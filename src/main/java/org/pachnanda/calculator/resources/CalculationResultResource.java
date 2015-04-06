package org.pachnanda.calculator.resources;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.pachnanda.calculator.core.CalculationResult;
import org.pachnanda.calculator.db.CalculationResultDAO;
import org.pachnanda.calculator.views.CalculationResultView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/calculation-result")
@Produces(MediaType.APPLICATION_JSON)
public class CalculationResultResource {

    private final CalculationResultDAO calculationResultDAO;

    public CalculationResultResource(CalculationResultDAO calculationResultDAO) {
        this.calculationResultDAO = calculationResultDAO;
    }

    @POST
    @UnitOfWork
    public CalculationResult createPerson(CalculationResult calculationResult) {
        return calculationResultDAO.create(calculationResult);
    }

    @GET
    @Path("/all")
    @UnitOfWork
    public List<CalculationResult> listCalculationResults() {
        return calculationResultDAO.findAll();
    }

    @GET
    @Path("/{calculationResultId}")
    @UnitOfWork
    public CalculationResult getCalculationResult(@PathParam("calculationResultId") LongParam calculationResultId) {
        return findSafely(calculationResultId.get());
    }

    @GET
    @Path("/view_freemarker/{calculationResultId}")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public CalculationResultView getCalculationResultViewFreemarker(@PathParam("calculationResultId") LongParam calculationResultId) {
        return new CalculationResultView(CalculationResultView.Template.FREEMARKER, findSafely(calculationResultId.get()));
    }

    @GET
    @Path("/view_mustache/{calculationResultId}")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public CalculationResultView getCalculationResultViewMustache(@PathParam("calculationResultId") LongParam calculationResultId) {
        return new CalculationResultView(CalculationResultView.Template.MUSTACHE, findSafely(calculationResultId.get()));
    }

    private CalculationResult findSafely(long calculationResultId) {
        final Optional<CalculationResult> calculationResult = calculationResultDAO.findById(calculationResultId);
        if (!calculationResult.isPresent()) {
            throw new NotFoundException("No such user.");
        }
        return calculationResult.get();
    }
}