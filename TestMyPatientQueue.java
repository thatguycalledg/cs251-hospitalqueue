import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestMyPatientQueue {
	Random random;

	/**
	 * initialize the random number generator
	 */
	@Before
	public void initRandom() {
		random = new Random(System.currentTimeMillis());
	}

	/**
	 * generate a random alphabetic string
	 * @return
	 */
	public String randomName() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 7; i++)
			sb.append('a'+random.nextInt(26));
		return sb.toString();
	}

	/**
	 * test size, enqueue, and dequeue methods
	 */
	@Test
	public void testSimpleQueue() {
		// declare and initialize queues
		MyPatientQueue Q = new MyPatientQueue();
		// use LinkedList as reference queue
		LinkedList<Patient> refQ = new LinkedList<Patient>();
		// sizes better be the same initially
		assertEquals(refQ.size(),Q.size());
		Patient p;
		int k = 0; // "arrival time"
		for (int j = 0; j < 20; j++ ) {
			// generate 0 to 100 patients and enqueue them
			for (int i = 0; i < random.nextInt(100); i++) {
				p = new Patient(randomName(),k++,random.nextInt(4)+1);
				refQ.addLast(p);
				Q.enqueue(p);
				// sizes better be the same at all times
				assertEquals(refQ.size(),Q.size());
			}
			// dequeue some of them
			for (int i = 0; i < random.nextInt(refQ.size()); i++) {
				// check that the correct patient is dequeued
				assertEquals(refQ.removeFirst(),Q.dequeue());
				// sizes better be the same at all times
				assertEquals(refQ.size(),Q.size());
			}
		}

		// check that the rest of the patients dequeue correctly
		while (!refQ.isEmpty()) {
			assertEquals(refQ.removeFirst(),Q.dequeue());
			assertEquals(refQ.size(),Q.size());
		}

		// MyPatientQueue should return null when empty
		assertNull(Q.dequeue());
		
		// "circularity" test
		Q = new MyPatientQueue();
		refQ = new LinkedList<Patient>();
		for (int i = 1; i < 27; i++) {
			// enqueue i patients
			for (int j = 0; j < i; j++) {
				p = new Patient(randomName(),k++,random.nextInt(4)+1);
				refQ.addLast(p);
				Q.enqueue(p);
				// sizes better be the same at all times
				assertEquals(refQ.size(),Q.size());
			}
			// enqueue and dequeue a single patient many times
			for (int j = 0; j < 280; j++) {
				p = new Patient(randomName(),k++,random.nextInt(4)+1);
				refQ.addLast(p);
				Q.enqueue(p);
				// sizes better be the same at all times
				assertEquals(refQ.size(),Q.size());
				
				// check that the correct patient is dequeued
				assertEquals(refQ.removeFirst(),Q.dequeue());
				// sizes better be the same at all times
				assertEquals(refQ.size(),Q.size());
			}
			// dequeue all patients
			// check that the rest of the patients dequeue correctly
			while (!refQ.isEmpty()) {
				assertEquals(refQ.removeFirst(),Q.dequeue());
				assertEquals(refQ.size(),Q.size());
			}

			// MyPatientQueue should return null when empty
			assertNull(Q.dequeue());
		}
	}

	/**
	 * test get method, testSimpleQueue must pass first
	 */
	@Test
	public void testGet() {
		// declare and initialize queues
		MyPatientQueue Q = new MyPatientQueue();
		// use LinkedList as reference queue
		LinkedList<Patient> refQ = new LinkedList<Patient>();
		Patient p;
		int k = 0; // "arrival time"
		for (int j = 0; j < 20; j++ ) {
			// generate 0 to 100 patients and enqueue them
			for (int i = 0; i < random.nextInt(100); i++) {
				p = new Patient(randomName(),k++,random.nextInt(4)+1);
				refQ.addLast(p);
				Q.enqueue(p);
			}
			// check some patients at random
			for (int i = 0; i < random.nextInt(refQ.size()); i++) {
				int l = random.nextInt(refQ.size());
				assertEquals(refQ.get(l),Q.get(l));
			}
			// dequeue a random number of patients
			for (int i = 0; i < random.nextInt(refQ.size()); i++) {
				assertEquals(refQ.removeFirst(),Q.dequeue());
				assertEquals(refQ.size(),Q.size());
			}
			// check some more patients at random
			for (int i = 0; i < random.nextInt(refQ.size()); i++) {
				int l = random.nextInt(refQ.size());
				assertEquals(refQ.get(l),Q.get(l));
			}
		}

		// check that the rest of the queue is correct
		for (int i = 0; i < refQ.size(); i++) {
			assertEquals(refQ.get(i),Q.get(i));
		}
	}


	/**
	 * test push method, testSimpleQueue must pass first
	 */
	@Test
	public void testPush() {
		// declare and initialize queues
		MyPatientQueue Q = new MyPatientQueue();
		// use LinkedList as reference queue
		LinkedList<Patient> refQ = new LinkedList<Patient>();
		Patient p;
		int k = 0; // "arrival time"
		for (int j = 0; j < 20; j++ ) {
			// generate 0 to 100 patients and enqueue or push them
			for (int i = 0; i < random.nextInt(100); i++) {
				p = new Patient(randomName(),k++,random.nextInt(4)+1);
				if (random.nextBoolean()) { // half the time enqueue
					refQ.addLast(p);
					Q.enqueue(p);
				} else { 					// half the time push
					refQ.addFirst(p);
					Q.push(p);
				}
				// the sizes better be the same at all times
				assertEquals(refQ.size(),Q.size());
			}
			// dequeue a random number of patients
			for (int i = 0; i < random.nextInt(refQ.size()); i++) {
				assertEquals(refQ.removeFirst(),Q.dequeue());
				assertEquals(refQ.size(),Q.size());
			}
		}

		// check that the rest of the patients dequeue correctly
		while (!refQ.isEmpty()) {
			assertEquals(refQ.removeFirst(),Q.dequeue());
			assertEquals(refQ.size(),Q.size());
		}
	}

	/**
	 * test dequeue(i) method, testSimpleQueue must pass first
	 */
	@Test
	public void testDequeueAtI() {
		// declare and initialize queues
		MyPatientQueue Q = new MyPatientQueue();
		// use LinkedList as reference queue
		LinkedList<Patient> refQ = new LinkedList<Patient>();
		Patient p;
		int k = 0; // "arrival time"
		for (int j = 0; j < 20; j++ ) {
			// generate 0 to 100 patients and enqueue them
			for (int i = 0; i < random.nextInt(100); i++) {
				p = new Patient(randomName(),k++,random.nextInt(4)+1);
				refQ.addLast(p);
				Q.enqueue(p);
				// the sizes better be the same at all times
				assertEquals(refQ.size(),Q.size());
			}
			
			// should return null when i is out of bounds
			for (int i = 1; i <= 10; i++) {
				assertNull(Q.dequeue(-i));
				assertNull(Q.dequeue(Q.size()-1+i));
			}

			// remove a random number of patients from random indices
			for (int i = 0; i < random.nextInt(refQ.size()); i++) {
				int l = random.nextInt(refQ.size());
				assertEquals(refQ.remove(l),Q.dequeue(l));
				assertEquals(refQ.size(),Q.size());
			}
		}


		// remove the rest of the patients in random order
		while (!refQ.isEmpty()) {
			int l = random.nextInt(refQ.size());
			assertEquals(refQ.remove(l),Q.dequeue(l));
			assertEquals(refQ.size(),Q.size());
		}

		// should return null when empty
		for (int i = -10; i <= 10; i++)
			assertNull(Q.dequeue(i));
	}
}
