import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class TestPatient {
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
	 * test arrival_time method
	 */
	@Test
	public void testArrivalTime() {
		Patient p;
		for (int i = 0 ; i < 100; i++) {
			int at = random.nextInt(Integer.MAX_VALUE);
			int u = random.nextInt(4)+1;
			p = new Patient(randomName(),at,u);
			assertEquals("arrival time is incorrect.",at,p.arrival_time());
		}
	}
	
	/**
	 * test urgency method
	 */
	@Test
	public void testUrgency() {
		Patient p;
		for (int i = 0 ; i < 100; i++) {
			int at = random.nextInt(Integer.MAX_VALUE);
			int u = random.nextInt(4)+1;
			p = new Patient(randomName(),at,u);
			assertEquals("urgency is incorrect.",u,p.urgency());
		}
	}
	
	/**
	 * test wait_time method
	 */
	@Test
	public void testWaitTime() {
		Patient p;
		for (int i = 0 ; i < 20; i++) {
			int at = random.nextInt(Integer.MAX_VALUE);
			int u = random.nextInt(4)+1;
			p = new Patient(randomName(),at,u);
			for (int j = 0; j < 5; j++) {
				int t = random.nextInt(Integer.MAX_VALUE - at) + at;
				int wt = t-at;
				assertEquals("wait time is incorrect.",wt,p.wait_time(t));
			}
		}
	}
}
