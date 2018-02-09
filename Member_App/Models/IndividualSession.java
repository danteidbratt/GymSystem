package Models;

public class IndividualSession extends Session{
    
    private int individualSessionID;
    private Member member;
    private boolean attendance;
    private Note note;

    public int getIndividualSessionID() {
        return individualSessionID;
    }

    public void setIndividualSessionID(int individualSessionID) {
        this.individualSessionID = individualSessionID;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
    
}