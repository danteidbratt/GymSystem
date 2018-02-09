package gruppuppgiftdatabas;

public class IndividualSession extends Sessions {
	private int indID;
	private boolean attendance;
	
	public int getindID() {
		return indID;
	}
	public void setindID(int iD) {
		indID = iD;
	}

	public boolean isAttendance() {
		return attendance;
	}
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
}
