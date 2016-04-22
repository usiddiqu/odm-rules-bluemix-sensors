package com.acme.sensors.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sensor {
		private String name;
		public double getTemp() {
			return temp;
		}

		public void setTemp(double temp) {
			this.temp = temp;
		}

		public double getHumidity() {
			return humidity;
		}

		public void setHumidity(double humidity) {
			this.humidity = humidity;
		}

		public double getObjectTemp() {
			return objectTemp;
		}

		public void setObjectTemp(double objectTemp) {
			this.objectTemp = objectTemp;
		}

		public String getName() {
			return name;
		}
		public double getFeelsLikeTemp() {
			if (feelsLikeTemp == 0.0)
					this.computeFeelsLikeTemperature();
			return feelsLikeTemp;
		}

		public String getWarning() {
			return warning;
		}
		public void setWarning(String warning) {
			this.warning = warning;
		}

		public void setName(String name) {
			this.name = name;
		}

		private double temp = 20.0;
		private double humidity = 50;
		private double objectTemp = 20.0;
		private double feelsLikeTemp=0.0;
		private String warning = "";
		private String type = "indoor";


		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Sensor() {
		};


		public Sensor(String name, String type, double temp, double humidity, double objectTemp) {
			this.name = name;
			this.type = type;
			this.temp = temp;
			this.humidity= humidity;
			this.objectTemp = objectTemp;
		}
		
		// from http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
		public static double round(double value, int places) {
		    if (places < 0) throw new IllegalArgumentException();

		    BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}


		// from http://www.srh.noaa.gov/images/epz/wxcalc/heatIndex.pdf
		public void computeFeelsLikeTemperature() {
			if (temp < 26.7 || humidity < 40){
				feelsLikeTemp = temp;
			} else {
				double tempF = temp * 9/5 + 32;
				double feelsLikeTempF =  - 42.379 + (2.04901523 * tempF) + (10.14333127 * humidity)
						- (0.22475541 * tempF * humidity) - (6.83783 * 0.001 * tempF * tempF)
						- (5.481717 * 0.01 * humidity * humidity) 
						+ (1.22874 * 0.001 * tempF * tempF * humidity)
						+ (8.5282 * 0.0001 * tempF * humidity * humidity ) 
						- (1.99 * 1/1000000 * tempF * tempF * humidity * humidity);
				feelsLikeTemp = (feelsLikeTempF - 32) * 5 / 9;
			}
			feelsLikeTemp = round(feelsLikeTemp, 2);
			System.out.print("The temperature " + temp + "C with humidity " + humidity + "% feels like " + feelsLikeTemp + "C");
		}


		@Override
		public String toString() {
			return "Sensor [name=" + name + ", type=" + type +", temp=" + temp + ", humidity=" + humidity
					+ ", objectTemp=" + objectTemp
					+ ", feelsLikeTemp="
					+ feelsLikeTemp + "]";
		}

		public static void main(String[] args) {
			Sensor sensor = new Sensor();
			sensor.setTemp(35);
			sensor.setHumidity(80);
			computeFeelsLikeTemp(sensor);
		}

		private static void computeFeelsLikeTemp(Sensor sensor) {
			sensor.computeFeelsLikeTemperature();
		}
		

}
