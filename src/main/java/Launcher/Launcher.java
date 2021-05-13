/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Launcher;

/**
 * @author Lucas Roberto
 * 
 */
import Pong.Pong;
import Cave.main.Game;

public class Launcher extends javax.swing.JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JComboBox<String> DifComboBox;
    private javax.swing.JLabel DifLabel;
    private javax.swing.JComboBox<String> GameComboBox;
    private javax.swing.JLabel JogoLabel;
    private javax.swing.JButton StartButton;    

    
    public Launcher() {
        initComponents();
    }
                          
    private void initComponents() {

        GameComboBox = new javax.swing.JComboBox<>();
        StartButton = new javax.swing.JButton();
        DifComboBox = new javax.swing.JComboBox<>();
        JogoLabel = new javax.swing.JLabel();
        DifLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        GameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pong", "Cave Escape" }));
        GameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameComboBoxActionPerformed(evt);
            }
        });

        StartButton.setText("iniciar");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        DifComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facil", "Medio", "Dificil" }));

        JogoLabel.setText("Jogo");

        DifLabel.setText("Dificuldade");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DifLabel)
                    .addComponent(DifComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(JogoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(DifLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DifComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(StartButton)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }                       

    private void GameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
    }                                            

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(GameComboBox.getSelectedItem().equals("Pong")){
            if(DifComboBox.getSelectedItem().equals("Facil")){
            	Pong pong = new Pong(1.4,0.053);
                pong.StartPong();
            }else if(DifComboBox.getSelectedItem().equals("Medio")){
            	Pong pong = new Pong(2.0,0.09);
                pong.StartPong();
            }else if(DifComboBox.getSelectedItem().equals("Dificil")){
            	Pong pong = new Pong(2.6,0.13);
                pong.StartPong();
            }
        }else if(GameComboBox.getSelectedItem().equals("Cave Escape")){
            if(DifComboBox.getSelectedItem().equals("Facil")){
            	Game cave = new Cave.main.Game(25,2,0.3);
                cave.startCave();
            }else if(DifComboBox.getSelectedItem().equals("Medio")){
            	Game cave = new Cave.main.Game(15,4,0.5);
            	cave.startCave();
            }else if(DifComboBox.getSelectedItem().equals("Dificil")){
            	Game cave = new Cave.main.Game(10,8,0.7);
            	cave.startCave();
            }
        }
    }                                           


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Launcher().setVisible(true);
            }
        });
    }
                
}
