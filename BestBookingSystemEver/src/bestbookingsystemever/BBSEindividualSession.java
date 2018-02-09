package bestbookingsystemever;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BBSEindividualSession {

    JFrame frame;
    SessionRepository srepo = new SessionRepository();
    EmployeeRepository emrepo = new EmployeeRepository();
    DurationRepository drepo = new DurationRepository();
    HallRepository hrepo = new HallRepository();


    public BBSEindividualSession(JFrame frame) {
        this.frame = frame;
    }

    public void individualSession() {
        frame.getContentPane().removeAll();
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.WHITE);
        JPanel message = new JPanel();
        message.setBackground(Color.WHITE);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        JLabel question = new JLabel("BestGymSystemEver - Admin");
        question.setHorizontalAlignment(JLabel.CENTER);
        JPanel p3 = new JPanel();
        p3.setBackground(Color.GRAY);
        JLabel empty = new JLabel();
        empty.setHorizontalAlignment(JLabel.CENTER);
        JPanel p4 = new JPanel();
        p4.setBackground(Color.GRAY);
        JLabel individualSession = new JLabel("Name of trainer: ");
        individualSession.setHorizontalAlignment(JLabel.CENTER);
        JComboBox name = new JComboBox();
        name.setPreferredSize( new Dimension( 200, 24 ) );
        try {
            emrepo.getAllEmployees().stream().forEach(e -> name.addItem(e));
        } catch (SQLException ex) {
            System.out.println(ex); // TODO error handling
        }
        JPanel p5 = new JPanel();
        p5.setBackground(Color.GRAY);
        JLabel hall = new JLabel("Hall: ");
        hall.setHorizontalAlignment(JLabel.CENTER);
        JComboBox hallName = new JComboBox();
        try {
            hrepo.getAll().stream().forEach(h -> hallName.addItem(h));
        } catch (SQLException ex) {
            System.out.println(ex); // TODO error handling
        }
        hallName.setPreferredSize( new Dimension( 200, 24 ) );
        JPanel p6 = new JPanel();
        p6.setBackground(Color.GRAY);
        JLabel individualSessionTime = new JLabel("Time and duration (minutes): ");
        individualSessionTime.setHorizontalAlignment(JLabel.CENTER);
        JTextField startDateTime = new JTextField();
        startDateTime.setPreferredSize( new Dimension( 200, 24 ) );
        JComboBox duration = new JComboBox();
        duration.setPreferredSize( new Dimension( 200, 24 ) );
        try {
            drepo.getAll().stream().forEach(d -> duration.addItem(d));
        } catch (SQLException ex) {
            System.out.println(ex); // TODO error handling
        }
        JPanel p7 = new JPanel();
        p7.setBackground(Color.GRAY);
        JButton save = new JButton("Save");
        save.setHorizontalAlignment(JLabel.CENTER);
        
        p1.add(question);
        p4.add(individualSession);
        p4.add(name);
        p5.add(hall);
        p5.add(hallName);
        p6.add(individualSessionTime);
        p6.add(startDateTime);
        p6.add(duration);
        p7.add(save);
        main.add(message);
        main.add(p1);
        main.add(p4);
        main.add(p5);
        main.add(p6);
        main.add(p7);
        
        frame.add(main);
            
            save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                message.removeAll();
                try {
                    int trainerID = ((EmployeeRepository.Employee)name.getSelectedItem()).getId();
                    int durationID = ((DurationRepository.Duration)duration.getSelectedItem()).getId();
                    int hallID = ((HallRepository.Hall)hallName.getSelectedItem()).getId();
                    int result = srepo.addIndividualSession(startDateTime.getText(), hallID, durationID, trainerID);
                    switch (result) {
                    case 1:
                        message.add(new JLabel("The hall is not available"));
                        break;
                    case 2:
                        message.add(new JLabel("The trainer is busy"));
                        break;
                    case 3:
                        message.add(new JLabel("Successfully added individual session"));
                        break;
                    default:
                        message.add(new JLabel("Something unknown happen, contact support"));
                        break;
                    }
                    message.repaint();
                    message.validate();
                } catch (SQLException ex) {
                    message.add(new JLabel("Something unknown happen, contact support"));
                    message.repaint();
                    message.validate();
                } 
            }

        });

        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }
}
