package org.dmiit3iy.service;

import org.dmiit3iy.model.Student;

import java.util.List;

public interface StudentService {
    void add(long userId, Student student);
    List<Student> get();
    Student get(long id);
    Student update(Student student);
    Student delete(long id);
}
