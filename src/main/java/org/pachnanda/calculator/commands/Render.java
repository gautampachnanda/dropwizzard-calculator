package org.pachnanda.calculator.commands;

import org.pachnanda.calculator.*;
import com.google.common.base.Optional;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.pachnanda.calculator.core.Template;

/**
 * Created by gautampachnanda on 03/04/15.
 */
public class Render  extends ConfiguredCommand<MainConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Render.class);

    public Render() {
        super("render", "Render the template data to console");
    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);
        subparser.addArgument("-i", "--include-default")
                .action(Arguments.storeTrue())
                .dest("include-default")
                .help("Also render the template with the default name");
        subparser.addArgument("names").nargs("*");
    }

    @Override
    protected void run(Bootstrap<MainConfiguration> bootstrap,
                       Namespace namespace,
                       MainConfiguration configuration) throws Exception {
        final Template template = configuration.buildTemplate();

        if (namespace.getBoolean("include-default")) {
            LOGGER.info("DEFAULT => {}", template.render(Optional.<String>absent()));
        }

        for (String name : namespace.<String>getList("names")) {
            for (int i = 0; i < 1000; i++) {
                LOGGER.info("{} => {}", name, template.render(Optional.of(name)));
                Thread.sleep(1000);
            }
        }
    }
}
