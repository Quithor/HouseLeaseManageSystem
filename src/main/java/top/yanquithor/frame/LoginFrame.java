package top.yanquithor.frame;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to create window that user log in system.
 * @author YanQuithor
 * @since 2024.1.8
 */
public class LoginFrame extends JFrame {
    
    public LoginFrame() {
        initFrame();
        createFrame();
    }
    
    private void createFrame() {
        JButton landlord = new JButton("我是房东");
        JButton tenant = new JButton("我是租户");
        landlord.setBounds(120,40,100,30);
        tenant.setBounds(260,40,100,30);
        tenant.addActionListener(e->{});
        add(landlord);
        add(tenant);
        JTextField id = new JTextField();
        JPasswordField pw = new JPasswordField();
        JLabel uid = new JLabel("id");
        JLabel pwL = new JLabel("密码");
        uid.setBounds(120,100,20,30);
        pwL.setBounds(120,160,20,30);
        add(uid);
        add(pwL);
        id.setBounds(160,100,200,30);
        pw.setBounds(160,160,200,30);
        add(id);
        add(pw);
    }
    
    private void initFrame() {
        setSize(480,360);
        setTitle("登录");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    }
}
