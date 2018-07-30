package RequestDAO;

import java.util.Date;

public class RequestModel {
	
	private int r_id;
	private int m_id;
	private int e_id;
	private Date requested;
	private Date completed;
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
	 * @param e_id
	 * @param requested
	 * @param completed
	 * @param amount
	 * @param status
	 */
	public RequestModel(int r_id, int m_id, int e_id, Date requested, Date completed, double amount, int status) {
		super();
		this.r_id = r_id;
		this.m_id = m_id;
		this.e_id = e_id;
		this.requested = requested;
		this.completed = completed;
		this.amount = amount;
		this.status = status;
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
	public Date getRequested() {
		return requested;
	}
	public void setRequested(Date requested) {
		this.requested = requested;
	}
	public Date getCompleted() {
		return completed;
	}
	public void setCompleted(Date completed) {
		this.completed = completed;
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
