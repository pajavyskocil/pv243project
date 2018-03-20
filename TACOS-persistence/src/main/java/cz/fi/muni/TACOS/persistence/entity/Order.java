package cz.fi.muni.TACOS.persistence.entity;

import cz.fi.muni.TACOS.persistence.enums.OrderState;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vyskocil Pavel <vyskocilpavel@muni.cz>
 */
@Entity
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;


    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private OrderState state;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private LocalDate submitted;


    private LocalDate finished;

    public Order() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public LocalDate getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDate submitted) {
        this.submitted = submitted;
    }

    public LocalDate getFinished() {
        return finished;
    }

    public void setFinished(LocalDate finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Order)) return false;
        Order order = (Order) o;

        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getSubmitted(), order.getSubmitted());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getSubmitted());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", state=" + state +
                ", submitted=" + submitted +
                ", finished=" + finished +
                '}';
    }
}
