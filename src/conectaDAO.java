
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            // URL de conexão corrigida
            String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "Ybr/*1812"; 
            
            conn = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
