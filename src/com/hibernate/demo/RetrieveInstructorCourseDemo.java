package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetails;

public class RetrieveInstructorCourseDemo {

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
			
			//print instructor
			System.out.println("Instructor is :"+theInstructor);
			
			//Print Course
			System.out.println("Respective Course is :"+theInstructor.getCourses());
			
			//Print instructor details
			System.out.println("Instructor Details is :"+theInstructor.getInstructorDetail());
			
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
