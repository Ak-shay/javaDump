package DesignPattern;

/**
 * Keeping algo at one place
 */

abstract class AbsVehicle {
	private DriveStrategy driveStrategy;
	private int topSpeed;
	
	AbsVehicle (DriveStrategy driveStrategy, int topSpeed) {
		this.driveStrategy = driveStrategy;
		this.topSpeed = topSpeed;
	}
	
	public void drive() {
		this.driveStrategy.drive();
	}
}

interface DriveStrategy {
	void drive();
}

class OffRoad implements DriveStrategy {
	public void drive() {
		System.out.println("Off road drive strategy");
	}
}

class CityRoad implements DriveStrategy {
	public void drive() {
		System.out.println("City road drive strategy");
	}
}

class Thar extends AbsVehicle {
	Thar () {
		super(new OffRoad(), 200);
	}
}

public class Strategy {
	public static void main(String args[]) {
		AbsVehicle thar1 = new Thar();
		
		thar1.drive();
	}

}
