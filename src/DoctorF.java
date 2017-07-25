import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.CardLayout;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorF extends JPanel {

	/**
	 * Create the panel.
	 */
	public DoctorF(Statement state) {
		
		JPanel card1 = new JPanel();
		add(card1,"Card1");
		//add(card2,"Card2");
		
		JButton btnNewButton = new JButton("Team 4");
		JButton button = new JButton("Team 3");
		JButton button_2 = new JButton("Team 5");
		JButton btnTeam = new JButton("Team 1");
		Statement stmt = state;
		
		setLayout(new CardLayout(0, 0));
		CardLayout cardLayout = (CardLayout) getLayout();
	
		card1.setLayout(null);
		JPanel p = this;
		JButton btnNewButton_1 = new JButton("Team 2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel card2 = new EditInfoDoc(stmt,2);
				
				add(card2,"Card2");
				 
				 cardLayout.next(p);
				 btnNewButton_1.setVisible(false);
				 btnNewButton.setVisible(false);
				 button.setVisible(false);
				 button_2.setVisible(false);
				 btnTeam.setVisible(false);
				 repaint();
			}
		});
		btnNewButton_1.setBounds(156, 96, 117, 29);
		card1.add(btnNewButton_1);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel card2 = new EditInfoDoc(stmt,4);
				
				 add(card2,"Card2");
				 
				 cardLayout.next(p);
				 
				 
				 cardLayout.show(p, "Card2");
				 btnNewButton_1.setVisible(false);
				 btnNewButton.setVisible(false);
				 button.setVisible(false);
				 button_2.setVisible(false);
				 btnTeam.setVisible(false);
				}
			
			
			
		});
		btnNewButton.setBounds(156, 161, 117, 29);
		card1.add(btnNewButton);
		
		JTextPane txtpnDoctorForm = new JTextPane();
		txtpnDoctorForm.setBounds(181, 6, 79, 16);
		txtpnDoctorForm.setText("Doctor Form");
		card1.add(txtpnDoctorForm);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JPanel card2 = new EditInfoDoc(stmt,3);
				
				 add(card2,"Card2");
				 
				 cardLayout.next(p);
				 
				 cardLayout.show(p, "Card2");
				 btnNewButton_1.setVisible(false);
				 btnNewButton.setVisible(false);
				 button.setVisible(false);
				 button_2.setVisible(false);
				 btnTeam.setVisible(false);
			}
		});
		button.setBounds(156, 127, 117, 29);
		card1.add(button);
		
		
		btnTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JPanel card2 = new EditInfoDoc(stmt,1);
				
				 add(card2,"Card2");
				 
				 cardLayout.next(p);
				 btnTeam.setVisible(false);
				 
				 cardLayout.show(p, "Card2");
				 btnNewButton_1.setVisible(false);
				 btnNewButton.setVisible(false);
				 button.setVisible(false);
				 button_2.setVisible(false);
				 btnTeam.setVisible(false);
			}
		});
		btnTeam.setBounds(156, 68, 117, 29);
		card1.add(btnTeam);
		
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				JPanel card2 =new EditInfoDoc(stmt,5);
				
				 add(card2,"Card2");
				 
				 cardLayout.next(p);
				 btnNewButton_1.setVisible(false);
				 btnNewButton.setVisible(false);
				 button.setVisible(false);
				 button_2.setVisible(false);
				 btnTeam.setVisible(false);
				
			}
		});
		button_2.setBounds(156, 191, 117, 29);
		card1.add(button_2);
		
		
		
		

	}
}
