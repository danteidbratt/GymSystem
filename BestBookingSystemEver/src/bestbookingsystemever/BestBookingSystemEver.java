
package bestbookingsystemever;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BestBookingSystemEver {
    JFrame frame;
    
    public BestBookingSystemEver() {
        frame = new JFrame("BestGymEver Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);
        start();
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void start() {
            JPanel main = new JPanel();
                main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
                main.setBackground(Color.WHITE);
            JPanel p1 = new JPanel();
                p1.setBackground(Color.GRAY);
                JLabel question = new JLabel("BestGymSystemEver - Admin");
                    question.setHorizontalAlignment(JLabel.CENTER);
            JPanel p2 = new JPanel();
                p2.setBackground(Color.GRAY);
                JLabel top = new JLabel("Choose a admin assignment: ");
                    top.setHorizontalAlignment(JLabel.CENTER);
            JPanel p3 = new JPanel();
                p3.setBackground(Color.GRAY);
                JLabel empty = new JLabel();
                    empty.setHorizontalAlignment(JLabel.CENTER);
        JPanel p4 = new JPanel();
            p4.setBackground(Color.GRAY);
                JButton employee = new JButton("Employee");
                    employee.setHorizontalAlignment(JLabel.CENTER);
        JPanel p5 = new JPanel();
            p5.setBackground(Color.GRAY);
                JButton member = new JButton("Member");
                    member.setHorizontalAlignment(JLabel.CENTER);
        JPanel p6 = new JPanel();
            p6.setBackground(Color.GRAY);
                JButton individualSession = new JButton("IndividualSession");
                    individualSession.setHorizontalAlignment(JLabel.CENTER);
        JPanel p7 = new JPanel();
            p7.setBackground(Color.GRAY);
                JButton groupSession = new JButton("GroupSession");
                    groupSession.setHorizontalAlignment(JLabel.CENTER);
        p1.add(question);
        p2.add(top);
        p4.add(employee);
        p5.add(member);
        p6.add(individualSession);
        p7.add(groupSession);
        main.add(p1);
        main.add(p2);
        main.add(p4);
        main.add(p5);
        main.add(p6);
        main.add(p7);
        frame.add(main);
        
        member.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BBSEmember member = new BBSEmember(frame);
                member.member();
            }
        });
        
        employee.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BBSEemployee employee = new BBSEemployee(frame);
                employee.employee();
            }
        });
          
        individualSession.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BBSEindividualSession individualSession = new BBSEindividualSession(frame);
                individualSession.individualSession();
            }
        });
        
        groupSession.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BBSEgroupSession groupSession = new BBSEgroupSession(frame);
                groupSession.groupSession();
            }
        });

    }   public static void main(String[] args) {
        BestBookingSystemEver  start = new BestBookingSystemEver ();
    }
}
