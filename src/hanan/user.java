/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class user {
    
     public void insertUpdateDeleteUser(char operation, Integer id, String fname, 
            String lname, String sex, String phone, String address){
         
         Connection con = MyConection.getConnection();
            PreparedStatement ps;
        if(operation == 'i') //for insert
        { 
            try 
               {
                   ps = con.prepareStatement("INSERT INTO user(first_name,last_name,sex,phone,address) VALUES (?,?,?,?,?)");
                   ps.setString(1, fname);
                   ps.setString(2, lname);
                   ps.setString(3, sex);
                   ps.setString(4, phone);
                   ps.setString(5, address);
                   
                    if(ps.executeUpdate() > 0){
                       JOptionPane.showMessageDialog(null, "New User Added");
                     }
                } 
            catch (SQLException ex) 
                {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE,null,ex);
                }
        
        }
    
        if(operation == 'u') //for update
        { 
            try 
               {
                   ps = con.prepareStatement("UPDATE `user` SET `first_name`= ?,`last_name`= ?,`sex`= ?,`phone`= ?,`address`= ? WHERE `id` = ?");
                   ps.setString(1, fname);
                   ps.setString(2, lname);
                   ps.setString(3, sex);
                   ps.setString(4, phone);
                   ps.setString(5, address);
                   ps.setInt(6, id);
                   
                    if(ps.executeUpdate() > 0){
                       JOptionPane.showMessageDialog(null, "User Data Updated");
                     }
                } 
            catch (SQLException ex) 
                {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE,null,ex);
                }
        
        }
        
         if(operation == 'd') //for delete
        { 
            try 
               {
                   ps = con.prepareStatement("DELETE FROM `user` WHERE `id`= ?");
                   ps.setInt(1, id);
                   
                    if(ps.executeUpdate() > 0){
                       JOptionPane.showMessageDialog(null, "Student Deleted");
                     }
                } 
            catch (SQLException ex) 
                {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE,null,ex);
                }
        
        }
    
    }
      
      public void Fill_User_Table(JTable table ,String ValueToSearch){
         Connection con = MyConection.getConnection();
            PreparedStatement ps;
         try{
           ps = con.prepareStatement("SELECT * FROM `user` WHERE CONCAT(`first_name`,`last_name`,`sex`,`phone`,`address`) LIKE ?");
            ps.setString(1,"%"+ValueToSearch+"%");
           ResultSet rs =ps.executeQuery();        
           DefaultTableModel model =(DefaultTableModel) table.getModel();
           Object[] row;
           while(rs.next())
                {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);
                }
            } catch (SQLException ex) {
              Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
