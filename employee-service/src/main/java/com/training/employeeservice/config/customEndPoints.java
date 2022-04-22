package com.training.employeeservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Controller;

@Controller
@Endpoint(id="details")
public  class customEndPoints {
	@ReadOperation
	public Map<String, String> getDetails()
	{
		HashMap<String, String> m=new HashMap<String, String>();
		m.put("version", "1.0");
		m.put("name", "Employee service");
		m.put("desc", "web application");
		return m;
				
	}

}
