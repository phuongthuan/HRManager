/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HRManager.bol;

import HRManager.ConvertData;
import HRManager.dal.DAO;
import HRManager.entities.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeBO {

    /**
     *
     * Add new Employee to database
     */
    public boolean add(Employee e) {
        
        String sql = "INSERT INTO Employees(LastName, FirstName, BirthDate, HireDate, Address, City, Country, " +
                    "HomePhone, Mobile, Email, Note) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            
        try {
            ConvertData cv = new ConvertData();
            Connection conn = DAO.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, e.getLastName());
            ps.setString(2, e.getFirstName());
            ps.setString(3, cv.date2string(e.getBirthDate(), HRManager.ValidData.EN_DATE));
            ps.setString(4, cv.date2string(e.getHireDate(), HRManager.ValidData.EN_DATE));
            ps.setString(5, e.getAddress());
            ps.setString(6, e.getCity());
            ps.setString(7, e.getCountry());
            ps.setString(8, e.getHomePhone());
            ps.setString(9, e.getMobile());
            ps.setString(10, e.getEmail());
            ps.setString(11, e.getNote());
            
            return ps.executeUpdate() > 0;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    /**
     * Delete a Employee.
     * 
     * @param id
     * @return 
     */
    public static boolean delete(Employee e) {
        try {
            Connection conn = DAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Employees WHERE id=?");
            ps.setInt(1, e.getEmployeeID());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     *
    update old Employee  by new Employee into database
     */
    public boolean edit(Employee e) {
        
        String sql = "UPDATE Employees SET LastName=?, FirstName=?, BirthDate=?, HireDate=?, Address=?, City=?, Country=?, HomePhone=?, Mobile=?, Email=?, PhotoPath=?, Note=? "
                + "WHERE EmployeeId=?";
            
        try {
            ConvertData cv = new ConvertData();
            Connection con = DAO.getConnection();
            PreparedStatement ps = con.prepareCall(sql);
            
            ps.setString(1, e.getLastName());
            ps.setString(2, e.getFirstName());
            ps.setString(3, cv.date2string(e.getBirthDate(), HRManager.ValidData.EN_DATE));
            ps.setString(4, cv.date2string(e.getHireDate(), HRManager.ValidData.EN_DATE));
            ps.setString(5, e.getAddress());
            ps.setString(6, e.getCity());
            ps.setString(7, e.getCountry());
            ps.setString(8, e.getHomePhone());
            ps.setString(8, e.getMobile());
            ps.setString(9, e.getEmail());
            ps.setString(10, e.getPhotoPath());
            ps.setString(11, e.getNote());
            
            return ps.executeUpdate() > 0;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    /**
     *
     * Lay ve tat ca cac Employee co trong CSDL.
     */
    public Employee[] select() {

        try {
            Vector vE = new Vector();

            String sql = "select * from Employees";
            Connection conn = DAO.getConnection();
            PreparedStatement ps = conn.prepareCall(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("EmployeeID"));
                e.setLastName(rs.getString("LastName"));
                e.setFirstName(rs.getString("FirstName"));
                e.setBirthDate(rs.getDate("BirthDate"));
                e.setHireDate(rs.getDate("HireDate"));
                e.setAddress(rs.getString("Address"));
                e.setCity(rs.getString("City"));
                e.setCountry(rs.getString("Country"));
                e.setHomePhone(rs.getString("HomePhone"));
                e.setMobile(rs.getString("Mobile"));
                e.setEmail(rs.getString("Email"));
                e.setPhotoPath(rs.getString("PhotoPath"));
                e.setNote(rs.getString("Note"));
                vE.add(e);
            }
            
            return convertToArray(vE);
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Tim kiem Employee co trong CSDL.
     * 
     * @param option
     * @param value
     * @return 
     */
    public Employee[] find(int option, String value) {
        String sql = "";
        switch (option) {
            case 0:
                sql = "select * from Employees where firstname= ? or lastname=?";
                break;
            case 1:
                sql = "select * from Employees where city= ?";
                break;
        }
        Vector vE = new Vector();
        try {
            Connection conn = DAO.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("EmployeeID"));
                e.setLastName(rs.getString("LastName"));
                e.setFirstName(rs.getString("FirstName"));
                e.setBirthDate(rs.getDate("BirthDate"));
                e.setHireDate(rs.getDate("HireDate"));
                e.setAddress(rs.getString("Address"));
                e.setCity(rs.getString("City"));
                e.setCountry(rs.getString("Country"));
                e.setHomePhone(rs.getString("HomePhone"));
                e.setMobile(rs.getString("Mobile"));
                e.setEmail(rs.getString("Email"));
                e.setPhotoPath(rs.getString("PhotoPath"));
                e.setNote(rs.getString("Note"));
                vE.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return convertToArray(vE);
    }

    /**
     * Get Employee by ID.
     * @param id
     * @return
     * @throws ClassNotFoundException 
     */
    public Employee getByID(int id) {
        
        String sql = "select * from Employees where EmployeeID=?";
        
        try {
            Connection conn = DAO.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("EmployeeID"));
                e.setLastName(rs.getString("LastName"));
                e.setFirstName(rs.getString("FirstName"));
                e.setBirthDate(rs.getDate("BirthDate"));
                e.setHireDate(rs.getDate("HireDate"));
                e.setAddress(rs.getString("Address"));
                e.setCity(rs.getString("City"));
                e.setCountry(rs.getString("Country"));
                e.setHomePhone(rs.getString("HomePhone"));
                e.setMobile(rs.getString("Mobile"));
                e.setEmail(rs.getString("Email"));
                e.setPhotoPath(rs.getString("PhotoPath"));
                e.setNote(rs.getString("Note"));
                
                return e;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

   
    /**
     * Convert from Vector to Employee Array
     * 
     * @param v
     * @return 
     */
    private Employee[] convertToArray(Vector v) {
        int n = v.size();
        if (n < 1) {
            return null;
        }
        Employee[] arrEmployee = new Employee[n];
        for (int i = 0; i < n; i++) {
            arrEmployee[i] = (Employee) v.get(i);
        }
        return arrEmployee;
    }
}