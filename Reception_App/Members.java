package gruppuppgiftdatabas;

import java.util.List;

public class Members {
	private String name;
	private int ID;
	private List<IndividualSession> indList;
	private List<Booking> bookList;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<IndividualSession> getIndList() {
		return indList;
	}
	public void setIndList(List<IndividualSession> indList) {
		this.indList = indList;
	}
	public List<Booking> getBookList() {
		return bookList;
	}
	public void setBookList(List<Booking> bookList) {
		this.bookList = bookList;
	}
}
