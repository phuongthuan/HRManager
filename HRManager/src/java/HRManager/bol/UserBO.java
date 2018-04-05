/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.bol;

import HRManager.dal.DAO;
import HRManager.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBO {
    
    

    /**
     * Check username and password.
     * 
     * @param u
     * @return 
     */
    public boolean authorization(User u) {
        try {
            String sql = "select * from users where username = ? and userpassword = ?";
            Connection conn = DAO.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getUserPassword());

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            }
        } catch (Exception ex) {
            //SinhNX_Lib.getLogger(this.getClass().getName()).error("Can't get data from database. " + ex.toString());
            return false;
        }
        return true;
    }
}
