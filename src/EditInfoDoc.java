import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditInfoDoc extends JPanel {

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public EditInfoDoc(Statement stmt,int teamID) {
		setLayout(null);
		int id = 0;
		
		String getPatients = "SELECT Patient.name, Patient.ID, Patient.Priority, Queue.ArrivalTime,Queue.waitingtime, MedicalIssues.Name as issue, (MedicalIssues.Procedure).t1 as kobe1, (MedicalIssues.Procedure).t2 as kobe2,(MedicalIssues.Procedure).t3 as kobe3"
				+ " FROM Queue JOIN Patient ON Queue.PatientID = Patient.ID JOIN  medicalissues on patient.medicalissue=medicalissues.issueID  WHERE Queue.teamID =" +  "'" + teamID + "'" + " ORDER BY Patient.Priority DESC,Queue.ArrivalTime Asc LIMIT(1)";
		
		ResultSet rs4 = null;
		try {
			rs4 = stmt.executeQuery(getPatients);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String str = "";
		String issue = "";
		int waiting =0;
		
		
		
		String proc1 = "",proc2 = "",proc3 = "";
		try {
			while(rs4.next()) {
				str += "name: " + rs4.getString("name") + ", ";
				id = rs4.getInt("ID");
				str += "id:" + id + ", ";
			
				str += rs4.getString("Priority") + " ";
				str += rs4.getTime("arrivaltime").toString()  + " ";
				waiting = rs4.getInt("waitingTime");
				issue = rs4.getString("issue");
				str += issue;
				proc1 = rs4.getString("kobe1");
				proc2 = rs4.getString("kobe2");
				proc3 = rs4.getString("kobe3");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JTextPane txtpnEditForm = new JTextPane();
		txtpnEditForm.setBounds(195, 5, 60, 16);
		txtpnEditForm.setText("Edit Form");
		add(txtpnEditForm);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(91, 52, 276, 25);
		textPane.setText(str);
		add(textPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"---",proc1 }));
		comboBox.setBounds(6, 132, 199, 27);
		add(comboBox);
		
		JTextPane txtpnMedicalIssue = new JTextPane();
		txtpnMedicalIssue.setText("Medical Procedure");
		txtpnMedicalIssue.setBounds(40, 104, 122, 16);
		add(txtpnMedicalIssue);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"------", "Drug-1", "Drug-2", "Drug-3", "Drug-4", "Drug-5", "Drug-6", "Drug-7", "Drug-8", "Drug-9", "Drug-10"}));
		comboBox_1.setBounds(6, 271, 117, 16);
		comboBox_1.setVisible(true);
		add(comboBox_1);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"---",proc2}));
		comboBox_3.setBounds(6, 159, 199, 27);
		add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(6, 186, 199, 27);
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"---",proc3}));
		add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"------", "Drug-1", "Drug-2", "Drug-3", "Drug-4", "Drug-5", "Drug-6", "Drug-7", "Drug-8", "Drug-9", "Drug-10"}));
		comboBox_5.setToolTipText("------\nDrug 1\nDrug 2\nDrug 3\nDrug 4\nDrug 5\nDrug 6\nDrug 7\nDrug 8\nDrug 9\nDrug 10");
		comboBox_5.setBounds(122, 266, 103, 27);
		add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"------", "Drug-1", "Drug-2", "Drug-3", "Drug-4", "Drug-5", "Drug-6", "Drug-7", "Drug-8", "Drug-9", "Drug-10"}));
		comboBox_6.setToolTipText("------\nDrug 1\nDrug 2\nDrug 3\nDrug 4\nDrug 5\nDrug 6\nDrug 7\nDrug 8\nDrug 9\nDrug 10");
		comboBox_6.setBounds(225, 266, 103, 27);
		add(comboBox_6);

		
		
		
		JTextPane txtpnDrugs = new JTextPane();
		txtpnDrugs.setText("Drugs");
		txtpnDrugs.setBounds(6, 243, 46, 16);
		add(txtpnDrugs);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Sent :)", "Stay :("}));
		comboBox_2.setBounds(284, 164, 102, 16);
		add(comboBox_2);
		
		JTextPane txtpnSentHomeOr = new JTextPane();
		txtpnSentHomeOr.setText("Sent Home or Not");
		txtpnSentHomeOr.setBounds(274, 132, 122, 13);
		add(txtpnSentHomeOr);
		final int t = id;
		JButton btnSubmit = new JButton("Submit");
		final int x = waiting;
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sqlStr = "INSERT INTO tDoctorForm VALUES(" + t  +  ",ROW( " + "'" + comboBox.getSelectedItem().toString() +"'" + 
								"," + "'" + comboBox_3.getSelectedItem().toString() +"'" + "," + "'" + comboBox_4.getSelectedItem().toString() +"')" + 
								",ROW( " + "'" + comboBox_1.getSelectedItem().toString() +"'" + 
								"," + "'" + comboBox_5.getSelectedItem().toString() +"'" + "," + "'" + comboBox_6.getSelectedItem().toString() +"')" 
								+ "," + "'" + comboBox_2.getSelectedItem().toString() + "'" + ")";
				
				
				String delQ = "DELETE FROM Queue WHERE PatientID =" + t ;
				try {
					stmt.executeUpdate(sqlStr);
					stmt.executeUpdate(delQ);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnSubmit.setEnabled(false);
				
				
				LastFrame frame = new LastFrame(stmt,t,teamID,x);
				frame.setVisible(true);
				
			}
		});
		btnSubmit.setBounds(327, 265, 117, 29);
		add(btnSubmit);
		
		
	}
}
