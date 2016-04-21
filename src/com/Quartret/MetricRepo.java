package com.Quartret;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class MetricRepo {
	Connection connection = DBConnection.getConnection();

	public String getvalue(String dateTime) {
		String name = "";
		int value = 0;
		try {
			Timestamp ts = Timestamp.valueOf(dateTime);
			String selectSQL = "SELECT name,value FROM tblMetrics where time ='"
					+ ts + "'";
			PreparedStatement ps = connection.prepareStatement(selectSQL);
			ResultSet rs = ps.executeQuery(selectSQL);
			while (rs.next()) {
				name = rs.getString("name");
				value = rs.getInt("value");
			}
		} catch (ParseException e) {
			System.out.println("err:" + e);
		} catch (Exception e) {
			System.out.println("err:" + e);
		}
		return name + "  " + value;
	}

	public String getAllValues(String start, String end) {
		String displayValue = "";
		int aggregate = 0;
		int count = 0;
		try {
			Timestamp startTs = Timestamp.valueOf(start);
			Timestamp endTs = Timestamp.valueOf(end);
			String selectSQL = "SELECT name,value FROM tblMetrics where time between '"
					+ startTs + "'" + " and '" + endTs + "'";
			PreparedStatement ps = connection.prepareStatement(selectSQL);
			ResultSet rs = ps.executeQuery(selectSQL);
			while (rs.next()) {
				count++;
				String name = rs.getString("name");
				int value = rs.getInt("value");
				displayValue = displayValue + name + " " + value + "\n";
				aggregate = aggregate + value;
			}
			if (count > 1) {
				aggregate = aggregate / count;
				displayValue = displayValue + "aggregate : " + aggregate;
			}
		} catch (ParseException e) {
			System.out.println("err:" + e);
		} catch (Exception e) {
			System.out.println("err:" + e);
		}
		return displayValue;
	}

	public void setValue(String name, int value) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String now = dateFormat.format(new Date());
			Timestamp ts = Timestamp.valueOf(now);
			String selectSQL = "INSERT into tblMetrics(time,name,value) values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(selectSQL);
			ps.setTimestamp(1, ts);
			ps.setString(2, name);
			ps.setInt(3, value);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("err:" + e);
		}
	}

}