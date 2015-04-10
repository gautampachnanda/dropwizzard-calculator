package org.pachnanda.calculator.db;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.pachnanda.calculator.core.Result;

import java.util.List;

/**
 * Created by gautampachnanda on 09/04/15.
 */
public class ResultDAO extends AbstractDAO<Result> {

    public ResultDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Result> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Result create(Result result) {
        return persist(result);
    }

    public List<Result> findAll() {
        return list(namedQuery("org.pachnanda.calculator.core.Result.findAll"));
    }
}
