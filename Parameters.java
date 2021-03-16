/**
 * Simulation parameters.<br>
 * Everything is public and static.<br>
 * reset_parameter() and set_parameters(...) functions are provided.<br>
 * To get or set an individual parameter's value, access it in a static way directly.
 * 
 *
 */
public class Parameters {	
	/**
	 * min treatment time for each urgency level
	 */
	public static int[] min_treatment_time = new int[]{5,10,30,60};
	
	/**
	 * max treatment time for each urgency level
	 */
	public static int[] max_treatment_time = new int[]{8,15,40,75};
	
	/**
	 * number of doctors
	 */
	public static int num_doctors = 4;
	
	/**
	 * probability of an emergency
	 */
	public static double p_emergency = 0;
	
	/**
	 * wait time to promote in queues 1 and 2
	 */
	public static int l12_escalation_delay = Integer.MAX_VALUE;
	
	/**
	 * probability of a walkout
	 */
	public static double p_walkout = 0;
	
	/**
	 * wait time to walkout
	 */
	public static int l1_walkout_delay = Integer.MAX_VALUE;
	
	/**
	 * random number generator seed
	 */
	public static long seed = 8675309;
	
	/**
	 * reset parameters to default values:<br>
	 * min_treatment_time = {5,10,30,60}<br>
	 * max_treatment_time = {8,15,40,75}<br>
	 * num_doctors = 4<br>
	 * p_emergency = 0<br>
	 * l12_escalation_delay = 2147483647<br>
	 * p_walkout = 0<br>
	 * l1_walkout_delay = 2147483647<br>
	 * seed = 8675309
	 */
	public static void reset_parameters() {
		min_treatment_time = new int[]{5,10,30,60};
		max_treatment_time = new int[]{8,15,40,75};
		num_doctors = 4;
		p_emergency = 0;
		l12_escalation_delay = Integer.MAX_VALUE;
		p_walkout = 0;
		l1_walkout_delay = Integer.MAX_VALUE;
		seed = 8675309;
	}
	
	/**
	 * set parameters to specified values
	 * @param min_treatment_time - min treatment time for each urgency level
	 * @param max_treatment_time - max treatment time for each urgency level
	 * @param num_doctors - number of doctors to use
	 * @param p_emergency - probability of an emergency
	 * @param l12_escalaction_delay - wait time to promote in queues 1 and 2
	 * @param p_walkout - probability of a walkout
	 * @param l1_walkout_delay - wait time to walkout
	 * @param seed - random number generator seed
	 */
	public void set_parameters(
			int[] min_treatment_time,
			int[] max_treatment_time,
			int num_doctors,
			double p_emergency,
			int l12_escalation_delay,
			double p_walkout,
			int l1_walkout_delay,
			long seed
			) {
		reset_parameters();
		
		if (min_treatment_time.length != 4)
			System.out.println("Warning: using default min_treatment_time (specified array's length != 4)");
		else
			System.arraycopy(min_treatment_time,0,min_treatment_time,0,4);
		
		if (max_treatment_time.length != 4)
			System.out.println("Warning: using default max_treatment_time (specified array's length != 4)");
		else
			System.arraycopy(min_treatment_time,0,min_treatment_time,0,4);
		
		if (num_doctors <= 0)
			System.out.println("Warning: using default num_doctors (specified number <= 0)");
		else
			Parameters.num_doctors = num_doctors;
		
		if (p_emergency < 0 || p_emergency > 1)
			System.out.println("Warning: using default p_emergency (specified  0 > p > 1)");
		else
			Parameters.p_emergency = p_emergency;
		
		if (l12_escalation_delay < 0)
			System.out.println("Warning: using default l12_escalation_delay (specified delay < 0)");
		else
			Parameters.l12_escalation_delay = l12_escalation_delay;
		
		if (p_walkout < 0 || p_walkout > 1)
			System.out.println("Warning: using default p_walkout (specified  0 > p > 1)");
		else
			Parameters.p_walkout = p_walkout;
		
		if (l1_walkout_delay < 0)
			System.out.println("Warning: using default l1_walkout_delay (specified delay < 0)");
		else
			Parameters.l1_walkout_delay = l1_walkout_delay;
		
		Parameters.seed = seed;
	}
}
