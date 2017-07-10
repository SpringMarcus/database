package com.marcuschiu.data.model.embeddable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by marcus.chiu on 10/24/16.
 */
@Embeddable
public class Name {

    @Column(name="first")
    @Access(AccessType.FIELD)
    private String first;

    @Column(name = "middle")
    @Access(AccessType.FIELD)
    private String middle;

    @Column(name = "last")
    @Access(AccessType.FIELD)
    private String last;

    public void setFirst(String first) { this.first = first; }

    public String getFirst() { return first; }

    public void setMiddle(String middle) { this.middle = middle; }

    public String getMiddle() { return middle; }

    public void setLast(String last) { this.last = last; }

    public String getLast() { return last; }
}
