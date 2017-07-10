package com.marcuschiu.data.model.entity;

import com.marcuschiu.data.model.embeddable.Name;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by marcus.chiu on 10/17/16.
 * @Entity - represents a table in a database
 * @Table - name - name of database table to map to
 *               - if not specified, defaulted to class name
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    /**
     * All instance variables are assumed to be @Basic
     * @Basic - is implicitly implied for each variable
     *        - signifies an attribute to be persisted and a standard mapping is used
     *        - defines 2 definitions:
     *              - optional - defaults to true
     *              - fetch - defaults to FetchType.EAGER.
     *                      - FetchType.LAZY
     * @Column - allows you to specify name of database column to be persisted to
     *         - look at joiningDate instance variable comments for more info
     */

    /**
     * @Id - indicates as primary key
     * @GeneratedValue - configures way of incrementing
     * strategy = GenerationType.AUTO - also supports MySQL
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * fetch - Collections are lazy-loaded by default (fetch = FetchType.LAZY)
     *       - when lazy-loaded, it loads when getPhones() is called
     *       - when eager, it loads on the spot
     * cascade -
     * mappedBy - attribute indicates this entity "Employee" is the inverse of the relationship
     *          - and owner resides in the other entity "Phone"
     */
    //@Access(AccessType.FIELD)
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "employee")
    private List<Phone> phones;

    /**
     * @Size - specify min and max length of string
     *       - is a JSR 303 Bean Validation annotation
     */
    @Size(min=3, max=50)
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * TODO
     */
    @Embedded
    private Name nameTwo;

    /**
     * @NotNull - CharSequence, Collection, Map or Array Object is not Null but can be empty
     *          - before persisting check if there is a value
     *          - is a JSR 303 Bean Validation annotation.
     * @DateTimeFormat - is a Spring specific annotation which declares
     *                   that the field should be formatted with the given format
     * @Column - name - name of database column for mapping
     *         - nullable=(false|true) - way of declaring a column to be not-null
     *         - length - (Applies only if a string-valued column is used)
     *                  - length=32 would generate a VARCHAR(32) into database column and
     *                  - trying to insert a longer string would result in an SQL error.
     *                  - if you don't set the length, it will be 255 by default.
     *         - unique - the value must be unique within database column
     */
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "JOINING_DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joiningDate;

    /**
     * @Past - indicates date must be in the past
     * @Future - indicates date must be in the future
     */
    @NotNull
    @Past
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "BIRTH_DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthDate;

    /**
     * @NotEmpty - CharSequence, Collection, Map or Array Object is not Null and size > 0
     * @Pattern - value must match the regular expression
     *          - go here to learn more about regular expressions
     *          - https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */
    @NotEmpty
    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    @Column(name = "TEXT", nullable = false)
    @Size(max=100)
    private String text;

    /**
     * @Digits - max integer, max fraction/precision
     */
    @NotNull
    @Digits(integer=8, fraction=2)
    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    /**
     * @NotBlank - not null and trimmed length is greater than 0
     */
    @NotBlank
    @Column(name = "SSN", unique=true, nullable = false)
    private String ssn;

    /**
     * @Transient - specifies property will not be persisted to database
     */
    @Transient
    private int temp;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Phone> getPhones() { return phones; }

    public void setPhones(List<Phone> phones) { this.phones = phones; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Name getNameTwo() { return nameTwo; }

    public void setNameTwo(Name nameTwo) { this.nameTwo = nameTwo; }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Employee))
            return false;
        Employee other = (Employee) obj;
        if (id != other.id)
            return false;
        if (ssn == null) {
            if (other.ssn != null)
                return false;
        } else if (!ssn.equals(other.ssn))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", joiningDate="
                + joiningDate + ", salary=" + salary + ", ssn=" + ssn + "]";
    }
}
