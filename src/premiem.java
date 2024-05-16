import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class premiem extends JFrame {
    private JTable libraryTable;
    private JTable playlistTable;
    private DefaultTableModel libraryTableModel;
    private DefaultTableModel playlistTableModel;
    private JTextField searchField;
    
    public premiem(JFrame p) {
        super("Premium Spotify");
        
        String[][] libraryData = null;
        String[] columnNames = {"Reference", "LINK", "Title Name", "Artist Name"};
        int rowCount = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotify", "root", "");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("select * from music");
            resultSet.last();
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            libraryData = new String[rowCount][4];
            int i = 0;
            while (resultSet.next()) {
                libraryData[i][0] = resultSet.getString(1);
                libraryData[i][1] = resultSet.getString(2);
                libraryData[i][2] = resultSet.getString(3);
                libraryData[i][3] = resultSet.getString(4);
                i++;
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }

        libraryTableModel = new DefaultTableModel(libraryData, columnNames);
        libraryTable = new JTable(libraryTableModel);
        JScrollPane libraryScrollPane = new JScrollPane(libraryTable);

        playlistTableModel = new DefaultTableModel(new String[0][4], columnNames);
        playlistTable = new JTable(playlistTableModel);
        JScrollPane playlistScrollPane = new JScrollPane(playlistTable);

        searchField = new JTextField(20);
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(libraryTableModel);
                libraryTable.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        });

        JButton addButton = new JButton("Add to Playlist");
        addButton.addActionListener(e -> addToPlaylist());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(libraryScrollPane);
        centerPanel.add(playlistScrollPane);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addButton);

        Color backgroundColor = new Color(240, 248, 255);
        Color foregroundColor = Color.BLACK;
        Color tableHeaderColor = new Color(75, 0, 130);
        Color selectionColor = new Color(173, 216, 230);
        Font tableFont = new Font("SansSerif", Font.PLAIN, 14);

        setupTableAppearance(libraryTable, tableHeaderColor, tableFont, foregroundColor, backgroundColor, selectionColor);
        setupTableAppearance(playlistTable, tableHeaderColor, tableFont, foregroundColor, backgroundColor, selectionColor);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().setBackground(backgroundColor);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupTableAppearance(JTable table, Color headerColor, Font tableFont, Color foregroundColor, Color backgroundColor, Color selectionColor) {
        table.setBackground(backgroundColor);
        table.setForeground(foregroundColor);
        table.getTableHeader().setBackground(headerColor);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(tableFont);
        table.setFont(tableFont);
        table.setSelectionBackground(selectionColor);
    }

    private void addToPlaylist() {
        int[] selectedRows = libraryTable.getSelectedRows();
        for (int row : selectedRows) {
            String[] rowData = new String[libraryTable.getColumnCount()];
            for (int col = 0; col < libraryTable.getColumnCount(); col++) {
                rowData[col] = (String) libraryTable.getValueAt(row, col);
            }
            playlistTableModel.addRow(rowData);
        }
    }


}
