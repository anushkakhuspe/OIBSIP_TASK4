//TASK_4 Online Examination - by Anushka
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.Timer;
import java.util.TimerTask;

class login extends JFrame implements ActionListener {
    JButton btn1;
    JPanel newPanel;
    JLabel userLabel, passLabel, label;
    final JTextField textField1, textField2;

    login() {
        userLabel = new JLabel();
        userLabel.setText("    Username :");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("    Password :");
        textField2 = new JPasswordField(8);
        btn1 = new JButton("   SUBMIT   ");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(btn1);
        add(newPanel, BorderLayout.CENTER);
        btn1.addActionListener(this);
        setTitle("Login");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if (!passValue.equals(""))
            new Test(userValue);
        else {
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }
}

class Test extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton btn1, btn2, log;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer = new Timer();

    Test(String s) {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        btn1 = new JButton("Save and Next");
        btn2 = new JButton("Review");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        add(btn1);
        add(btn2);
        set();
        l.setBounds(30, 40, 500, 20);
        l1.setBounds(20, 20, 500, 20);
        jb[0].setBounds(50, 80, 300, 20);
        jb[1].setBounds(50, 110, 300, 20);
        jb[2].setBounds(50, 140, 300, 20);
        jb[3].setBounds(50, 170, 300, 20);
        btn1.setBounds(95, 240, 140, 30);
        btn2.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 90;

            public void run() {
                l1.setText("Time left: " + i);
                i--;
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Over");
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 4) {
                btn1.setEnabled(false);
                btn2.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Save for later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(500, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 4)
                btn2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "Score =" + count + "/5");
            System.exit(0);
        }
    }

    // Set Questions
    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText("1: Who invented Java Programming?");
            jb[0].setText("Guido van Rossum");
            jb[1].setText("James Gosling");
            jb[2].setText("Dennis Ritchie");
            jb[3].setText("Bjarne Stroustrup");
        }
        if (current == 1) {
            l.setText("2: Which statement is true about Java?");
            jb[0].setText("Java is a sequence-dependent programming language");
            jb[1].setText("Java is a code dependent programming language");
            jb[2].setText("Java is a platform-dependent programming language");
            jb[3].setText("Java is a platform-independent programming language");
        }
        if (current == 2) {
            l.setText("3: Which component is used to compile, debug and execute the java programs?");
            jb[0].setText("JRE");
            jb[1].setText("JIT");
            jb[2].setText("JDK");
            jb[3].setText("JVM");
        }
        if (current == 3) {
            l.setText("4: Which one of the following is not a Java feature?");
            jb[0].setText("Object-oriented");
            jb[1].setText("Use of pointers");
            jb[2].setText("Portable");
            jb[3].setText("Dynamic and Extensible");
        }
        if (current == 4) {
            l.setText("5: Which of these cannot be used for a variable name in Java?");
            jb[0].setText("identifier & keyword");
            jb[1].setText("identifier");
            jb[2].setText("keyword");
            jb[3].setText("none of the mentioned");
        }
        l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);
    }

    // Check Options
    boolean check() {
        if (current == 0)
            return (jb[1].isSelected());
        if (current == 1)
            return (jb[3].isSelected());
        if (current == 2)
            return (jb[2].isSelected());
        if (current == 3)
            return (jb[1].isSelected());
        if (current == 4)
            return (jb[2].isSelected());
        return false;
    }
}

class OnlineExamination {
    public static void main(String args[]) {
        try {
            login form = new login();
            form.setSize(400, 150);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}