package threads;


import data.*;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class RunLego implements Runnable{
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (Robot.getRun()==1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			motorA.setPower(Robot.turnRight());
			motorB.setPower(Robot.turnLeft());
		}
	}

}
