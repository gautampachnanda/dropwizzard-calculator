package org.pachnanda.calculator;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.pachnanda.calculator.auth.ExampleAuthenticator;
import org.pachnanda.calculator.commands.Render;
import org.pachnanda.calculator.core.CalculationResult;
import org.pachnanda.calculator.core.Template;
import org.pachnanda.calculator.core.User;
import org.pachnanda.calculator.db.CalculationResultDAO;
import org.pachnanda.calculator.filter.DateRequiredFeature;
import org.pachnanda.calculator.health.TemplateHealthCheck;
import org.pachnanda.calculator.resources.*;

import java.util.Map;

//import com.google.common.collect.ImmutableMap;

/**
 * Created by gautampachnanda on 03/04/15.
 */
public class MainApplication extends Application<MainConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    private final HibernateBundle<MainConfiguration> hibernateBundle =
            new HibernateBundle<MainConfiguration>(CalculationResult.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(MainConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
           };

    @Override
    public String getName() {
        return "calculator";
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addCommand(new Render());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<MainConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MainConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<MainConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(MainConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) {
        final CalculationResultDAO dao = new CalculationResultDAO(hibernateBundle.getSessionFactory());
        final Template template = configuration.buildTemplate();

        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(DateRequiredFeature.class);

        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(new ExampleAuthenticator(),
                "SUPER SECRET STUFF",
               User.class)));
        environment.jersey().register(new ProtectedResource());
        environment.jersey().register(new CalculationResource(dao,template));
        environment.jersey().register(new CalculationResultResource(dao));
        environment.jersey().register(new ViewResource());
       environment.jersey().register(new FilteredResource());
    }
}
