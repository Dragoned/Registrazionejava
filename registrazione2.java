import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Container;
public class registrazione2 {
    public static JFrame f = new JFrame("register");
    public static Container c = f.getContentPane();
    public static JPasswordField password, passwordC;
    public static JTextField user;
    public static JCheckBox MostraP;
    public static String directory="C:/Users/Admin/Documents/registrazioni/";
    public static void main(String[] args) {
        user(10, 10, 500, 40);
        password(10, 110, 500, 40);
        c.setLayout(null);
        f.setSize(700, 450);
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
                ControllaP();
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
        if(MostraP.isSelected()){
            password.setEchoChar((char)0);
            passwordC.setEchoChar((char)0);
        }
        else{
            password.setEchoChar('\u2022');
            passwordC.setEchoChar('\u2022');
        }
    }

    public static void ControllaP(){
        String PasswordControl=password.getText();
        boolean LM=false,N=false,CS=false,LP=false;
        if(PasswordControl.length()>=8){
            LP=true;
        }
        for(int i=0;i<PasswordControl.length();i++){
            if(PasswordControl.charAt(i)>=30&&PasswordControl.charAt(i)<=39){
                N=true;
            }
            else if(PasswordControl.charAt(i)>=65&&PasswordControl.charAt(i)<=90){
                LM=true;
            }
            else if(PasswordControl.charAt(i)==61||PasswordControl.charAt(i)==47||PasswordControl.charAt(i)==64||PasswordControl.charAt(i)==95||PasswordControl.charAt(i)==94){
                CS=true;
            }
        }
        if(LP==true&&N==true&&LM==true&&CS==true){
            if(password.getText()==passwordC.getText()){
                Registra(directory+user.getText()+".txt");
            }
            else{
                errPassNC();
            }
        }
        else{
            errPass();
        }
    }

    public static void Registra(String path){
        System.out.println(path);
        try {
            File registra = new File(path);
            if (registra.exists()){
                //System.out.println("Il file " + path + " esiste");
                errUserExist();
            }else if (registra.createNewFile()){
                //System.out.println("Il file "+path+" e stato creato");
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
            endRegister();
        }catch(IOException e){
            System.out.println("errore nella scrittura");
        }
    }

    public static void endRegister(){
        JFrame Registrato=new JFrame("Registrato");
        JButton cf=new JButton("chiudi finestra ");
        JLabel regist=new JLabel("Registrazone completata con sucesso");
        Registrato.setBounds(50,50,300,200);
        regist.setBounds(20,20,400,50);
        cf.setBounds(20,100,200,50);
        cf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrato.hide();   
            }
        });
        Registrato.setLayout(null);
        Registrato.add(regist);
        Registrato.add(cf);
        Registrato.show();
    }

    public static void errUserExist(){
        JFrame ErrUt=new JFrame("Utente gia esistente");
        JButton cf=new JButton("chiudi finestra ");
        JLabel regist=new JLabel("utente gia registrarto riprovare con un altro nome utente");
        ErrUt.setBounds(50,50,400,200);
        regist.setBounds(20,20,400,50);
        cf.setBounds(20,100,200,50);
        cf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrUt.hide();   
            }
        });
        ErrUt.setLayout(null);
        ErrUt.add(regist);
        ErrUt.add(cf);
        ErrUt.show();
    }

    public static void errPass(){
        JFrame ErrPass=new JFrame("Errore password");
        JButton cf=new JButton("chiudi finestra ");
        JLabel Err=new JLabel("la password deve essere almeno di 8 caretteri e deve contenere ");
        JLabel Err2=new JLabel(" lettere maiuscole caratteri speciali e lettere maiuscole e numeri");
        ErrPass.setBounds(50,50,400,200);
        Err.setBounds(20,20,400,50);
        Err2.setBounds(20,60,400,50);
        cf.setBounds(20,100,200,50);
        cf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrPass.hide();   
            }
        });
        ErrPass.setLayout(null);
        ErrPass.add(Err);
        ErrPass.add(Err2);
        ErrPass.add(cf);
        ErrPass.show();
    }
    public static void errPassNC(){
        JFrame ErrPassNC=new JFrame("Errore password");
        JButton ep=new JButton("chiudi finestra ");
        JLabel Err=new JLabel("Password non corispondente");
        ErrPassNC.setBounds(50,50,400,200);
        Err.setBounds(20,20,400,50);
        ep.setBounds(20,100,200,50);
        ep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrPassNC.hide();   
            }
        });
        ErrPassNC.setLayout(null);
        ErrPassNC.add(Err);
        ErrPassNC.add(ep);
        ErrPassNC.show();
    }
}