
import java.awt.Color;
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
    ArrayList<Integer> listaReferencias = new ArrayList();
    ArrayList<Pagina> listaIngresados = new ArrayList();
    Integer marco=0;
    DefaultTableModel dtm = new DefaultTableModel();
    Pulsacion pul = new Pulsacion();
    int cont=0;
    int cont2 = 0;
    /**
     * Creates new form frmMain
     */
    public frmMain() {
        this.setTitle("FIFO");
        this.getContentPane().setBackground(Color.pink);
        initComponents();
        dtm.addColumn("Nombre");
        dtm.addColumn("Página");
        for (Integer i = 0; i < 4; i++) {
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
                    if(cont >7){
                        cont=0;
                    }else{
                    Pagina p = new Pagina();
                    p.setMarco(marco);
                    int marcoAux = marco; 
                    char yes = 'A';
                    char no = 'F';
                    marco++;
                    //Agregar a la tabla de referencias
                    switch (cont) {
                        case 0 -> Titulo1.setText(""+p.getValor());
                        case 1 -> Titulo2.setText(""+p.getValor());
                        case 2 -> Titulo3.setText(""+p.getValor());
                        case 3 -> Titulo4.setText(""+p.getValor());
                        case 4 -> Titulo5.setText(""+p.getValor());
                        case 5 -> Titulo6.setText(""+p.getValor());
                        case 6 -> Titulo7.setText(""+p.getValor());
                        default -> Titulo8.setText(""+p.getValor());
                    };
                    cont++;
                    //Referencias.getColumn(Titulo).setHeaderValue(p.getValor());
                    //System.out.println("*******************************"+ Titulo+ "Tamaño Ref= "+ listaReferencias.size()+ "NUM= "+p.getValor());
                    
                        
                    
                    if(marco==4){
                        marco=0;
                    }
                    boolean siEstaba=false; // primero verifico si estaba en nuestra lista que lleva los marcos
                    for(Integer i=0; i<listaPulsaciones.size();i++){
                        if(Objects.equals(listaPulsaciones.get(i).getValor(), p.getValor())){
                            siEstaba=true;
                            marco=marcoAux; //aquí se mandarían los aciertos
                        }
                        else{
                            //aquí se mandarías los fallos
                        }
                    }
                    if (siEstaba == false) {
                        if (listaPulsaciones.size() < 4) {
                            listaPulsaciones.add(p); // si todavía hay espacio en mi ram lo meto
                            listaIngresados.add(p);
                        }
                        else{
                            //aquí es donde debo sacar de mi lista y meterlo donde lo saqué (tomar en cuenta...
                            //que debo obtener el marco del que voy a sacar antes de sacarlo, porque ese marco se le va a agregar
                            // al nuevo que ingrese
                            Integer marcoAconservar = listaIngresados.get(0).getMarco();
                            listaIngresados.remove(0);
                            listaIngresados.add(p);
                            p.setMarco(marcoAconservar);
                            listaPulsaciones.get(marcoAconservar).setValor(p.getValor());
                            listaPulsaciones.get(marcoAconservar).setMarco(p.getMarco());
                            dtm.setValueAt(listaPulsaciones.get(marcoAconservar).getValor(), listaPulsaciones.get(marcoAconservar).getMarco(), 1);
                        }
                    }
                    for(int i= 0; i< listaPulsaciones.size(); i++){
                            Object valor = listaPulsaciones.get(i).getValor();
                            int colum= cont-2;
                            Referencias.setValueAt(valor, i, cont-1);
                    }
                    
                    if (siEstaba == true){
                        if(cont2 <=7){
                        validacion.setValueAt(yes, 0, cont2);
                        }
                        else{
                            eliminar2();
                            validacion.setValueAt(yes, 0, 0);
                            cont2 = 0;
                        }
                    }
                    else if (siEstaba == false){
                        if(cont2 <=7){
                        validacion.setValueAt(no, 0, cont2);
                        }
                        else{
                            eliminar2();
                            validacion.setValueAt(no, 0, 0);
                            cont2 = 0;
                        }
                    }
                    
                    cont2++;
                    
                    for (Integer i = 0; i < listaPulsaciones.size(); i++) {
                        dtm.setValueAt(listaPulsaciones.get(i).getValor(), listaPulsaciones.get(i).getMarco(), 1);
                    }
                    
                    /*
                    for(int i=0; i<listaPulsaciones.size(); i++){
                        System.out.println(""+ listaPulsaciones.get(i).getValor());
                    }
                        System.out.println("LISTA INGRESADOS");
                    for(int i=0; i<listaIngresados.size(); i++){
                        System.out.println(""+ listaIngresados.get(i).getValor());
                    }*/
                    }
                   
                }
                try {
                    Thread.sleep(3000);
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
        Referencias = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Titulo1 = new javax.swing.JLabel();
        Titulo2 = new javax.swing.JLabel();
        Titulo3 = new javax.swing.JLabel();
        Titulo4 = new javax.swing.JLabel();
        Titulo6 = new javax.swing.JLabel();
        Titulo7 = new javax.swing.JLabel();
        Titulo8 = new javax.swing.JLabel();
        Titulo5 = new javax.swing.JLabel();
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
        setBackground(new java.awt.Color(255, 204, 204));
        setResizable(false);

        Referencias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Referencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ));
        Referencias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(Referencias);
        if (Referencias.getColumnModel().getColumnCount() > 0) {
            Referencias.getColumnModel().getColumn(0).setResizable(false);
            Referencias.getColumnModel().getColumn(1).setResizable(false);
            Referencias.getColumnModel().getColumn(2).setResizable(false);
            Referencias.getColumnModel().getColumn(3).setResizable(false);
            Referencias.getColumnModel().getColumn(4).setResizable(false);
            Referencias.getColumnModel().getColumn(5).setResizable(false);
            Referencias.getColumnModel().getColumn(6).setResizable(false);
            Referencias.getColumnModel().getColumn(7).setResizable(false);
        }

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("                                                                                   Pulsaciones de reloj");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("            1                      2                        3                      4                       5                        6                       7                         8");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("                                                                                        Referencias");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel5.setText("1");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel6.setText("2");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel7.setText("3");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setBackground(new java.awt.Color(255, 153, 102));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("     Marco");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel9.setText("4");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo1.setText("           0");
        Titulo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo2.setText("           0");
        Titulo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo3.setText("           0");
        Titulo3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo4.setText("           0");
        Titulo4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo6.setText("           0");
        Titulo6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo7.setText("           0");
        Titulo7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo8.setText("           0");
        Titulo8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Titulo5.setText("           0");
        Titulo5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Titulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Titulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Titulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Titulo7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Titulo8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel9)))
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1)))
                        .addGap(116, 116, 116))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Titulo6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Titulo1)
                                        .addComponent(Titulo2)
                                        .addComponent(Titulo3)
                                        .addComponent(Titulo4)
                                        .addComponent(Titulo7)
                                        .addComponent(Titulo8)
                                        .addComponent(Titulo5)))
                                .addGap(96, 96, 96))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
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
    private javax.swing.JTable Referencias;
    private javax.swing.JLabel Titulo1;
    private javax.swing.JLabel Titulo2;
    private javax.swing.JLabel Titulo3;
    private javax.swing.JLabel Titulo4;
    private javax.swing.JLabel Titulo5;
    private javax.swing.JLabel Titulo6;
    private javax.swing.JLabel Titulo7;
    private javax.swing.JLabel Titulo8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable validacion;
    // End of variables declaration//GEN-END:variables
}
