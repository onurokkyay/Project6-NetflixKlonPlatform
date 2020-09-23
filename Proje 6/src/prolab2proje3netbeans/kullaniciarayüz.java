/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prolab2proje3netbeans;

import com.mysql.cj.protocol.Resultset;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.System.currentTimeMillis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import static prolab2proje3netbeans.Prolab2proje3netbeans.connectdb;
import static prolab2proje3netbeans.filmarayüz.baslangic_süresi;
import static prolab2proje3netbeans.filmarayüz.film_zamancekme;
import static prolab2proje3netbeans.filmarayüz.izleme_süresi;
import static prolab2proje3netbeans.filmarayüz.jComboBox2;
import static prolab2proje3netbeans.filmarayüz.kullanıcıfilm_kontrol;
import static prolab2proje3netbeans.filmarayüz.prg_id;
import static prolab2proje3netbeans.filmarayüz.time;
import static prolab2proje3netbeans.kullanicigiris.kullanıcı_arayüz;




/**
 *
 * @author melih
 */
public class kullaniciarayüz extends javax.swing.JFrame {

    /**
     * Creates new form kullaniciarayüz
     */
    public static String email="";
    public static int prog_id;
    public static ArrayList <program> programlar1 =new  ArrayList();
    public static ArrayList <program> programlar2 =new  ArrayList();
    public static ArrayList <program> programlar3 =new  ArrayList();
    public static ArrayList <program> secili_program =new  ArrayList();
    public static int []tip_id=new int [3];
    public static Prolab2proje3netbeans kullanıcı=new Prolab2proje3netbeans();
    public static ArrayList <program> secili_program2=new ArrayList(); 
    
    public kullaniciarayüz(String email) {
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.email=email;
        this.comboboxdoldur();
        ArrayList <tip> tip_list=new ArrayList();
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			ResultSet myRs=  ((java.sql.Statement) myStat).executeQuery("SELECT * FROM kullanıcıtablosu WHERE kullanıcı_email='"+this.email+"'");
                        
                        while(myRs.next()){
                            jLabel3.setText("Hoş Geldin "+myRs.getString("kullanıcı_adı"));
                            //System.out.println(""+id);
                        }
                        
                     
		}catch(Exception e){
			e.printStackTrace();
                        
                        
		}
        
        
        
       
       
       jTextField4.addKeyListener(new KeyAdapter(){
           @Override
           public void keyPressed(KeyEvent e){
               
                  if(e.getKeyCode()==KeyEvent.VK_ENTER){
                 
                 String sql_arama=jTextField4.getText();
                 
                 operations opera=new operations(sql_arama);
                  opera.listProgramsInJList(jList1, kullanicigiris.kullanıcı_arayüz,secili_program2,sql_arama);
                 
                 
                 
                 
                 
                 
                  }
                  
                  
              }
              
          }); 
       
       
        int id;
        
        
        id=kullanıcı.kullanıcıbulemail(email);
//        System.out.println("id: "+id);
        tip_id=kullanıcı.tip_tespit(id, tip_id);
//        for(int i=0;i<3;i++){
//            System.out.println("--> "+tip_id[i]);
//        }
        for (int i = 0; i <3; i++) {
            tip a=new tip();
            a=kullanıcı.belirleme(a, tip_id[i]);
            tip_list.add(a);
        }
        jTextField2.setText(tip_list.get(0).tip_adı);
        jTextField5.setText(tip_list.get(1).tip_adı);
        jTextField6.setText(tip_list.get(2).tip_adı);
//        for (int i = 0; i < 3; i++) {
//            System.out.println("==>"+tip_list.get(i).tip_adı);
//        }
        
        programlar1=kullanıcı.favoriprogramlar(programlar1, tip_id[0]);
        programlar2=kullanıcı.favoriprogramlar(programlar2, tip_id[1]);
        programlar3=kullanıcı.favoriprogramlar(programlar3, tip_id[2]);

        String dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar1.get(0).adı)+".jpg";    
        jButton1.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar1.get(1).adı)+".jpg";
        jButton2.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar2.get(0).adı)+".jpg";
        jButton3.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar2.get(1).adı)+".jpg";
        jButton4.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar3.get(0).adı)+".jpg";
        jButton5.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar3.get(1).adı)+".jpg";
        jButton6.setIcon(new ImageIcon(dosya_yolu));
        
        
        
        
        operations opera=new operations(1);
        opera.listProgramsInJList(programRecordList, this,secili_program);
