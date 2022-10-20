package com.luv2code.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@NoArgsConstructor
@Getter
@Setter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    //w klasie bazowej ustawia się mappedBy na pole z klasy podrzędnej.
    //Hibernate po mappedBy dochodzi do pola instruktor w Course i stamtąd bierze JoinColumn
    //cascade wszystko poza delete, żeby się nic kaskadowo nie usuwało
    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor(String firstName, String lastName, String email) {
        //nie dodaje joinowych kolumn do konstruktora
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //metoda do dodawania Course do listy courses w Instructor - ustawia też Instructor w Course
    public void addCourse(Course course) {
        if (courses == null) courses = new ArrayList<>();
        courses.add(course);
        course.setInstructor(this);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                ", courses=" + courses +
                '}';
    }
}
