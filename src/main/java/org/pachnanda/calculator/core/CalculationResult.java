package org.pachnanda.calculator.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CalculationResult")
@NamedQueries({
        @NamedQuery(
                name = "org.pachnanda.calculator.core.CalculationResult.findAll",
                query = "SELECT cr FROM CalculationResult cr"
        )
})
public class CalculationResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1901038593467639617L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    @Column(name = "firstNumber", nullable = false)
    @JsonProperty
    private Integer firstNumber;

    @Column(name = "secondNumber", nullable = false)
    @JsonProperty
    private Integer secondNumber;

    @Column(name = "result", nullable = false)
    @JsonProperty
    private Integer result;

    public CalculationResult() {
        // Jackson deserialization
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getFirstNumber() {

        return firstNumber;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Integer getResult(){
        return this.result;
    }

    public void setResult(Integer result){
        this.result=result;
    }

}
