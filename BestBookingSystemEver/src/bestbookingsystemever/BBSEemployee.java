package bestbookingsystemever;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BBSEemployee {

    JFrame frame;
    EmployeeRepository erepo = new EmployeeRepository();

    public BBSEemployee(JFrame frame) {
        this.frame = frame;
    }

    public void employee() {
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
        JLabel top = new JLabel("Add an employee: ");
        top.setHorizontalAlignment(JLabel.CENTER);
        JPanel p3 = new JPanel();
        p3.setBackground(Color.GRAY);
        JLabel empty = new JLabel();
        empty.setHorizontalAlignment(JLabel.CENTER);
        JPanel p4 = new JPanel();
        p4.setBackground(Color.GRAY);
        JButton employee = new JButton("Name: ");
        employee.setHorizontalAlignment(JLabel.CENTER);
        JTextField name = new JTextField();
        name.setPreferredSize( new Dimension( 200, 24 ) );
        JPanel p5 = new JPanel();
        p5.setBackground(Color.GRAY);
        JButton save = new JButton("Save");
        save.setHorizontalAlignment(JLabel.CENTER);
        
        p1.add(question);
        p2.add(top);
        p4.add(employee);
        p4.add(name);
        p5.add(save);
        main.add(message);
        main.add(p1);
        main.add(p2);
        main.add(p4);
        main.add(p5);

        frame.add(main);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                message.removeAll();
                String eName = name.getText();
                boolean ok = erepo.addEmployee(eName);
                if(ok == false){
                    message.add(new JLabel("Could not save employee"));
                } else {
                    message.add(new JLabel("Successfully saved a employee"));
                }
                message.repaint();
                message.validate();
            }
        });
        
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        
    }
}
