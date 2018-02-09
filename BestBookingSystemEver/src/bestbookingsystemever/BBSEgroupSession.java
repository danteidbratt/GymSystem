
package bestbookingsystemever;

import bestbookingsystemever.DurationRepository.Duration;
import bestbookingsystemever.EmployeeRepository.Employee;
import bestbookingsystemever.ExerciseTypeRepository.ExerciseType;
import bestbookingsystemever.HallRepository.Hall;
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

public class BBSEgroupSession {
    
    JFrame frame;
    EmployeeRepository emrepo = new EmployeeRepository();
    HallRepository hrepo = new HallRepository();
    ExerciseTypeRepository etrepo = new ExerciseTypeRepository();
    DurationRepository drepo = new DurationRepository();
    SessionRepository srepo = new SessionRepository();
    
    public BBSEgroupSession(JFrame frame) {
        this.frame = frame;
    }

    public void groupSession() {
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
        JPanel p2 = new JPanel();
        p2.setBackground(Color.GRAY);
        JLabel top = new JLabel("Add a groupsession type");
        top.setHorizontalAlignment(JLabel.CENTER);
        JComboBox gt = new JComboBox();
        try {
            etrepo.getAll().stream().forEach(et -> gt.addItem(et));
        } catch (SQLException ex) {
            System.out.println(ex); // TODO error handling
        }
        gt.setPreferredSize( new Dimension( 200, 24 ) );
        JPanel p4 = new JPanel();
        p4.setBackground(Color.GRAY);
        JLabel groupSession = new JLabel("Name of trainer: ");
        groupSession.setHorizontalAlignment(JLabel.CENTER);
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
        JLabel groupSessionTime = new JLabel("Time and duration (minutes): ");
        groupSessionTime.setHorizontalAlignment(JLabel.CENTER);
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
        JLabel capacity = new JLabel("Capacity: ");
        capacity.setHorizontalAlignment(JLabel.CENTER);
        JTextField capacitySize = new JTextField();
        capacitySize.setPreferredSize( new Dimension( 200, 24 ) );
        JPanel p8 = new JPanel();
        p8.setBackground(Color.GRAY);
        JButton save = new JButton("Save");
        save.setHorizontalAlignment(JLabel.CENTER);
        
        p1.add(question);
        p2.add(top);
        p2.add(gt);
        p4.add(groupSession);
        p4.add(name);
        p5.add(hall);
        p5.add(hallName);
        p6.add(groupSessionTime);
        p6.add(startDateTime);
        p6.add(duration);
        p7.add(capacity);
        p7.add(capacitySize);
        p8.add(save);
        main.add(message);
        main.add(p1);
        main.add(p2);
        main.add(p4);
        main.add(p5);
        main.add(p6);
        main.add(p7);
        main.add(p8);

        frame.add(main);
        
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                message.removeAll();
                try {
                    int exerciseID = ((ExerciseType)gt.getSelectedItem()).getId();
                    int hallID = ((Hall)hallName.getSelectedItem()).getId();
                    int trainerID = ((Employee)name.getSelectedItem()).getId();
                    int durationID = ((Duration)duration.getSelectedItem()).getId();
                    int cap = Integer.parseInt(capacitySize.getText());
                    int result = srepo.addGroupSession(startDateTime.getText(), exerciseID, cap, hallID, durationID, trainerID);
                    
                    switch (result) {
                    case 1:
                        message.add(new JLabel("The hall is already taken"));
                        break;
                    case 2:
                        message.add(new JLabel("The trainer is busy"));
                        break;
                    case 3:
                        message.add(new JLabel("Successfully added session"));
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
