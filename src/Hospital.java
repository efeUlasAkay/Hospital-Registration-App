import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class Hospital extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static Statement stmt;
	private static Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//-----------------------------------
		
		

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/sample_db");
			stmt = connection.createStatement();
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Hospital frame = new Hospital();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			/*
			String sql = "Select * FROM Doctor";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
		         String id  = rs.getString("id");
		         
		         String name = rs.getString("name");
		         String teamID = rs.getString("teamId");

		         System.out.print("ID: " + id);
		         System.out.print(", Name: " + name);
		         System.out.println(", TeamID: " + teamID);
		      }
			rs.close();
			stmt.close(); */
		

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		
		
		
		
		
		
		//-------------------------------------
		
	}

	/**
	 * Create the frame.
	 */
	public Hospital() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		
        JPanel  panel = new JPanel();
		tabbedPane.addTab("Triage Nurse Form", null, panel, null);
		panel.setForeground(new Color(0, 0, 0));
		panel.setLayout(null);
		
		JTextPane txtpnTriageNurseForm = new JTextPane();
		txtpnTriageNurseForm.setBounds(215, 6, 137, 21);
		txtpnTriageNurseForm.setBackground(Color.WHITE);
		txtpnTriageNurseForm.setFont(new Font("Lucida Blackletter", Font.PLAIN, 16));
		txtpnTriageNurseForm.setText("Triage Nurse Form");
		panel.add(txtpnTriageNurseForm);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setText("Name:");
		txtpnName.setBounds(31, 49, 40, 21);
		panel.add(txtpnName);
		
		textField = new JTextField();
		textField.setBounds(83, 44, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnGender = new JTextPane();
		txtpnGender.setText("Gender");
		txtpnGender.setBounds(29, 91, 50, 21);
		panel.add(txtpnGender);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(93, 91, 74, 21);
		panel.add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(83, 131, 84, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextPane txtpnAge = new JTextPane();
		txtpnAge.setText("Age:");
		txtpnAge.setBounds(31, 136, 40, 21);
		panel.add(txtpnAge);
		
		JTextPane txtpnPriority = new JTextPane();
		txtpnPriority.setText("Priority:");
		txtpnPriority.setBounds(31, 174, 58, 21);
		panel.add(txtpnPriority);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_1.setBounds(101, 174, 74, 21);
		panel.add(comboBox_1);
		
		JTextPane txtpnIssue = new JTextPane();
		txtpnIssue.setText("Issue:");
		txtpnIssue.setBounds(31, 207, 48, 21);
		panel.add(txtpnIssue);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Diabetes", "Headache", "Shock", "Stomachache", "Alergic", "Chest Pain", "Cut", "Trauma", "Choking", "Seizure"}));
		comboBox_2.setBounds(93, 207, 120, 21);
		panel.add(comboBox_2);
		
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String age = textField_1.getText();
				String gender = comboBox.getSelectedItem().toString();
				String priority = comboBox_1.getSelectedItem().toString();
				String issue = comboBox_2.getSelectedItem().toString();
				int myId = addToDatabase(name,age,gender,priority,issue);
				panel.setVisible(false);
				
				
				
				
				Queue q = new Queue(stmt,issue,myId);
				q.setVisible(false);
				q.setVisible(true);
				tabbedPane.remove(panel);
				tabbedPane.addTab("Queue", null,q, null);
				tabbedPane.setComponentAt(0, q);
				
				JPanel panel_1 = new DoctorF(stmt);
				tabbedPane.addTab("Doctor Form", null, panel_1, null);
				
				
			}
		});
		btnSubmit.setBounds(291, 199, 117, 29);
		panel.add(btnSubmit);
		
		JPanel panel_1 = new DoctorF(stmt);
		tabbedPane.addTab("Doctor Form", null, panel_1, null);
		
		
	}
	


	
	public int addToDatabase(String name,String age,String gender,String p, String issue) {
		String sqlHastalikSelect = "Select IssueID FROM MedicalIssues where name =" + "'" + issue + "'";
		ResultSet rs2 = null;
		int kobe = 0;
		int issueInt = 0;
		try {
			rs2 = stmt.executeQuery(sqlHastalikSelect);
			while(rs2.next())
				issueInt = rs2.getInt("IssueId");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "INSERT INTO PATIENT(Name,ID,Gender,Age,MedicalIssue,Priority,ArrivedWith) VALUES(" + "'" + name + "'" + "," + "DEFAULT"  + "," + "'" + gender + "'" + "," + "'" + age + "'"  + "," + "'" + issueInt +  "'" + "," + "'" + p + "'" + "," + "'" + "W" + "'"  + ");";
			 
		try {
			int rs = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//INSERT INTO Patient VALUES('Huseyin','1','M','23',1,3,'W');
		String str = "SELECT id FROM patient ORDER BY id desc LIMIT(1)";
		
		try {
			
			ResultSet rs86 = stmt.executeQuery(str);
			while(rs86.next()){
				kobe = rs86.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kobe;
		
				
	}
}		
