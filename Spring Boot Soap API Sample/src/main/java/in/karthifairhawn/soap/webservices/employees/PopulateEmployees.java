package in.karthifairhawn.soap.webservices.employees;

import org.springframework.stereotype.Component;

import java.util.*;



@Component
public class PopulateEmployees {
		static List<EmployeeDetail> employees = new ArrayList<>();
		
		static {
			EmployeeDetail emp1 = new EmployeeDetail(1, 20, "Karthi", "Kadayanallur");
			EmployeeDetail emp2 = new EmployeeDetail(2, 18, "Raja", "Tirunelveli");
			EmployeeDetail emp3 = new EmployeeDetail(3, 19, "Ajay", "Tnvl");
			EmployeeDetail emp4 = new EmployeeDetail(4, 22, "Rama", "Sivakasi");
			employees.add(emp1);
			employees.add(emp2);
			employees.add(emp3);
			employees.add(emp4);
		}
		public EmployeeDetail findById(int id){
			for(EmployeeDetail e: employees) {
				if(e.getId()==id) return e;
			}
			return null;
		}
		
		public List<EmployeeDetail> findAll() {
			return employees;
		}
		public int deleteById(int id){
			Iterator<EmployeeDetail> iterator = employees.iterator();
			while(iterator.hasNext()) {
				EmployeeDetail e=iterator.next();
				if(e.getId()==id) {
					iterator.remove();
					return 1;
				}
			}
			return 0;
		}
		
}
