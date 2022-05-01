
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author HP15DA0023LA
 */
public class frmMain extends javax.swing.JFrame {
    ArrayList<Pagina> listaPulsaciones = new ArrayList(); // estos son los valores en la tabla de marcos (ram) basados en pulsaciones
    Integer marco=0;
    DefaultTableModel dtm = new DefaultTableModel();
    Pulsacion pul = new Pulsacion();
    int cont = 0;
    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
        dtm.addColumn("Nombre");
        dtm.addColumn("Cantidad");
        for (Integer i = 0; i < 5; i++) {
            Integer cantidad = i+1;
            String[] info = new String[2];
            info[0] = "Marco " + cantidad.toString();
            info[1]="";
            dtm.addRow(info);
        }
        this.jTable2.setModel(dtm);
        pul.encender();
        pul.start();
    }
    public class Pulsacion extends Thread{
         public Boolean encendido = false;
        
        public void apagar() {
            this.encendido = false;
        }
        
        public void encender() {
            this.encendido = true;
        }
        @Override
        public void run(){
            while(true){
                if(encendido){
                    Pagina p = new Pagina();
                    p.setMarco(marco);
                    int marcoAux = marco;
                    char yes = 'A';
                    char no = 'F';
                    marco++;
                    
                    if(marco==5){
                        marco=0;
                    }
                    
                    boolean siEstaba=false; // primero verifico si estaba en nuestra lista que lleva los marcos
                    
                    for(Integer i=0; i<listaPulsaciones.size();i++){
                        if(Objects.equals(listaPulsaciones.get(i).getValor(), p.getValor())){
                            siEstaba=true;
                            System.out.println("Si estaba el "+ listaPulsaciones.get(i).getValor() + " En posición " + listaPulsaciones.get(i).getMarco());
                            marco=marcoAux; //aquí se mandarían los aciertos  
                        }
                        else{
                            //Si tiene fallo   
                        }
                    }
                    
                    if (siEstaba == false) {
                        if (listaPulsaciones.size() < 5) {
                            listaPulsaciones.add(p); // si todavía hay espacio en mi ram lo meto
                        }
                        else{
                            //aquí es donde debo sacar de mi lista y meterlo donde lo saqué (tomar en cuenta...
                            //que debo obtener el marco del que voy a sacar antes de sacarlo, porque ese marco se le va a agregar
                            // al nuevo que ingrese
                            Integer marcoAconservar=listaPulsaciones.get(0).getMarco();
                            p.setMarco(marcoAconservar);
                            listaPulsaciones.remove(0);
                            listaPulsaciones.add(p);
                            dtm.setValueAt(listaPulsaciones.get(4).getValor(), listaPulsaciones.get(4).getMarco(), 1);
                            
                        }
                    }
                    
                    //Validación en tabla si es acierto o Fallo
                    
                    if (siEstaba == true){
                        if(cont <=7){
                        validacion.setValueAt(yes, 0, cont);
                        }
                        else{
                            eliminar2();
                            validacion.setValueAt(yes, 0, 0);
                            cont = 0;
                        }
                    }
                    else if (siEstaba == false){
                        if(cont <=7){
                        validacion.setValueAt(no, 0, cont);
                        }
                        else{
                            eliminar2();
                            validacion.setValueAt(no, 0, 0);
                            cont = 0;
                        }
                    }
                    cont++;
                    //System.out.println("llego a esta parte ------------- " + cont);
                    
                    //System.out.println("Tamaño "+listaPulsaciones.size());
                    for (Integer i = 0; i < listaPulsaciones.size(); i++) {
                        dtm.setValueAt(listaPulsaciones.get(i).getValor(), listaPulsaciones.get(i).getMarco(), 1);
                    }
                    
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    public void eliminar(){
        DefaultTableModel tb = (DefaultTableModel) jTable2.getModel();
        int a = jTable2.getRowCount()-1;
        for (int i = a; i >= 0; i--) {          
        tb.removeRow(tb.getRowCount()-1);
        }
        //cargaTicket();
    }
    public void eliminar2(){
        validacion.setValueAt("", 0, 0);
        validacion.setValueAt("", 0, 1);
        validacion.setValueAt("", 0, 2);
        validacion.setValueAt("", 0, 3);
        validacion.setValueAt("", 0, 4);
        validacion.setValueAt("", 0, 5);
        validacion.setValueAt("", 0, 6);
        validacion.setValueAt("", 0, 7);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        validacion = new javax.swing.JTable();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(7).setResizable(false);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setText("Estado");

        validacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "---*---", "---*---", "---*---", "---*---", "---*---", "---*---", "---*---", "---*---"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(validacion);
        if (validacion.getColumnModel().getColumnCount() > 0) {
            validacion.getColumnModel().getColumn(0).setResizable(false);
            validacion.getColumnModel().getColumn(1).setResizable(false);
            validacion.getColumnModel().getColumn(2).setResizable(false);
            validacion.getColumnModel().getColumn(3).setResizable(false);
            validacion.getColumnModel().getColumn(4).setResizable(false);
            validacion.getColumnModel().getColumn(5).setResizable(false);
            validacion.getColumnModel().getColumn(6).setResizable(false);
            validacion.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable validacion;
    // End of variables declaration//GEN-END:variables
}
