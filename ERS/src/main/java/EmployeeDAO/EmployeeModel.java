package EmployeeDAO;

public class EmployeeModel {
	
	private int e_id;
	private String name;
	private String username;
	private String password;
	private int age;
	private int img_id;
	private int access;
	/**
	 * 
	 */
	public EmployeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param e_id
	 * @param name
	 * @param username
	 * @param password
	 * @param age
	 * @param img_id
	 * @param access
	 */
	public EmployeeModel(int e_id, String name, String username, String password, int age, int img_id, int access) {
		super();
		this.e_id = e_id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.age = age;
		this.img_id = img_id;
		this.access = access;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public int getAccess() {
		return access;
	}
	public void setAccess(int access) {
		this.access = access;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + access;
		result = prime * result + age;
		result = prime * result + e_id;
		result = prime * result + img_id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeModel other = (EmployeeModel) obj;
		if (access != other.access)
			return false;
		if (age != other.age)
			return false;
		if (e_id != other.e_id)
			return false;
		if (img_id != other.img_id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeModel [e_id=" + e_id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", age=" + age + ", img_id=" + img_id + ", access=" + access + "]";
	}

}
