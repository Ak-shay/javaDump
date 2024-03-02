package LLDQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a parking lot
 * Requirements:
 * 1. Multi level parking
 * 2. Two wheeler and four wheeler space
 * 3. Multiple entry gate
 * 4. get nearest slot and assign a ticket 
 * 5. multiple Exits 
 * 6. Give ticket and pay due
 */


class Vehicle {
	String name;
	String type;
	Vehicle(String name, String type) {
		this.name = name;
		this.type = type;
	}
}

class Slot {
	String id;
	Vehicle vehicle;
	
	void parkVehicle(Vehicle v) {
		this.vehicle = v;
	};
	
	Slot(String id) {
		this.id = id;
		this.vehicle = null;
	}
	
	boolean isEmpty() {
		return this.vehicle == null;
	}
	
	void emptySlot() {
		this.vehicle = null;
	}
}


interface SearchSlotStrategy {
	Slot findSlot();
}

abstract class SlotManager {
	protected List<Slot> slots;
	SearchSlotStrategy searchStrategy;
	
	void parkVehicle(Vehicle v) {
		// get nearest slot
		
	};
}

class TwoWheelerSlotManager extends SlotManager {

	TwoWheelerSlotManager(int numOfSlots) {
		this.slots = new ArrayList<Slot>();
		for (int i = 0; i<numOfSlots; i++) {
			this.slots.add(new Slot("twoWheelerSlot" + i));
		}
	}	
}

class FourWheelerSlotManager extends SlotManager {

	FourWheelerSlotManager(int numOfSlots) {
		this.slots = new ArrayList<Slot>();
		for (int i = 0; i<numOfSlots; i++) {
			this.slots.add(new Slot("fourWheelerSlot" + i));
		}
	}	
}

class Ticket {
	Vehicle v;
	int checkInTime;
	
}

abstract class EntryGateItf {
	String name;
	private TwoWheelerSlotManager twoWheelerManager;
	private FourWheelerSlotManager fourWheelerManager;
	
	EntryGateItf(String name, int numOfTwoWheelerSpots, int numOfFourWheelerSpots) {
		this.name = name;
		this.twoWheelerManager = new TwoWheelerSlotManager(numOfTwoWheelerSpots);
		this.fourWheelerManager = new FourWheelerSlotManager(numOfFourWheelerSpots);
	}
	
	abstract void parkVehicle (SlotManager manager, Vehicle v);
	abstract Ticket getTicket();
}


public class ParkingLot {
	List<EntryGateItf> entryGates;
	
	
	void parkVehicle(Vehicle v) {
		
	}
	
	public static void main(String args[]) {
		ParkingLot lot1 = new ParkingLot("lot1", 10, 10);
		Vehicle v1 = new Vehicle("v1", "TwoWheeler");
		Vehicle v2 = new Vehicle("v2", "FourWheeler");
		System.out.println("hey");
	}

}
