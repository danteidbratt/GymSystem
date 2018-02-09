package gruppuppgiftdatabas;

public class Booking {
	
	private int ID;
	private boolean attendance;
	private GroupSession grpSess;
	
	public Booking() {
		this.grpSess = new GroupSession();
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public boolean isAttendance() {
		return attendance;
	}
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
	public GroupSession getGrpSess() {
		return grpSess;
	}
	public void setGrpSess(GroupSession grpSess) {
		this.grpSess = grpSess;
	}
}
