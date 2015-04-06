package org.pachnanda.calculator.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by gautampachnanda on 04/04/15.
 */
public class ToAdd {

    @NotNull
    private Integer number;

    @JsonProperty
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
