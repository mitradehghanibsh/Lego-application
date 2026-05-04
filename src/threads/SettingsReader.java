package threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import data.Robot;

public class SettingsReader implements Runnable {

    public void run() {

        while(true) {
            try {

                URL url = new URL("http://10.95.162.86:8080/rest/lego/getvalues");

                HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
                );

                String response = br.readLine();

                String[] parts = response.split("#");

                if(parts.length >= 6) {

                    Robot.setRun(Integer.parseInt(parts[1]));
                    Robot.setSpeed(Integer.parseInt(parts[2]));
                    Robot.setTurn(Integer.parseInt(parts[3]));
                    Robot.setParkingMode(Integer.parseInt(parts[4]));
                    Robot.setObstacleDistance(Float.parseFloat(parts[5]));
                }

                Thread.sleep(3000);

            } catch(Exception e) {
                System.out.println("Settings read error: " + e.getMessage());
            }
        }
    }
}
