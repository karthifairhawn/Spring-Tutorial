package in.karthifairhawn.soap.webservices.employees;

public class EmployeeDetail {	
	public EmployeeDetail(int id, int age, String name, String city) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	int id;
	int age;
	String name;
	@Override
	public String toString() {
		return "EmployeeDetail [id=" + id + ", age=" + age + ", name=" + name + ", city=" + city + "]";
	}
	String city;
	
}
