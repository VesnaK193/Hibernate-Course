package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMarryDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			int theId = 2;
			Student tempStudent = session.get(Student.class, theId);
			
			Course tempCourse1 = new Course("Hibernate");
			Course tempCourse2 = new Course("Spring");
			Course tempCourse3 = new Course("Java");
			
			tempStudent.addCourses(tempCourse1);
			tempStudent.addCourses(tempCourse2);
			tempStudent.addCourses(tempCourse3);
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			
			//add clean up code
			session.close();
			
			factory.close();
		}
		
	}

}
