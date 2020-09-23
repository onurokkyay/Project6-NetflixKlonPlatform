/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab2proje3netbeans;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import static prolab2proje3netbeans.Prolab2proje3netbeans.connectdb;

/**
 *
 * @author melih
 */
public class operations {
    public static int tip_id;
    public static String sql_ara;
    public operations(int tip_id){
        this.tip_id=tip_id;
    }
    public operations(String sql_ara){
        this.sql_ara=sql_ara;
    }
    
    public static void listProgramsInJList(JList list,JFrame frame, ArrayList<program> secili_p){
        DefaultListModel listModel=new DefaultListModel();
        listModel.clear();
        secili_p.clear();
        try{
        
            Connection myConn=connectdb();
			PreparedStatement preparedStatement= (PreparedStatement) (Statement) myConn.prepareStatement("SELECT * FROM programtiptablosu,programtablosu WHERE programtiptablosu.tip_id='"+tip_id+"' and programtablosu.program_id=programtiptablosu.program_id");
			ResultSet resultSet= preparedStatement.executeQuery();
                        String dosya_yolu;
                        while(resultSet.next()){
//                          System.out.println(""+resultSet.getString("program_adı"));
                          
                             dosya_yolu="src\\filmkapakları2\\"+resultSet.getInt("program_id")+".jpg";    
                             program pr=new program(resultSet.getString("program_adı"));  
                             pr.photo=new ImageIcon (dosya_yolu);
                           
                            secili_p.add(pr);
                          listModel.addElement(pr);
                            
                        }
            
        }catch(Exception exception){
            JOptionPane.showMessageDialog(frame,"Loading Error"+ exception.getMessage());
        }
        cusmtomrenderer c=new cusmtomrenderer();
        
        list.setCellRenderer(c);
        list.setModel(listModel);
        
    }
    
    public static void listProgramsInJList(JList list,JFrame frame, ArrayList<program> secili_p,String sql_ara){
        DefaultListModel listModel=new DefaultListModel();
        listModel.clear();
        secili_p.clear();
        try{
        
            Connection myConn=connectdb();
			PreparedStatement preparedStatement= (PreparedStatement) (Statement) myConn.prepareStatement("SELECT * FROM programtablosu WHERE program_adı LIKE '"+sql_ara+"%'");
			ResultSet resultSet= preparedStatement.executeQuery();
                         String dosya_yolu;
                         
                        while(resultSet.next()){
//                            System.out.println(""+resultSet.getString("program_adı"));
                           
                            dosya_yolu="src\\filmkapakları2\\"+resultSet.getInt("program_id")+".jpg";
                            program pr=new program(resultSet.getString("program_adı"));
                            pr.photo=new ImageIcon (dosya_yolu);  
                            
                            
                            secili_p.add(pr);
                            listModel.addElement(pr);
                            
                        }
            
        }catch(Exception exception){
            JOptionPane.showMessageDialog(frame,"Loading Error"+ exception.getMessage());
        }
        cusmtomrenderer c=new cusmtomrenderer();
        
        list.setCellRenderer(c);
        list.setModel(listModel);
        
    }
    
    
    
    
    
}
