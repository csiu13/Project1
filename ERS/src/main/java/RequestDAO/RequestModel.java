package RequestDAO;

public class RequestModel {
	
	private int r_id;
	private int m_id;
	private String manager;
	private String employee;
	private int e_id;
	private String requested;
	private String completed;
	private String reason;
	private double amount;
	private int status;
	/**
	 * 
	 */
	public RequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param r_id
	 * @param m_id
	 * @param manager
	 * @param employee
	 * @param e_id
	 * @param requested
	 * @param completed
	 * @param reason
	 * @param amount
	 * @param status
	 */
	public RequestModel(int r_id, int m_id, String manager, String employee, int e_id, String requested,
			String completed, String reason, double amount, int status) {
		super();
		this.r_id = r_id;
		this.m_id = m_id;
		this.manager = manager;
		this.employee = employee;
		this.e_id = e_id;
		this.requested = requested;
		this.completed = completed;
		this.reason = reason;
		this.amount = amount;
		this.status = status;
	}

	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public String getRequested() {
		return requested;
	}
	public void setRequested(String requested) {
		this.requested = requested;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getReason() {
		return this.reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((completed == null) ? 0 : completed.hashCode());
		result = prime * result + e_id;
		result = prime * result + m_id;
		result = prime * result + r_id;
		result = prime * result + ((requested == null) ? 0 : requested.hashCode());
		result = prime * result + status;
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
		RequestModel other = (RequestModel) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (completed == null) {
			if (other.completed != null)
				return false;
		} else if (!completed.equals(other.completed))
			return false;
		if (e_id != other.e_id)
			return false;
		if (m_id != other.m_id)
			return false;
		if (r_id != other.r_id)
			return false;
		if (requested == null) {
			if (other.requested != null)
				return false;
		} else if (!requested.equals(other.requested))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RequestModel [r_id=" + r_id + ", m_id=" + m_id + ", e_id=" + e_id + ", requested=" + requested
				+ ", completed=" + completed + ", amount=" + amount + ", status=" + status + "]";
	}
}
