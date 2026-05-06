package threads;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import data.Robot;

public class SendData {

    public static void sendIncident(String message) {
        try {
            URL url = new URL("http://192.168.0.100:8080/rest/lego/setvalues");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String jsonInputString = "{"
                + "\"speed\": " + Robot.getSpeed() + ","
                + "\"turn\": " + Robot.getTurn() + ","
                + "\"run\": " + Robot.getRun() + ","
                + "\"parkingMode\": " + Robot.getParkingMode() + ","
                + "\"obstacleDistance\": " + Robot.getObstacleDistance() + ","
                + "\"info\": \"" + message + "\""
                + "}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Status report sent successfully.");
            }

            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Communication error: " + e.getMessage());
        }
    }
}