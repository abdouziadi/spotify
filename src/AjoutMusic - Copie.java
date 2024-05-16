import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AjoutMusic extends JFrame {
	public AjoutMusic(JFrame p) {

        JFrame f = this;
		/*String [][] s =null;
		String[] tit1 =null;
		int nb =0;*/

        JLabel l = new JLabel("Référence");
        JLabel l1 = new JLabel("LINK");
        JLabel l2 = new JLabel("Nom De Titre");
        JLabel l3 = new JLabel("Nom De l'Artist");

        l.setBounds(50, 50, 100, 30);
        l1.setBounds(50, 100, 100, 30);
        l2.setBounds(50, 150, 100, 30);
        l3.setBounds(50, 200, 100, 30);

        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();
        JTextField field4 = new JTextField();

        field1.setBounds(160, 50, 100, 30);
        field2.setBounds(160, 100, 100, 30);
        field3.setBounds(160, 150, 100, 30);
        field4.setBounds(160, 200, 100, 30);

        JButton button = new JButton("ajouter");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String ref = field1.getText();
                String link = field2.getText();
                String titre = field3.getText();
                String artiste = field4.getText();

                if (ref.isEmpty() || link.isEmpty() || titre.isEmpty() || artiste.isEmpty()) {
                    JOptionPane.showMessageDialog(null, " Veuillez remplir tous les champs. : ");

                } else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                    java.sql.Statement st = bd.createStatement();
                    st.executeUpdate("insert into music values('" + field1.getText() + "','" + field2.getText()
                            + "','" + field3.getText() + "','" + field4.getText() + "')");
                    
                    field1.setText("");
                    field2.setText("");
                    field3.setText("");
                    field4.setText("");
                    JOptionPane.showMessageDialog(null, "Music ajoutée : " + field1.getText());

                } catch (ClassNotFoundException | SQLException e1) {
		            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'ajout du music.", "Erreur", JOptionPane.ERROR_MESSAGE);

                    e1.printStackTrace();

            }} 
        }});
        
 
        JButton button1 = new JButton("fermer");
        button1.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                f.hide();
            }
        });
        JButton button2 = new JButton("vider");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field1.setText("");
                field2.setText("");
                field3.setText("");
                field4.setText("");
            }
        });

        JButton button3 = new JButton("modifier");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String ref = field1.getText();
                String link = field2.getText();
                String titre = field3.getText();
                String artiste = field4.getText();

                if (ref.isEmpty() || link.isEmpty() || titre.isEmpty() || artiste.isEmpty()) {
                    JOptionPane.showMessageDialog(null, " Veuillez remplir tous les champs pour modifier. : ");
                } else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                    java.sql.Statement st = bd.createStatement();
                    st.executeUpdate("DELETE FROM music WHERE ref=" + field1.getText() + "");
                    st.executeUpdate("insert into music values('" + field1.getText() + "','" + field2.getText()
                            + "','" + field3.getText() + "','" + field4.getText() + "')");
                    JOptionPane.showMessageDialog(null, "Music modifiée : " + field1.getText());


                } catch (ClassNotFoundException | SQLException e1) {
		            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la modification du music.", "Erreur", JOptionPane.ERROR_MESSAGE);

                    e1.printStackTrace();
                }
            }
        }});
        JButton button4 = new JButton("Supprimer");
        button4.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	String ref = field1.getText();
                if (ref.isEmpty()) {
                    JOptionPane.showMessageDialog(null, " Veuillez remplir le ref de music a supprimer. : ");
                } else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                    java.sql.Statement st = bd.createStatement();
                    st.executeUpdate("DELETE FROM music WHERE ref=" + field1.getText() + "");
	                JOptionPane.showMessageDialog(null, "Music supprimé : " + field1.getText());

                } catch (ClassNotFoundException | SQLException e1) {
		            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la suppression du music.", "Erreur", JOptionPane.ERROR_MESSAGE);

                    e1.printStackTrace();
                }
            }
        }});

        JButton button5 = new JButton("Afficher");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = null;

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                    java.sql.Statement st = bd.createStatement();
                    String req = "Select * from music where ref="+ field1.getText() + "";
                    st = bd.createStatement();
                    rs = st.executeQuery(req);
                    while (rs.next()) {
                        field1.setText(rs.getString("ref"));
                        field2.setText(rs.getString("link"));
                        field3.setText(rs.getString("titre"));
                        field4.setText(rs.getString("artist"));

                    }

                    rs.close();
                    st.close();
                    bd.close();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        /*try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection bd=DriverManager.getConnection("jdbc:mysql://localhost/spotify","root","");
			java.sql.Statement st=bd.createStatement();
			ResultSet rs=st.executeQuery("select * from music");
			rs.last();
			 nb =rs.getRow();
			rs.beforeFirst();
			 s =new String[nb][4] ;
			int i=0;
			while(rs.next()) {
				
				String ref=rs.getString(1);
				String link=rs.getString(2);
				String titre=rs.getString(3);
				String artist=rs.getString(4);
				s[i][0]=ref;
				s[i][1]=link;
				s[i][2]=titre;
				s[i][3]=artist;
				i++;
				
			}
			bd.close();
			 tit1 =new String[] {"ref","link","titre","artist"};
			
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

        button.setBounds(50, 250, 90, 35);
        button1.setBounds(350, 250, 90, 35);
        button2.setBounds(250, 250, 90, 35);
        button3.setBounds(150, 250, 90, 35);
        button5.setBounds(300, 100, 120, 35);
        button4.setBounds(300, 150, 120, 35);
        

        button.setForeground(Color.BLUE);
        button1.setForeground(Color.BLUE);
        button2.setForeground(Color.BLUE);
        button3.setForeground(Color.BLUE);
        button4.setForeground(Color.red);
        button5.setForeground(Color.red);

        l.setForeground(Color.DARK_GRAY);
        l1.setForeground(Color.DARK_GRAY);
        l2.setForeground(Color.DARK_GRAY);
        l3.setForeground(Color.DARK_GRAY);


        add(l);
        add(l1);
        add(l2);
        add(l3);

        add(field1);
        add(field2);
        add(field3);
        add(field4);

        add(button);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        
 /*       JTable t =new JTable(s,tit1);
		JScrollPane scrol=new JScrollPane(t);
		this.getContentPane().setBackground(Color.pink);
		add(scrol);
		setSize(50,500);
		setVisible(true);*/

        setLayout(null);
        setSize(900,900);
		setLocationRelativeTo(null);
        setVisible(true);
    }
}
