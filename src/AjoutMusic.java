import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AjoutMusic extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AjoutMusic(JFrame p) {
        JFrame f = this;
        f.setTitle("Add Music");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel l = new JLabel("Reference");
        JLabel l1 = new JLabel("LINK");
        JLabel l2 = new JLabel("Title Name");
        JLabel l3 = new JLabel("Artist Name");

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        l.setFont(labelFont);
        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);

        JTextField field1 = new JTextField(15);
        JTextField field2 = new JTextField(15);
        JTextField field3 = new JTextField(15);
        JTextField field4 = new JTextField(15);

        JButton button = new JButton("Add");
        JButton button1 = new JButton("Close");
        JButton button2 = new JButton("Clear");
        JButton button3 = new JButton("Update");
        JButton button4 = new JButton("Delete");
        JButton button5 = new JButton("Display");

        // Set button font
        Font buttonFont = new Font("SansSerif", Font.PLAIN, 14);
        button.setFont(buttonFont);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);
        button4.setFont(buttonFont);
        button5.setFont(buttonFont);

        // Set button border style
        Border buttonBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        button.setBorder(buttonBorder);
        button1.setBorder(buttonBorder);
        button2.setBorder(buttonBorder);
        button3.setBorder(buttonBorder);
        button4.setBorder(buttonBorder);
        button5.setBorder(buttonBorder);

        // Set colors
        Color buttonColor = new Color(75, 0, 130); // Indigo
        Color errorButtonColor = new Color(220, 20, 60); // Crimson
        Color labelColor = Color.BLACK; // Black
        Color panelBgColor = new Color(240, 248, 255); // Alice Blue
        Color buttonBgColor = new Color(230, 230, 250); // Light gray

        panel.setBackground(panelBgColor);
        f.getContentPane().setBackground(panelBgColor);

        button.setBackground(buttonBgColor);
        button1.setBackground(buttonBgColor);
        button2.setBackground(buttonBgColor);
        button3.setBackground(buttonBgColor);
        button4.setBackground(errorButtonColor);
        button5.setBackground(buttonBgColor);

        button.setForeground(buttonColor);
        button1.setForeground(buttonColor);
        button2.setForeground(buttonColor);
        button3.setForeground(buttonColor);
        button4.setForeground(Color.WHITE);
        button5.setForeground(buttonColor);

        l.setForeground(labelColor);
        l1.setForeground(labelColor);
        l2.setForeground(labelColor);
        l3.setForeground(labelColor);

        // Add components to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(l, gbc);

        gbc.gridx = 1;
        panel.add(field1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(l1, gbc);

        gbc.gridx = 1;
        panel.add(field2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(l2, gbc);

        gbc.gridx = 1;
        panel.add(field3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(l3, gbc);

        gbc.gridx = 1;
        panel.add(field4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(button, gbc);

        gbc.gridx = 1;
        panel.add(button1, gbc);

        gbc.gridx = 2;
        panel.add(button2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(button3, gbc);

        gbc.gridx = 1;
        panel.add(button4, gbc);

        gbc.gridx = 2;
        panel.add(button5, gbc);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ref = field1.getText();
                String link = field2.getText();
                String titre = field3.getText();
                String artiste = field4.getText();

                if (ref.isEmpty() || link.isEmpty() || titre.isEmpty() || artiste.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                        java.sql.Statement st = bd.createStatement();
                        st.executeUpdate("INSERT INTO music VALUES('" + ref + "','" + link + "','" + titre + "','" + artiste + "')");

                        field1.setText("");
                        field2.setText("");
                        field3.setText("");
                        field4.setText("");
                        JOptionPane.showMessageDialog(null, "Music added: " + ref);

                    } catch (ClassNotFoundException | SQLException e1) {
                        JOptionPane.showMessageDialog(null, "An error occurred while adding the music.", "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field1.setText("");
                field2.setText("");
                field3.setText("");
                field4.setText("");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ref = field1.getText();
                String link = field2.getText();
                String titre = field3.getText();
                String artiste = field4.getText();

                if (ref.isEmpty() || link.isEmpty() || titre.isEmpty() || artiste.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields to update.");
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                        java.sql.Statement st = bd.createStatement();
                        st.executeUpdate("DELETE FROM music WHERE ref=" + ref);
                        st.executeUpdate("INSERT INTO music VALUES('" + ref + "','" + link + "','" + titre + "','" + artiste + "')");
                        JOptionPane.showMessageDialog(null, "Music updated: " + ref);

                    } catch (ClassNotFoundException | SQLException e1) {
                        JOptionPane.showMessageDialog(null, "An error occurred while updating the music.", "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ref = field1.getText();
                if (ref.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in the music reference to delete.");
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                        java.sql.Statement st = bd.createStatement();
                        st.executeUpdate("DELETE FROM music WHERE ref=" + ref);
                        JOptionPane.showMessageDialog(null, "Music deleted: " + ref);

                    } catch (ClassNotFoundException | SQLException e1) {
                        JOptionPane.showMessageDialog(null, "An error occurred while deleting the music.", "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
                    java.sql.Statement st = bd.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM music WHERE ref=" + field1.getText());
                    if (rs.next()) {
                        field1.setText(rs.getString("ref"));
                        field2.setText(rs.getString("link"));
                        field3.setText(rs.getString("titre"));
                        field4.setText(rs.getString("artist"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No records found.");
                    }

                    rs.close();
                    st.close();
                    bd.close();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        f.add(panel);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(600, 400);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

