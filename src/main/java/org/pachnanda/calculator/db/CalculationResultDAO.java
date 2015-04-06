package org.pachnanda.calculator.db;

import org.pachnanda.calculator.core.CalculationResult;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class CalculationResultDAO extends AbstractDAO<CalculationResult> {
    public CalculationResultDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<CalculationResult> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public CalculationResult create(CalculationResult calculationResult) {
        return persist(calculationResult);
    }

    public List<CalculationResult> findAll() {
        return list(namedQuery("org.pachnanda.calculator.core.CalculationResult.findAll"));
    }
}
