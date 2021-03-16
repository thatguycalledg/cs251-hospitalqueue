/**
 * A class representing a patient.
 * 
 * @author gvinayak
 */
public class Patient {

	String name;
	int arrival_time;
	int urgency;
	
	// constructor
	public Patient(String name, int arrival_time, int urgency) {

		if(!name.isEmpty())
			this.name = name;

		this.arrival_time = arrival_time;
		this.urgency = urgency;
	}
	
	// functions
	/**
	 * @return this patient's arrival time
	 */
	public int arrival_time() {

		return this.arrival_time;
	}
	
	/**
	 * @return this patient's urgency
	 */
	public int urgency() {

		return this.urgency;
	}
	
	/**
	 * @param time - current simulation time
	 * @return wait time of this patient
	 */
	public int wait_time(int time){

		return time - this.arrival_time;
	}
}