//        for (int i = 0; i <secili_program.size(); i++) {
//            System.out.println("-->"+secili_program.get(i).adı);
//        }
        
        
        
       
        
       
        
        
        
     
       
    }
 public static void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }
    
    
    public static void arayüzü_güncelle(){
        programlar1=kullanıcı.favoriprogramlar(programlar1, tip_id[0]);
        programlar2=kullanıcı.favoriprogramlar(programlar2, tip_id[1]);
        programlar3=kullanıcı.favoriprogramlar(programlar3, tip_id[2]);
        
//        System.out.println("pr1"+programlar1.get(0).adı+" "+programlar1.get(1).adı);
//        System.out.println("pr2"+programlar2.get(0).adı+" "+programlar2.get(1).adı);
//        System.out.println("pr3"+programlar3.get(0).adı+" "+programlar3.get(1).adı);
        
        String dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar1.get(0).adı)+".jpg";    
        jButton1.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar1.get(1).adı)+".jpg";
        jButton2.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar2.get(0).adı)+".jpg";
        jButton3.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar2.get(1).adı)+".jpg";
        jButton4.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar3.get(0).adı)+".jpg";
        jButton5.setIcon(new ImageIcon(dosya_yolu));
        dosya_yolu="src\\filmkapakları\\"+program_idbul(programlar3.get(1).adı)+".jpg";
        jButton6.setIcon(new ImageIcon(dosya_yolu));
        
    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        programRecordList = new javax.swing.JList<>();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Netflix");
        setMinimumSize(new java.awt.Dimension(1250, 1050));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(1250, 1050));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 1050));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 90, -1));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(0, 0, 0));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setText("Film Türüne Göre Ara");
        jTextField3.setBorder(null);
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 120, -1));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Sevdiğin Türlerden En Sevilenler (#1,#2)");
        jTextField1.setBorder(null);
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 13, 340, -1));

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 173, 189));

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 170, 190));

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, 170, 190));

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 280, 180, 190));

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 530, 180, 190));

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 530, 180, 190));

        programRecordList.setBackground(new java.awt.Color(255, 51, 51));
        programRecordList.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                programRecordListMouseMoved(evt);
            }
        });
        programRecordList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                programRecordListMouseClicked(evt);
            }
        });
        programRecordList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                programRecordListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(programRecordList);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 238, -1));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 107, -1));

        jList1.setBackground(new java.awt.Color(255, 51, 51));
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 238, 180));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-google-web-search-24.png"))); // NOI18N
        jLabel1.setText("Ara");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 54, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-netflix-384.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 770, 420, 170));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 60, 20));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 430, 50));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 0));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setBorder(null);
        jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 210, -1));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(0, 0, 0));
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setBorder(null);
        jTextField5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 210, -1));

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(0, 0, 0));
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setBorder(null);
        jTextField6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 610, 210, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
        operations opera=new operations(jComboBox1.getSelectedIndex()+1);
        opera.listProgramsInJList(programRecordList, this,secili_program);
        
        
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int prg_id=program_idbul(programlar1.get(0).adı);
        
        filmarayüz f_arayüz=new filmarayüz(prg_id,email);
        f_arayüz.setVisible(true);
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void programRecordListMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_programRecordListMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_programRecordListMouseMoved

    private void programRecordListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_programRecordListValueChanged
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_programRecordListValueChanged

    private void programRecordListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_programRecordListMouseClicked
        // TODO add your handling code here:
       
        
            
        if(evt.getClickCount()==2){
            int prg_id=program_idbul(secili_program.get(programRecordList.getSelectedIndex()).adı);
//            System.out.println("jlist "+prg_id+" "+secili_program.get(programRecordList.getSelectedIndex()).adı);
//            System.out.println("jlist "+prg_id);
            filmarayüz f_arayüz=new filmarayüz(prg_id,email);
            f_arayüz.setVisible(true);
        }
        
        
    }//GEN-LAST:event_programRecordListMouseClicked
     public static int program_idbul(String program_adı){
        try {
			Connection myConn=connectdb();
			PreparedStatement myStat= (PreparedStatement) myConn.prepareStatement("SELECT * FROM programtablosu WHERE program_adı=?");
                        myStat.setString(1, program_adı);
			ResultSet myRs=  myStat.executeQuery();
                        
                        
                        int p_id =0;
                        while(myRs.next()){
                            p_id=myRs.getInt("program_id");
                            
                            
                        }
                        return p_id;
                     
		}catch(Exception e){
			e.printStackTrace();
                        
                        
		}
        
        return -1;
        
        
    }
    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int prg_id=program_idbul(programlar1.get(1).adı);
        filmarayüz f_arayüz=new filmarayüz(prg_id,email);
        f_arayüz.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        int prg_id=program_idbul(programlar2.get(0).adı);
        filmarayüz f_arayüz=new filmarayüz(prg_id,email);
        f_arayüz.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int prg_id=program_idbul(programlar2.get(1).adı);
        filmarayüz f_arayüz=new filmarayüz(prg_id,email);
        f_arayüz.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int prg_id=program_idbul(programlar3.get(0).adı);
        filmarayüz f_arayüz=new filmarayüz(prg_id,email);
        f_arayüz.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        int prg_id=program_idbul(programlar3.get(1).adı);
        filmarayüz f_arayüz=new filmarayüz(prg_id,email);
        f_arayüz.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            int prg_id=program_idbul(secili_program2.get(jList1.getSelectedIndex()).adı);
