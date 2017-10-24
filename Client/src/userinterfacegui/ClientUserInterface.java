/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacegui;

/**
 *
 * @author loveboat
 */
public class ClientUserInterface extends javax.swing.JFrame
                                 implements userinterface.Userinterface {

    /**
     * Creates new form ClientUserInterface
     */
    public ClientUserInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectToServerButton = new javax.swing.JButton();
        disconnectFromServerButton = new javax.swing.JButton();
        getTimeButton = new javax.swing.JButton();
        led1CheckBox = new javax.swing.JCheckBox();
        led2CheckBox = new javax.swing.JCheckBox();
        led3CheckBox = new javax.swing.JCheckBox();
        led4CheckBox = new javax.swing.JCheckBox();
        scrollPane = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        outputLabel = new javax.swing.JLabel();
        btn1StateButton = new javax.swing.JButton();
        btn2StateButton = new javax.swing.JButton();
        btn3StateButton = new javax.swing.JButton();
        btn1Label = new javax.swing.JLabel();
        btn2Label = new javax.swing.JLabel();
        btn3Label = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        connectToServerButton.setBackground(new java.awt.Color(131, 254, 71));
        connectToServerButton.setText("Connect");

        disconnectFromServerButton.setBackground(new java.awt.Color(254, 163, 34));
        disconnectFromServerButton.setText("Disconnect");

        getTimeButton.setText("Get Time");

        led1CheckBox.setText("LED1 ON");

        led2CheckBox.setText("LED2 ON");

        led3CheckBox.setText("LED3 ON");

        led4CheckBox.setText("LED4 ON");

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        scrollPane.setViewportView(outputTextArea);

        outputLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        outputLabel.setText("Output");

        btn1StateButton.setText("BTN1");

        btn2StateButton.setText("BTN2");

        btn3StateButton.setText("BTN3");

        btn1Label.setText("Get BTN1 state");

        btn2Label.setText("Get BTN2 state");

        btn3Label.setText("Get BTN3 state");

        quitButton.setBackground(new java.awt.Color(254, 81, 70));
        quitButton.setText("Quit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(led2CheckBox)
                            .addComponent(led3CheckBox)
                            .addComponent(led4CheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(led1CheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(connectToServerButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(disconnectFromServerButton))
                            .addComponent(getTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn1StateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn1Label))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btn2StateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn3StateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn2Label)
                                    .addComponent(btn3Label))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(outputLabel)
                                .addGap(230, 230, 230))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(quitButton)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {connectToServerButton, getTimeButton, quitButton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn1StateButton, btn3StateButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectToServerButton)
                    .addComponent(disconnectFromServerButton)
                    .addComponent(outputLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(getTimeButton)
                        .addGap(18, 18, 18)
                        .addComponent(led1CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(led2CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(led3CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(led4CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn1StateButton)
                            .addComponent(btn1Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn2StateButton)
                            .addComponent(btn2Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn3StateButton)
                            .addComponent(btn3Label)))
                    .addComponent(scrollPane))
                .addGap(18, 18, 18)
                .addComponent(quitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ClientUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientUserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ClientUserInterface().setVisible(true);
        });
    }


    /**
     * Update the user interface with a new message.
     * @param message The message to be appended to the user message display.
     */
    @Override
    public void update(String message) {
        // Append message to output text area
        this.outputTextArea.append(message + "\n");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn1Label;
    private javax.swing.JButton btn1StateButton;
    private javax.swing.JLabel btn2Label;
    private javax.swing.JButton btn2StateButton;
    private javax.swing.JLabel btn3Label;
    private javax.swing.JButton btn3StateButton;
    private javax.swing.JButton connectToServerButton;
    private javax.swing.JButton disconnectFromServerButton;
    private javax.swing.JButton getTimeButton;
    private javax.swing.JCheckBox led1CheckBox;
    private javax.swing.JCheckBox led2CheckBox;
    private javax.swing.JCheckBox led3CheckBox;
    private javax.swing.JCheckBox led4CheckBox;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JButton quitButton;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}