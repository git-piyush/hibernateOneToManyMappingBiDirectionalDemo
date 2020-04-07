package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetails;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		//Create the session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).
				addAnnotatedClass(Course.class).buildSessionFactory();
		
		//get the current session factory
		Session currentSession = factory.getCurrentSession();
		
		try {
			//begin transaction
			currentSession.beginTransaction();
			
			//delete the course but not instructor
			int theId = 10;
			Course theCourse = currentSession.get(Course.class, theId);
			
			//delete thrCourse
			currentSession.delete(theCourse);
			
			//commit transaction
			currentSession.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurs");
		}finally {
			currentSession.close();
		}

	}

}
