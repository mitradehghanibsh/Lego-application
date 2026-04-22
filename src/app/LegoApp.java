package app;

import threads.*;

public class LegoApp {

	public static void main(String[] args) {
		RunLego runLego=new RunLego();
		ReadData readData=new ReadData();

		System.out.println("Run in Threads");

		Thread runLegoThread=new Thread(runLego);
		Thread readDataThread=new Thread(readData);
		runLegoThread.start(); //Saikeen kaynnistys
		readDataThread.start(); //Virtuaalikone aloittaa saikeen kun ehtii
	}
}
