package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetails;

public class CreateCourseDemo {

	public static void main(String[] args) {
		//Create the session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetails.class).
				addAnnotatedClass(Course.class).buildSessionFactory();
		
		//get the current session factory
		Session currentSession = factory.getCurrentSession();
		
		try {
			//begin transaction
			currentSession.beginTransaction();
			
			//get the instructor
			int theId = 1;
			Instructor theInstructor = currentSession.get(Instructor.class, theId);
			
			//Create courses
			Course c1 = new Course("Mathematics");
			Course c2 = new Course("Physics");
			Course c3 = new Course("Chemistry");
			
			//associate Course and instructor
			theInstructor.add(c1);
			theInstructor.add(c2);
			theInstructor.add(c3);
			
			//save instructor
			currentSession.save(c1);
			currentSession.save(c2);
			currentSession.save(c3);
			System.out.println(theInstructor);
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
