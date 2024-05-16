import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

@SuppressWarnings("serial")
public class listemusic extends JFrame {
    
    public listemusic() {
        super("Spotify");

        JFrame m = this;

        UIManager.put("Menu.font", new FontUIResource(new Font("SansSerif", Font.BOLD, 13)));
        UIManager.put("MenuItem.font", new FontUIResource(new Font("SansSerif", Font.PLAIN, 14)));

        JMenuBar bar = new JMenuBar();
        bar.setBackground(new Color(230, 230, 250)); 
        JMenu jMenu0 = new JMenu("Premium Spotify");
        jMenu0.setForeground(Color.BLACK);
        jMenu0.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JMenuItem addMusicItem0 = new JMenuItem("Premium Spotify");
        addMusicItem0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new premiem(m);
            }
        });
        jMenu0.add(addMusicItem0);


        JMenu jMenu1 = new JMenu("AddMusic");
        jMenu1.setForeground(Color.BLACK);
        jMenu1.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JMenuItem addMusicItem = new JMenuItem("Add Music");
        addMusicItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjoutMusic(m);
            }
        });
        jMenu1.add(addMusicItem);

        JMenu jMenu2 = new JMenu("PlayListe");
        jMenu2.setForeground(Color.BLACK);
        jMenu2.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JMenuItem playListeItem = new JMenuItem("PlayListe");
        playListeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aff(m);
            }
        });
        jMenu2.add(playListeItem);


        JMenu jMenu3 = new JMenu("Exit");
        jMenu3.setForeground(Color.BLACK);
        jMenu3.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jMenu3.add(exitItem);
        bar.add(jMenu0);

        bar.add(jMenu1);
        bar.add(jMenu2);
        bar.add(jMenu0);

        bar.add(jMenu3);

        setJMenuBar(bar);
        
        getContentPane().setBackground(new Color(240, 248, 255)); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); 
        setLocationRelativeTo(null);
        setVisible(true);
    }

 
}
