import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;

@SuppressWarnings("serial")
public class aff extends JFrame {
    public aff(JFrame p) {
        super("PlayListe");

        String[][] s = null;
        String[] tit1 = null;
        int nb = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection bd = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
            java.sql.Statement st = bd.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from music");
            rs.last();
            nb = rs.getRow();
            rs.beforeFirst();
            s = new String[nb][4];
            int i = 0;
            while (rs.next()) {
                String ref = rs.getString(1);
                String link = rs.getString(2);
                String titre = rs.getString(3);
                String artist = rs.getString(4);
                s[i][0] = ref;
                s[i][1] = link;
                s[i][2] = titre;
                s[i][3] = artist;
                i++;
            }
            bd.close();
            tit1 = new String[]{"Reference", "LINK", "Title Name", "Artist Name"};
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }

        JTable t = new JTable(s, tit1);
        JScrollPane scrollPane = new JScrollPane(t);

        Color backgroundColor = new Color(240, 248, 255); 
        Color foregroundColor = Color.BLACK; 
        Color tableHeaderColor = new Color(75, 0, 130); 
        Color selectionColor = new Color(173, 216, 230); 

        
        Font tableFont = new Font("SansSerif", Font.PLAIN, 14);

        t.setBackground(backgroundColor);
        t.setForeground(foregroundColor);
        t.getTableHeader().setBackground(tableHeaderColor);
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setFont(tableFont);
        t.setFont(tableFont);
        t.setSelectionBackground(selectionColor);

        scrollPane.setBackground(backgroundColor);
        scrollPane.getViewport().setBackground(backgroundColor);
        scrollPane.setBorder(null);

        add(scrollPane);

        getContentPane().setBackground(backgroundColor);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
