package com.gestion.athlete.repositories;

import com.gestion.athlete.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // You can add methods like findByVille or findByDateCourse later
}
