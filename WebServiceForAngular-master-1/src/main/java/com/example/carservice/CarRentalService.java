package com.example.carservice;

import java.util.ArrayList;
import java.util.List;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;


@RestController
public class CarRentalService {
	
	private List<Car> cars = new ArrayList<Car>();
	
	public CarRentalService() {
		cars.add(new Car("11AA23", "Ferrari", 1000, 5));
		cars.add(new Car("33BB44", "Porshe", 2222, 6));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/cars", method=RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK) 
	public List<Car> getListOfCars(){
		return cars;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/cars/{plateNumber}", method=RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK) 
	public Car getListOfCars(@PathVariable(value="plateNumber") String plateNumber){
		for(Car car: cars) {
			if(car.getPlateNumber().equals(plateNumber)) {
				return car;
			}
		}
		return null;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/cars", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Car addCar(@RequestBody Car car) throws Exception{
		cars.add(car);
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");

			Queue queue = (Queue) applicationContext.getBean("queue");

			// Create a connection.
			QueueConnection connection = factory.createQueueConnection();

			// Open a session without transaction and acknowledge automatic
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create a sender
			QueueSender sender = session.createSender(queue);

			// Create an object
			//Pour tester: Message message = session.createTextMessage("yo");
			ObjectMessage aCar = session.createObjectMessage(car);

			//Send the object
			//Pour tester: sender.send(message);
			sender.send(aCar);

			// Close the session
			session.close();

			// Close the connection
			connection.close();

			/* TOPIC
			TopicConnectionFactory factoryTopic = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			Topic topic = (Topic) applicationContext.getBean("topic");
			TopicConnection connection = factoryTopic.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher sender = session.createPublisher(topic);
			Message message = session.createTextMessage("coucou topic");
			sender.send(message);
			session.close();
			connection.close();
			*/
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return car;
	}
}
