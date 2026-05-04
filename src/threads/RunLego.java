package threads;

import data.*;
import threads.SettingsReader;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class RunLego implements Runnable {

    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

    EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S2);
    SampleProvider distanceProvider = ultrasonicSensor.getDistanceMode();
    float[] sample = new float[distanceProvider.sampleSize()];

    private boolean parked = false;

    @Override
    public void run() {
        new Thread(new SettingsReader()).start();
        
        while (Robot.getRun() == 1) {
            sleep(10);

            float distance = getDistance();

            if (Robot.getParkingMode() == 1 && !parked && distance < Robot.getObstacleDistance()) {
                parkBackward();
                parked = true;
                
                // Reporting the successful parking incident to the web service
                SendData.sendIncident("Robot Parked Successfully");

                Robot.setSpeed(0);
                Robot.setRun(0); 
                continue;
            }

            motorA.setPower(Robot.turnRight());
            motorB.setPower(Robot.turnLeft());

            if (Robot.getSpeed() > 0) {
                motorA.forward();
                motorB.forward();
            } else if (Robot.getSpeed() < 0) {
                motorA.backward();
                motorB.backward();
            } else {
                motorA.stop();
                motorB.stop();
            }
        }

        motorA.stop();
        motorB.stop();
        ultrasonicSensor.close();
    }

    private float getDistance() {
        distanceProvider.fetchSample(sample, 0);
        return sample[0];
    }

    private void parkBackward() {
        motorA.stop();
        motorB.stop();
        sleep(300);

        motorA.setPower(35);
        motorB.setPower(35);

        motorA.forward();
        motorB.backward();
        sleep(1800);

        motorA.stop();
        motorB.stop();
        sleep(300);

        motorA.backward();
        motorB.backward();
        sleep(1900);

        motorA.stop();
        motorB.stop();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
