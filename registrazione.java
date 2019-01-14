import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Container;
public class registrazione {
    public static JFrame f = new JFrame("register");
    public static Container c = f.getContentPane();
    public static JPasswordField password, passwordC;
    public static JTextField user;
    public static JCheckBox MostraP;
    public static String directory="C:/Users/Admin/Documents/registrazioni/";
    public static void main(String[] args) {
        user(10, 10, 500, 50);
        password(10, 110, 500, 50);
        c.setLayout(null);
        f.setSize(500, 400);
        f.show();
    }

    public static void password(int x, int y, int dx, int dy) {
        password=new JPasswordField();
        passwordC=new JPasswordField();
        MostraP = new JCheckBox("Mostra Password");
        JLabel LPassword = new JLabel("Password"), LCPassword = new JLabel("confirm Password");
        JButton register = new JButton("Register");
        LPassword.setBounds(x, y, dx, dy);
        password.setBounds(x, y + dy, dx, dy);
        LCPassword.setBounds(x, y + dy + 50, dx, dy);
        passwordC.setBounds(x, y + dy + 50 * 2, dx, dy);
        MostraP.setBounds(x + dx, y + dy, dx, dy);
        register.setBounds(x, y + dy * 2 + 50 * 3, dx / 2, dy / 2);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registra(directory+user.getText()+".txt");
            }
        });
        MostraP.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                MostraPass();
            }
        });
        c.add(LPassword);
        c.add(password);
        c.add(LCPassword);
        c.add(passwordC);
        c.add(MostraP);
        c.add(register);
    }

    public static void user(int x, int y, int dx, int dy) {
        user = new JTextField();
        JLabel LUser = new JLabel("user");
        LUser.setBounds(x, y, dx, dy);
        user.setBounds(x, y + 50, dx, dy);
        c.add(LUser);
        c.add(user);
    }

    public static void MostraPass() {
        System.out.println("MOstra password");
        if(MostraP.isSelected()){
            password.setEchoChar((char)0);
            passwordC.setEchoChar((char)0);
        }
        else{
            password.setEchoChar('\u2022');
            passwordC.setEchoChar('\u2022');
        }
    }

    public static void Registra(String path){
        System.out.println(path);
        try {
            File registra = new File(path);
            if (registra.exists())
                System.out.println("Il file " + path + " esiste");
            else if (registra.createNewFile()){
                System.out.println("Il file "+path+" e stato creato");
                scrivi(registra);
            }else
                System.out.println("Il file " + path + " non può essere creato");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scrivi(File registra){
        try{
            FileWriter fw=new FileWriter(registra);
            BufferedWriter bw =new BufferedWriter(fw);
            bw.write("user = "+user.getText());
            bw.newLine();
            bw.write("password = "+password.getText());
            bw.newLine();
            bw.write("conferma password = "+passwordC.getText());
            bw.flush();
            bw.close();
        }catch(IOException e){
            System.out.println("errore nella scrittura");
        }
    }
}
