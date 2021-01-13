package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		//create session factory  
		SessionFactory factory =new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
			//create the objects
			Instructor tempInstructor=new Instructor("sameer","bharsa","abc@xyz.com");
			
			
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://asas.com/youtube","nothing");
			
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save the instructor
			//
			//Note: this will also save the details object
			//because of CascadeType.ALL
			System.out.println("Saving Instructor: "+ tempInstructor);
			session.save(tempInstructor);
			
	
			//commit transaction
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}
