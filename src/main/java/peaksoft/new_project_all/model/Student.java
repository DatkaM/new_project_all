package peaksoft.new_project_all.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.new_project_all.dto.request.StudentRegisterRequest;
import peaksoft.new_project_all.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(generator = "student_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen",sequenceName = "student_seq",allocationSize = 1)
    private Long id;
    private String name;
    private int age;
    private String phoneNumber;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private AuthInfo authInfo;


    public Student(StudentRegisterRequest request) {
        this.name = request.getFirstName();
        this.age = request.getAge();
        this.phoneNumber = request.getPhoneNumber();
        AuthInfo authInfo1 = new AuthInfo();
        authInfo1.setEmail(request.getEmail());
        authInfo1.setPassword(request.getPassword());
        authInfo1.setRole(Role.STUDENT);
        this.authInfo = authInfo1;
    }

    public Student(String name, int age, String phoneNumber, String email, AuthInfo authInfo) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.authInfo = authInfo;
    }
}
