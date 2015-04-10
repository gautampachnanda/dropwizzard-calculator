package org.pachnanda.calculator.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gautampachnanda on 09/04/15.
 */
public class ProtectedResults {

    @JsonProperty
    List<Result> results;

    public ProtectedResults() {
        Result one = new Result("I am A", 1l);
        Result two = new Result("I am B", 2l);
        Result three = new Result("I am C", 3l);

        this.setResults(Arrays.asList(one, two, three));
        for (Result result : getResults()) {
            System.out.println("XXXXXXXX" + result);
        }
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
