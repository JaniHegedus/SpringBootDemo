package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent())
        {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id) {
        Boolean exists = studentRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException("student with this id: "+id+"doesn't exists");
        }
        studentRepository.deleteById(id);
    }
    @Transactional
    public void updateStudent(Long id, String name, String email)
    {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("student with id: "+id+"does not exist"));
        if(name!=null&&name.length()>0&&!student.getName().equals(name))
        {
            student.setName(name);
        }
        if(email!=null&&email.length()>0&&!student.getEmail().equals(email))
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent())
            {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
