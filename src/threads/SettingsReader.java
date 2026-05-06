package threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import data.Robot;

public class SettingsReader implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                URL url = new URL("http://192.168.0.100:8080/rest/lego/getvalues");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(2000);

                if (conn.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String response = br.readLine();

                    if (response != null && response.contains("#")) {
                        String[] parts = response.split("#");

                        if (parts.length >= 6) {
                            Robot.setRun(Integer.parseInt(parts[1]));
                            Robot.setSpeed(Integer.parseInt(parts[2]));
                            Robot.setTurn(Integer.parseInt(parts[3]));
                            Robot.setParkingMode(Integer.parseInt(parts[4]));
                            Robot.setObstacleDistance(Float.parseFloat(parts[5]));
                        }
                    }
                }
                conn.disconnect();

            } catch (Exception e) {
                // Connection failed or server offline
            }

            try {
                Thread.sleep(3000); 
            } catch (InterruptedException ie) {
                // Thread interrupted
            }
        }
    }
}