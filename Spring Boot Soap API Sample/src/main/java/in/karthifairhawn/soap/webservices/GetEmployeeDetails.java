package in.karthifairhawn.soap.webservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import in.karthifairhawn.emp.DeleteEmployeeDetailsRequest;
import in.karthifairhawn.emp.DeleteEmployeeDetailsResponse;
import in.karthifairhawn.emp.EmpDetails;
import in.karthifairhawn.emp.GetAllEmployeeDetailsRequest;
import in.karthifairhawn.emp.GetAllEmployeeDetailsResponse;
import in.karthifairhawn.emp.GetEmployeeDetailsRequest;
import in.karthifairhawn.emp.GetEmployeeDetailsResponse;
import in.karthifairhawn.soap.webservices.employees.EmployeeDetail;
import in.karthifairhawn.soap.webservices.employees.PopulateEmployees;



@Endpoint
public class GetEmployeeDetails {
	
	@Autowired
	PopulateEmployees service;
	
	@PayloadRoot(namespace="http://karthifairhawn.in/emp",localPart="GetEmployeeDetailsRequest")
	@ResponsePayload
	public GetEmployeeDetailsResponse ProcessEmployeeDetails(@RequestPayload GetEmployeeDetailsRequest  req) {				
		EmployeeDetail emplo = service.findById(req.getId());		
		if(emplo==null)
			throw new EmployeeNotFoundException("Employee Not found in here");
		
		return mapEmp(emplo);		
	}

	public GetEmployeeDetailsResponse mapEmp(EmployeeDetail emplo) {
		GetEmployeeDetailsResponse response = new GetEmployeeDetailsResponse();	
		response.setEmpDetails(mapEmpDetails(emplo));
		return response;
	}

	public EmpDetails mapEmpDetails(EmployeeDetail emplo) {
		EmpDetails empDetails = new EmpDetails();
		empDetails.setAge(emplo.getAge());
		empDetails.setCity(emplo.getCity());
		empDetails.setName(emplo.getName());
		empDetails.setId(emplo.getId());
		return empDetails;
	}
	
	@PayloadRoot(namespace="http://karthifairhawn.in/emp",localPart="GetAllEmployeeDetailsRequest")
	@ResponsePayload
	public GetAllEmployeeDetailsResponse getAllEmployeeDetails(@RequestPayload GetAllEmployeeDetailsRequest req){
		List<EmployeeDetail> employees = service.findAll(); 
		return mapAllEmpDetails(employees);
	}

	public GetAllEmployeeDetailsResponse mapAllEmpDetails(List<EmployeeDetail> employees){
		GetAllEmployeeDetailsResponse response = new GetAllEmployeeDetailsResponse();
		for(EmployeeDetail e:employees){
			EmpDetails ee = mapEmpDetails(e);
			response.getEmpDetails().add(ee);
		}
		return response;		
	}
	
	@PayloadRoot(namespace="http://karthifairhawn.in/emp",localPart="DeleteEmployeeDetailsRequest")
	@ResponsePayload
	public DeleteEmployeeDetailsResponse processDeleteEmplopyeeRequest(@RequestPayload DeleteEmployeeDetailsRequest req){
		DeleteEmployeeDetailsResponse response = new DeleteEmployeeDetailsResponse();
		int result = service.deleteById(req.getId());
		response.setId(result);
		return response;
	}
}	
