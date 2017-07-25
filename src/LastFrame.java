import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class LastFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public LastFrame(Statement stmt,int pID,int teamID,int waiting) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String query = "SELECT Patient.name, Patient.ID, Patient.Priority, MedicalIssues.Name AS issue,  (tDoctorForm.medProcedure).t1 as treatMe1,(tDoctorForm.medProcedure).t2 as treatMe2,(tDoctorForm.medProcedure).t3 as treatMe3 ,"+
						" (tDoctorform.Drug).t1 as myDrug1,(tDoctorform.Drug).t2 as myDrug2, (tDoctorform.Drug).t3 as myDrug3,tDoctorform.decision as SN " +
				"FROM Patient JOIN  medicalissues ON patient.medicalissue=medicalissues.issueID JOIN tdoctorform ON Patient.id = tdoctorform.id WHERE Patient.id =" + pID;
		
		ResultSet rs4 = null;
		
		try {
			rs4 = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		JTextPane txtpnPatientVitalStatistics = new JTextPane();
		txtpnPatientVitalStatistics.setText("Patient Vital Statistics");
		txtpnPatientVitalStatistics.setBounds(144, 6, 145, 16);
		contentPane.add(txtpnPatientVitalStatistics);
		
		JTextPane txtpnWaitedTime = new JTextPane();
		txtpnWaitedTime.setText("Waited Time:");
		txtpnWaitedTime.setBounds(6, 123, 86, 21);
		contentPane.add(txtpnWaitedTime);
		
		JTextPane txtpnMedicalIssue = new JTextPane();
		txtpnMedicalIssue.setText("Medical Issue:");
		txtpnMedicalIssue.setBounds(6, 156, 97, 16);
		contentPane.add(txtpnMedicalIssue);
		
		JTextPane txtpnTreatments = new JTextPane();
		txtpnTreatments.setText("Treatments:");
		txtpnTreatments.setBounds(6, 184, 75, 16);
		contentPane.add(txtpnTreatments);
		
		JTextPane txtpnDrugs = new JTextPane();
		txtpnDrugs.setText("Drugs:");
		txtpnDrugs.setBounds(6, 212, 47, 16);
		contentPane.add(txtpnDrugs);
		
		JTextPane txtpnSentHomeOr = new JTextPane();
		txtpnSentHomeOr.setText("Sent Home or Not:");
		txtpnSentHomeOr.setBounds(6, 240, 116, 16);
		contentPane.add(txtpnSentHomeOr);
		
		JTextPane txtpnPatientName = new JTextPane();
		txtpnPatientName.setText("Patient Name:");
		txtpnPatientName.setBounds(6, 59, 97, 16);
		contentPane.add(txtpnPatientName);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(120, 59, 256, 21);
		contentPane.add(textPane);
		
		JTextPane txtpnLala_1 = new JTextPane();
		
		txtpnLala_1.setBounds(104, 123, 137, 21);
		contentPane.add(txtpnLala_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(115, 151, 174, 21);
		contentPane.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(93, 179, 228, 21);
		contentPane.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setBounds(61, 207, 228, 21);
		contentPane.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBounds(132, 235, 97, 21);
		contentPane.add(textPane_5);
		String strName ="";
		String medIssue = "";
		String treat1 = "";
		String treat2 = "";
		String treat3 = "";
		String drugs1 = "";
		String drugs2 = "";
		String drugs3 = "";
		String sentOrNot = "";
		try {
			while(rs4.next()) {
				strName = rs4.getString("name");
				strName += ",id: " + rs4.getString("ID");
				strName += ",priority: " + rs4.getString("priority");
				
				medIssue = rs4.getString("issue");
				treat1 = rs4.getString("treatMe1");
				treat2 = rs4.getString("treatMe2");
				treat3 = rs4.getString("treatMe3");
				drugs1 = rs4.getString("myDrug1");
				drugs2 = rs4.getString("myDrug2");
				drugs3 = rs4.getString("myDrug3");
				sentOrNot = rs4.getString("SN");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(treat1.equals("---"))
			treat1 = "";
		
		if(treat2.equals("---"))
			treat2 = "";
		
		if(treat3.equals("---"))
			treat3 = "";
		
		if(drugs1.equals("------"))
			drugs1 = "";
		
		if(drugs2.equals("------"))
			drugs2 = "";
		
		if(drugs3.equals("------"))
			drugs3 = "";
		
		textPane.setText(strName);
		textPane_2.setText(medIssue);
		textPane_3.setText(treat1+" "+treat2+" "+treat3);
		textPane_4.setText(drugs1+" "+drugs2+" "+drugs3);
		textPane_5.setText(sentOrNot);
		
		String sqlc = "SELECT sum(cost) as cs FROM (SELECT * FROM medicalprocedure  WHERE (medicalprocedure.name = '"+treat1+"' OR medicalprocedure.name = '"+treat2+"' OR medicalprocedure.name = '"+drugs3+"') "+
						"UNION SELECT * FROM drug WHERE ( drug.name = '"+drugs1+"' OR drug.name = '"+drugs2+"' OR drug.name = '"+drugs3+"')) foo ";
		ResultSet rs = null;
		try {
			 rs = stmt.executeQuery(sqlc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cost = 0;
		try {
			while(rs.next()) {
				cost=rs.getInt("cs");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JTextPane txtpnCost = new JTextPane();
		txtpnCost.setText("Cost:");
		txtpnCost.setBounds(6, 90, 41, 21);
		contentPane.add(txtpnCost);
		
		JTextPane txtpnLala = new JTextPane();
		txtpnLala.setText(""+cost);
		txtpnLala.setBounds(61, 90, 137, 21);
		contentPane.add(txtpnLala);
		
		txtpnLala_1.setText(""+waiting);
		
		
		String insertSQL = "INSERT INTO patient_vitals_log VALUES(" + "'" + strName + "'" + ","+ cost +"," + "'" + medIssue + "'" + "," + "'" + waiting + "'" + ","  + "'" + treat1+" "+treat2+" "+treat3 + "'" + "," + "'" + drugs1+" "+drugs2+" "+drugs3 + "'" + "," + "'" + sentOrNot + "'" + ")"; 
		//INSERT INTO patient_vitals_log VALUES ('efe','123','Diabetes','120','....','....','sent');
	
		try {
			stmt.executeUpdate(insertSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//"INSERT INTO PATIENT(Name,ID,Gender,Age,MedicalIssue,Priority,ArrivedWith) VALUES(" + "'" + name + "'" + "," + "DEFAULT"  + ",
	}
}
