package org.dmiit3iy.service;

import org.dmiit3iy.model.Student;
import org.dmiit3iy.repository.StudentRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Student student) {

        try {
            studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Student has already added!");
        }

    }

    @Override
    public List<Student> get() {
        return studentRepository.findAll();
    }

    @Override
    public Student get(long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student does not exists!"));
    }

    @Override
    public Student update(Student student) {
        Student base = this.get(student.getId());
        base.setFio(student.getFio());
        base.setAge(student.getAge());
        base.setSalary(student.getSalary());
        base.setNum(student.getNum());
        try {
            this.studentRepository.save(base);
            return base;
        } catch (DataIntegrityViolationException e) { //TODO handle SpringData exception
            throw new IllegalArgumentException("Student is already exists!");
        }
    }

    @Override
    public Student delete(long id) {
        Student student = this.get(id);
        this.studentRepository.deleteById(id);
        return student;
    }
}
