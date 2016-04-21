package com.Quartret;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
	

public class MetricHandler {
	MetricRepo metricRepo = new MetricRepo();
	
	public String setMetrics(String name, int value) {
		String status = "";
		try {
		    metricRepo.setValue(name, value);
			status = "Values updated to DB. ";
		} catch (Exception e) {
			status = "Error : " + e.getMessage().toString();
		}
		return status;
	}

	public String getMetric(String dateTime) {
		String value = "";
		try{
			value = metricRepo.getvalue(dateTime);
		}catch(ParseException e){
			System.out.println("err:"+e);			
		}catch(Exception e){
			System.out.println("err:"+e);
		}
		return value;
	}
	
	public String getAggregateMetric(String startTime, String endTime) {
		String value = "";
		try{
			value = metricRepo.getAllValues(startTime, endTime);
		}catch(ParseException e){
			System.out.println("err:"+e);
		}catch(Exception e){
			System.out.println("err:"+e);
		}
		return value;
		
	}

}
