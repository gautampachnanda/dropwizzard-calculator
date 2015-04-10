package org.pachnanda.calculator.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.pachnanda.calculator.core.ProtectedResults;
import org.pachnanda.calculator.core.Result;
import org.pachnanda.calculator.core.User;
import org.pachnanda.calculator.views.ProtectedResultView;
import org.pachnanda.calculator.views.ProtectedResultsView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by gautampachnanda on 09/04/15.
 */
@Path("/operator")
public class ProtectedViewResource {

    @GET
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public ProtectedResultsView home(@Auth User user) {
        ProtectedResults results = new ProtectedResults();
        return new ProtectedResultsView(ProtectedResultsView.Template.FREEMARKER, results);
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    @Path("/display/{id}")
    public ProtectedResultView home(@Auth User user, @PathParam("id") LongParam id) {
        ProtectedResults results = new ProtectedResults();

        return new ProtectedResultView(ProtectedResultView.Template.FREEMARKER,
                findById(id.get()));
    }

    public Result findById(Long id) {
        ProtectedResults results = new ProtectedResults();
        for (Result result : results.getResults()) {
            System.out.println(result);
            if (result.getId().equals(id)) {
                return result;
            }
        }
        return null;
    }
}
