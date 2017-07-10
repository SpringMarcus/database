package com.marcuschiu.data.model.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by marcus.chiu on 10/26/16.
 */
@Entity
@Table(name = "PHONE")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id", nullable = false)
    private int id;

    @NotBlank
    @Size(max=255)
    @Column(name = "number", nullable = false)
    private String number;

    /**
     * @JoinColumn - indicates entity is the owner of the relationship (that is: the
     *               corresponding table has a column with a foreign key to the referenced table)
     *             - specifies a mapped column for joining an entity
     *             - can be used in any class (Parent and/or Child)
     *             - ideally used in only one side (Parent or Child)
     *
     * mappedBy - look at Employee class
     *          - attribute indicates that the entity in this side is the inverse of the relationship
     *            and owner resides in the other entity
     * name - name of database column
     * foreignKey -
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "EMPLOYEE_ID_FK"))
    private Employee employee;

    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() { return employee;
    }

    public void setEmployee(Employee employee) { this.employee = employee; }
}
