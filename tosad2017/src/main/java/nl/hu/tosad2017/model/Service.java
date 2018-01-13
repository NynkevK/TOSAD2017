package nl.hu.tosad2017.model;

import java.util.ArrayList;
import nl.hu.tosad2017.model.*;
import nl.hu.tosad2017.persistence.*;

//import java.sql.*;

public class Service {
	RowDAO rowDAO = new RowDAO();
	
	public Service() {}

	public ArrayList<String> getRangeRules() {		
		return rowDAO.getRangeRules();
	}
	
	public Row getRowById(String Id) {
		return rowDAO.findById(Id);
	}
	
	public Row addRow (Row r) {
		return rowDAO.save(r);
	}
}
