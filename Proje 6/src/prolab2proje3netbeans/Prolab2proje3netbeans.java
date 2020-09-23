/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab2proje3netbeans;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author melih
 */
public class Prolab2proje3netbeans {

    /**
     * @param args the command line arguments
     */
    public static String host_name;
    public static String server_name;
    public static String server_password;
    public static DataBase database =new DataBase();
    
    public static Connection connectdb() {
        try {
        Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://"+host_name+":3306/netflixdatabase?serverTimezone=UTC",server_name,server_password);
        
        return myConn;
        
        }catch(Exception e){
        
            
        }
        return null;
    }
    
    
    public void sorguekle(kullanici user){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			int myRs=  ((java.sql.Statement) myStat).executeUpdate("INSERT INTO kullanıcıtablosu values(null,"+"'"+user.kullanici_adi+"'"+","+"'"+user.kullanici_email+"'"+","+"'"+user.kullanici_sifre+"'"+","+"'"+user.kullanici_dogumtarihi+"'"+")");
//                      
		}catch(Exception e){
			e.printStackTrace();
		}
        
        
        
        
    }
    public int kayitsorgula(kullanici user){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
                        //SELECT EXISTS(SELECT id FROM tablo WHERE id='$aranan_id')
			//ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT EXISTS (SELECT kullanıcı_email FROM kullanıcıtablosu WHERE kullanıcı_email="+"'"+user.kullanici_email+"'"+")");
                       ResultSet myRs=  myStat.executeQuery("SELECT * FROM kullanıcıtablosu WHERE kullanıcı_email='"+user.kullanici_email+"'");
                         
                        //System.out.println(""+count);
                        if(myRs.next()){
                            //System.out.println("success");
                            return 1;
                        }else{
                            //System.out.println("failure");
                            //return 0;
                        }
                      
		}catch(Exception e){
			e.printStackTrace();
		}
        return -1;
        
        
        
        
    }
    
    
    public int kullanıcıbul(kullanici user){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT kullanıcı_id FROM kullanıcıtablosu WHERE kullanıcı_email='"+user.kullanici_email+"'");
                        int id =0;
                        while(myRs.next()){
                            id=myRs.getInt("kullanıcı_id");
                            //System.out.println(""+id);
                        }
                        return id;
                     
		}catch(Exception e){
			e.printStackTrace();
                        
                        
		}
        
        return -1;
        
        
    }
    
    public int kullanıcıbulemail(String email){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT kullanıcı_id FROM kullanıcıtablosu WHERE kullanıcı_email='"+email+"'");
                        int id =0;
                        while(myRs.next()){
                            id=myRs.getInt("kullanıcı_id");
                            //System.out.println(""+id);
                        }
                        return id;
                     
		}catch(Exception e){
			e.printStackTrace();
                        
                        
		}
        
        return -1;
        
        
    }
    
    public int[] tip_tespit(int id,int []dizi){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT * FROM favoritablosu WHERE kullanıcı_id='"+id+"'");
                        int i=0;
                        while(myRs.next()){
                            dizi[i]=myRs.getInt("tip_id");
                            i++;
                            
                        }
                        
                                         
		}catch(Exception e){
			e.printStackTrace();
                        
                        
                        
		}
        
        return dizi;
    }
    
    public tip  belirleme(tip f_tip,int t_id) {
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT * FROM tiptablosu WHERE tip_id='"+t_id+"'");
                        
                        while(myRs.next()){
                         f_tip.tip_adı=myRs.getString("tip_adı");
                            
                        }
                        
                                         
		}catch(Exception e){
			e.printStackTrace();
                            
                        
		}
        
        return f_tip;
    }
    
    
    public static ArrayList <program> favoriprogramlar (ArrayList <program> programlar,int tip_id){
        programlar.clear();
         try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT * FROM tiptablosu,programtablosu,programtiptablosu WHERE programtablosu.program_id=programtiptablosu.program_id and"
                                + " tiptablosu.tip_id=programtiptablosu.tip_id and tiptablosu.tip_id='"+tip_id+"' ORDER BY program_ortpuan DESC");
                        int sayac=0;
                        
                        while(myRs.next() && sayac<2){
                            program prg=new program();
                            //System.out.print(""+myRs.getFloat("program_ortpuan"));
                            //System.out.println(" "+myRs.getString("program_adı"));
                            prg.adı=myRs.getString("program_adı");
                            prg.ort=myRs.getFloat("program_ortpuan");  
                            programlar.add(prg);
                            sayac++;
                            
                        }

                        return programlar;
                                         
		}catch(Exception e){
			e.printStackTrace();
                            
                        
		}
         //System.out.println("null");
         return null;
    }
    
    
    
    public void favoriekle(int k_id, int t_id){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			int myRs=  ((java.sql.Statement) myStat).executeUpdate("INSERT INTO favoritablosu values('"+k_id+"','"+t_id+"'"+")");
//                      
		}catch(Exception e){
			e.printStackTrace();
		}
        
        
        
        
    }
     public int kullanıcıgiriskontrol(String k_email, String k_sifre){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT * FROM kullanıcıtablosu WHERE kullanıcı_email='"+k_email+"'"+"and kullanıcı_sifre='"+k_sifre+"'");
                        String email ="";
                        String sifre="";
                        while(myRs.next()){
                            email=myRs.getString("kullanıcı_email");
                            sifre=myRs.getString("kullanıcı_sifre");
                            //System.out.println("xemail "+email);
                            //System.out.println("xsifre "+sifre);
                            return 1;
                            
                        }
                        return 0;
                        
                     
		}catch(Exception e){
			e.printStackTrace();
                        
                        
                        
		}
        
        return -1;
        
        
    }
     
     
     
     
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        database.setVisible(true);
        
        
        
        
        
        
    }
    
}