//            System.out.println("jlist "+prg_id+" "+secili_program2.get(jList1.getSelectedIndex()).adı);
            filmarayüz f_arayüz=new filmarayüz(prg_id,email);
            f_arayüz.setVisible(true);
        }
    }//GEN-LAST:event_jList1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(kullaniciarayüz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kullaniciarayüz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kullaniciarayüz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kullaniciarayüz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new kullaniciarayüz(email).setVisible(true);
            }
        });
    }
    public void comboboxdoldur(){
         try {
			Connection conn=null;
                        PreparedStatement pst=null;
                        ResultSet rs=null;
                        conn=connectdb();
                        pst=conn.prepareStatement("SELECT * FROM tiptablosu");
                        rs=pst.executeQuery();
                        
                        while(rs.next()){
                            jComboBox1.addItem(rs.getString("tip_adı"));
                        }
                        
                        
                     
		}catch(Exception e){
			e.printStackTrace();
                        
                        
                        
		}
         
     }
    @Override
    public synchronized void addWindowListener(WindowListener l) {
        super.addWindowListener(l); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * @param args the command line arguments
     */
    protected void processWindowEvent(WindowEvent e) {
            super.processWindowEvent(e);
            
            if(baslangic_süresi!=0){
            if(e.getID() == WindowEvent.WINDOW_CLOSING) {
                int k_id;
        String sql_time = null;
        
        Prolab2proje3netbeans izleyici=new Prolab2proje3netbeans();
        k_id=izleyici.kullanıcıbulemail(email);
        
        
        izleme_süresi=currentTimeMillis()-baslangic_süresi;
       
        
        jButton1.setVisible(true);
        jButton2.setVisible(false);
        try {
             sql_time=time(izleme_süresi);
        
        } catch (ParseException ex) {
            Logger.getLogger(filmarayüz.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(kullanıcıfilm_kontrol(k_id,prg_id)==0){
        try {
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
			int myRs=  ((java.sql.Statement) myStat).executeUpdate("INSERT INTO kullanıcıprogramtablosu values(CURRENT_TIMESTAMP(),'"+sql_time+"'"+",null,'"+(jComboBox2.getSelectedIndex()+1)+"','"+k_id+"','"+prg_id+"')");
                      
		}catch(Exception ex2){
			ex2.printStackTrace();
		}
        }else{
            
                long izleme_süresi2=currentTimeMillis()-baslangic_süresi;
                Time güncel_süre;
                
                güncel_süre=film_zamancekme(k_id,prg_id);
                long süre = güncel_süre.getTime();
                süre=süre+izleme_süresi2;
                String sql_time2=null;
                try {
                     sql_time2=time(süre);
                     
        
                } catch (ParseException ex) {
                     Logger.getLogger(filmarayüz.class.getName()).log(Level.SEVERE, null, ex);
                     
                     
                }

                

            
            try {
                
			Connection myConn=connectdb();
			Statement myStat= (Statement) myConn.createStatement();
                        
			int myRs=  ((java.sql.Statement) myStat).executeUpdate("UPDATE kullanıcıprogramtablosu SET izleme_tarihi=CURRENT_TIMESTAMP(),izleme_süresi='"+sql_time2+"',kaldığı_bölüm='"+(jComboBox2.getSelectedIndex()+1)+"' WHERE kullanıcı_id='"+k_id+"' and program_id='"+prg_id+"'");
                      
		}catch(Exception ex2){
			ex2.printStackTrace();
		}
            
            
            
        }
                
                
                
                
                
                
                
                
                
                
                
                System.exit(0);
            }
         }
        }
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JButton jButton4;
    public static javax.swing.JButton jButton5;
    public static javax.swing.JButton jButton6;
    public javax.swing.JComboBox<String> jComboBox1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JList<String> jList1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JList<String> programRecordList;
    // End of variables declaration//GEN-END:variables
}
