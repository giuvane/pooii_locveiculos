/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giuvane
 */
public class Conexao {
   
    private volatile static Connection uniqueInstance;
    static final String DATABASE_URL = "jdbc:mysql://localhost/locadora_veiculos";
    static final String USER = "root";
    static final String PASS = "12345";
    //public static Connection connection = null; // manages connection
 
    private Conexao() {}

    public static Connection getInstance() {
            if (uniqueInstance == null) {
                    synchronized (Conexao.class) {
                            if (uniqueInstance == null) {
                                   
                                try {
                                    uniqueInstance = DriverManager.getConnection(DATABASE_URL, USER, PASS );
                                } catch (SQLException ex) {
                                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                     
                               
                            }
                    }
            }
            return uniqueInstance;
    }
}

