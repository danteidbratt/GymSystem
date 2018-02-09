package gruppuppgiftdatabas;

import java.time.LocalDateTime;

public class Sessions {
	private int ID;
	private LocalDateTime dateTime;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
