package com.gestion.athlete.models;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Objects;

@Embeddable
public class ResultatId implements Serializable {

    private Long codeA;       
    private Long numCourse;   

    public ResultatId() {}

    public ResultatId(Long codeA, Long numCourse) {
        this.codeA = codeA;
        this.numCourse = numCourse;
    }



    public Long getCodeA() {
        return codeA;
    }

    public void setCodeA(Long codeA) {
        this.codeA = codeA;
    }

    public Long getNumCourse() {
        return numCourse;
    }

    public void setNumCourse(Long numCourse) {
        this.numCourse = numCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultatId)) return false;
        ResultatId that = (ResultatId) o;
        return Objects.equals(codeA, that.codeA) &&
               Objects.equals(numCourse, that.numCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeA, numCourse);
    }
}
