package top.yanquithor.frame;

import top.yanquithor.table.Landlord;
import top.yanquithor.table.People;
import top.yanquithor.table.Tenant;

import javax.swing.*;

/**
 * @author YanQuithor
 * @since 2023.12.26
 */
public class MainFrame extends JFrame {
    
    public static Tenant userTenant;
    public static Landlord userLandlord;
    
    public MainFrame() {
        initFrame();
        initMenuBar();
    }
    
    private void initMenuBar() {
        JMenuBar jmb = new JMenuBar();
        JMenu account = new JMenu("账户");
        JMenuItem relogin = new JMenuItem("重新登录");
        JMenuItem exitLogin = new JMenuItem("退出登录");
        JMenuItem drop = new JMenuItem("注销账号");
        JMenuItem exit = new JMenuItem("退出系统");
        
        relogin.addActionListener(e -> {
//            setVisible(false);
        });
        exitLogin.addActionListener(e -> {});
        drop.addActionListener(e -> {});
        
        exit.addActionListener(e -> System.exit(0));
        
        account.add(relogin);
        account.add(exitLogin);
        account.add(drop);
        jmb.add(account);
        jmb.add(exit);
        setJMenuBar(jmb);
    }
    
    private void initFrame() {
        setSize(960,720);
        setTitle("房屋租赁平台");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void setUser(People people) {
        if (people instanceof Tenant)
            userTenant = (Tenant) people;
        else if (people instanceof Landlord)
            userLandlord = (Landlord) people;
        else System.out.println("ERROR: Failed found this class!");
    }
    
    private void landlord() {
        //
    }
    
    private void tenant() {
        //cascade
    }
}
