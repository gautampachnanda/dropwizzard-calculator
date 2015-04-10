package org.pachnanda.calculator.core;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gautampachnanda on 09/04/15.
 */
@Entity
@Table(name = "Result")
@NamedQueries({
        @NamedQuery(
                name = "org.pachnanda.calculator.core.Result.findAll",
                query = "SELECT r FROM Result r"
        )
})
public class Result implements Serializable {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Result(String name, Long id) {
        this.setName(name);
        this.setId(id);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
