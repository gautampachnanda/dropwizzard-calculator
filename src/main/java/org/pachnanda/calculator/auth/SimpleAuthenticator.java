package org.pachnanda.calculator.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.pachnanda.calculator.core.User;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, User> {
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("secret".equals(credentials.getPassword()) && "admin".equals(credentials.getUsername())) {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.absent();
    }
}
