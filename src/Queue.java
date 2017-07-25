import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.CardLayout;

public class Queue extends JPanel {
	private JTable table;
	private Statement stmt;
	public static  String[][] arr2;
	

	/**
	 * Create the panel.
	 */
	public Queue(Statement state,String issue,int myID) {
		JPanel card1 = new JPanel();
		
		
		add(card1,"Card1");
		//add(card2,"Card2");
		stmt = state;
		setLayout(new CardLayout(0, 0));
		CardLayout cardLayout = (CardLayout) getLayout();
		cardLayout.show(this, "Card1");
		JTextPane txtpnQueue = new JTextPane();
		card1.setLayout(null);
		txtpnQueue.setFont(new Font("Lucida Blackletter", Font.PLAIN, 16));
		txtpnQueue.setText("             Queue");
		txtpnQueue.setBounds(153, 6, 137, 21);
		card1.add(txtpnQueue);
		
		
		
		
		
		
		
		table = new JTable();
		
		String sqlHastalikSelect = "Select IssueID FROM MedicalIssues where name =" + "'" + issue + "'";
		ResultSet rs2 = null;
		int issueInt = 0;
		try {
			rs2 = stmt.executeQuery(sqlHastalikSelect);
			while(rs2.next())
				issueInt = rs2.getInt("IssueId");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sqlteams = "SELECT id FROM team WHERE (Issues).Proc1="+ issueInt + "OR (Issues).Proc2=" + issueInt +" OR (Issues).Proc3=" + issueInt ;
		ResultSet rs3 = null;
		int teams=0;
		ArrayList<Integer> ids;
		try {
			rs3 = stmt.executeQuery(sqlteams);
			ids = new ArrayList<Integer>();
			while(rs3.next()){
				System.out.println(rs3.getInt("id"));
				ids.add(rs3.getInt("id"));
				teams++;
			}
			
			ResultSet rs4 = null;
			for(int i =0;i<teams;i++){
				String getPatients = "SELECT patientID FROM queue where  teamID = " + "'" + ids.get(i) + "'";
				
				rs4 = stmt.executeQuery(getPatients);
				String[] arr = { "Team " + ids.get(i) };
				
				
				int patients =0;
				while(rs4.next()) {
					patients++;
				}
				ResultSet rs5;
				rs5 = stmt.executeQuery(getPatients);
				arr2 = new String[patients][1];
				int k = 0;
				while(rs5.next() && k < patients) {
					arr2[k][0] = "" + rs5.getInt("patientID");
					System.out.println("q" + arr2[k][0]);
					k++;
				}
				
				
				JTable table1 = new JTable(arr2,arr);
				table1.setBackground(Color.GREEN);
				table1.setBounds(6 + 93*i,96, 85, 90);
				card1.add(table1);
				
				JTextPane textPane = new JTextPane();
				textPane.setText("Team " + ids.get(i) );
				textPane.setBounds(6 + 93*i,75, 85, 16);
				card1.add(textPane);
				
				JButton btnSubmit = new JButton("Submit");
				btnSubmit.setBounds(6 + 93*i, 200, 85, 29);
				final int temp = i;
				cardLayout.show(this, "Card2");
				JPanel p = this;
				JPanel save  = null;
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//	String mySQL = "INSERT INTO queue"
						String sqlStr = "INSERT INTO queue VALUES(" + "'" + ids.get(temp) + "'" +  "," + myID + ","+0+")";
						 try {
							stmt.executeUpdate(sqlStr);
							 JPanel card2 = new NewQueue(stmt,ids.get(temp));
							 add(card2,"Card2");
							 
							 cardLayout.show(p, "Card2");
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Throwable e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 cardLayout.next(p);
						
						
					}
				});
				
				card1.add(btnSubmit);
				
				
				
				
				
			}
			
				
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
