import java.util.Arrays;

/**
 * A Patient queue implementation using a dynamically-sized circular array.
 *
 * @author gvinayak
 */
public class MyPatientQueue{

	Patient[] queue;
	int head;
	int tail;
	int numberInQueue;

	// constructor
	public MyPatientQueue() {

		this.queue = new Patient[7];
		this.head = 0;
		this.tail = 0;
		this.numberInQueue = 0;
	}

	// functions
	/**
	 * @return the number of patients in the queue
	 */
	public int size() {

		return this.numberInQueue;
	}

	/**
	 * add patient to end of queue.
	 * @param p - Patient to add to queue
	 */
	public void enqueue(Patient p) {

		this.queue[tail] = p;
		this.numberInQueue++;
		this.tail++;

		//rotate tail to zero, once end of array is reached
		if(this.tail == this.queue.length)
			this.tail = 0;

		//if the tail is equal to head, but there are more than 1 elements, then double array size.
		if(tail == head && numberInQueue != 0)
			upSize();
	}

	/**
	 * remove and return next patient from the queue
	 * @return patient at front of queue, null if queue is empty
	 */
	public Patient dequeue() {

		//Can't dequeue if null
		if(this.numberInQueue == 0) return null;

		Patient nextUp = this.queue[head];
		this.queue[head] = null;
		this.head++;
		this.numberInQueue--;

		//rotate head to zero, once end of array is reached
		if(this.head == this.queue.length)
			this.head = 0;

		//If array size is 4 times the number of elements after dequeue, then make the array size half.
		if(numberInQueue <= (this.queue.length / 4))
			downSize();

		//return the de-queued person
		return nextUp;
	}

	/**
	 * return, but do not remove, the patient at index i
	 * @param i - index of patient to return
	 * @return patient at index i, or null if no such element
	 */
	public Patient get(int i) {

		int count = head;

		for (int j = 0; j < i; j++) {

			if(count == this.queue.length - 1)
				count = 0;

			else
				count++;
		}

		if(this.queue[count] == null)
			return null;

		return this.queue[count];
	}

	/**
	 * add patient to front of queue
	 * @param p - patient being added to queue
	 */
	public void push(Patient p) {

		if(numberInQueue != queue.length) {

			if(head == 0)
				head = queue.length - 1;

			else
				head--;

			queue[head] = p;
			numberInQueue++;
		}

		if(numberInQueue == queue.length) {
			upSize();
		}
	}

	/**
	 * remove and return patient at index i from queue
	 * @param i - index of patient to remove
	 * @return patient at index i, null if no such element
	 */
	public Patient dequeue(int i) {

		if(i < 0 || i > numberInQueue - 1)
			return null;

		Patient[] reQueue = new Patient[this.queue.length];
		int reIndex = 0;

		Patient p = get(i);

		if(p == null)
			return null;

		for(int j = 0; j < numberInQueue; j++) {

			if(!p.equals(this.queue[head])) {

				reQueue[reIndex] = this.queue[head];
				reIndex++;
			}

			if(head == this.queue.length - 1)
				head = 0;

			else
				head++;
		}

		numberInQueue--;
		this.head = 0;
		this.tail = numberInQueue;
		this.queue = reQueue;

		//If array size is 4 times the number of elements after dequeue, then make the array size half.
		if(numberInQueue <= (this.queue.length / 4))
			downSize();

		return p;
	}

	public void upSize() {

		Patient[] reQueue = new Patient[this.queue.length * 2];

		for(int i = 0; i < this.queue.length; i++) {

			if(head == this.queue.length)
				this.head = 0;

			reQueue[i] = this.queue[this.head];
			this.head++;
		}

		this.head = 0;
		this.tail = this.numberInQueue;
		this.queue = reQueue;
	}

	public void downSize() {

		Patient[] reQueue;

		if ((this.queue.length / 2) <= 7) {
			reQueue = new Patient[7];
		}

		else {
			reQueue = new Patient[this.queue.length / 2];
		}

		for(int i = 0; i < this.numberInQueue; i++) {

			if(head == this.queue.length)
				head = 0;

			reQueue[i] = this.queue[head];
			head++;
		}

		head = 0;
		tail = this.numberInQueue;
		this.queue = reQueue;
	}
}