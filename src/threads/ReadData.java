package threads;


import java.io.BufferedReader;



import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import data.*;

public class ReadData implements Runnable{
    URL url = null;
	HttpURLConnection conn = null;
	InputStreamReader isr = null;
	BufferedReader br=null;

	String s=null;

	@Override
	public void run() {
		while (Robot.getRun()==1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				url = new URL("http://10.95.162.86:8080/rest/lego/getvalues");
				conn = (HttpURLConnection)url.openConnection();
				InputStream is=null;
				try {
					is=conn.getInputStream();
				}
				catch (Exception e) {
		  			System.out.println("Exception conn.getInputSteam()");
		  			e.printStackTrace();
		            System.out.println("Cannot get InputStream!");
				}
				isr = new InputStreamReader(is);
	      		br=new BufferedReader(isr);
				while ((s=br.readLine())!=null){
					String [] values=s.split("#");
					Robot.setId(values[0]);
					Robot.setRun(values[1]);
					Robot.setSpeed(values[2]);
					Robot.setTurn(values[3]);

					if (values.length > 4) {
   						 Robot.setParkingMode(values[4]);
					}

					if (values.length > 5) {
						Robot.setObstacleDistance(values[5]);
					}
					
				}
				br.close();
				isr.close();
				is.close();
				conn.disconnect();
			}
	  		catch(Exception e) {
	  			e.printStackTrace();
	            System.out.println("Some problem!");
	  		}
		}
	}

}
