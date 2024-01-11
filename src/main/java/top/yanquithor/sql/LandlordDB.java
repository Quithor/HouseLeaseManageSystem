package top.yanquithor.sql;

import top.yanquithor.table.Landlord;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @author YanQuitor
 * @since 2023.12.27
 */
public final class LandlordDB {
    
    private LandlordDB() { }
    
    public static Landlord getLandlord(int id) {
        Landlord l = null;
        PreparedStatement ps;
        String select = "select * from landlord where="+id;
        try {
            l = new Landlord();
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(select);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                l.setId(id);
                l.setUsername(set.getString("landlord_name"));
                l.setPassword(set.getString("landlord_password"));
                l.setAge(set.getInt("landlord_age"));
                l.setSex(set.getString("landlord_sex"));
                select = "select * from house " +
                        "where landlord_id=" + id;
                set = ps.executeQuery(select);
                while (set.next()){
                    l.addHouse(set.getInt("house_id"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }
    
    public static boolean add(Landlord l) {
        boolean result = true;
        PreparedStatement ps;
        String add = "insert into landlord (" +
                "landlord_id," +
                "landlord_name," +
                "landlord_password," +
                "landlord_age," +
                "landlord_sex) value (?,?,?,?,?)";
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(add);
            ps.setInt(1,l.getId());
            ps.setString(2,l.getUsername());
            ps.setString(3,l.getPassword());
            ps.setInt(4,l.getAge());
            ps.setString(5,l.getSex());
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean modify(Landlord l) {
        boolean result = true;
        PreparedStatement ps;
        String modify = "update landlord set " +
                "landlord_name=?," +
                "landlord_password=?," +
                "landlord_age=?," +
                "landlord_sex=? " +
                "where landlord_id="+l.getId();
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME, DBData.PW)
                    .prepareStatement(modify);
            ps.setString(1,l.getUsername());
            ps.setString(2,l.getPassword());
            ps.setInt(3,l.getAge());
            ps.setString(4,l.getSex());
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean delete(int id) {
        boolean result = true;
        PreparedStatement ps;
        String drop = "delete from landlord where landlord_id="+id;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(drop);
            boolean dropL = ps.execute();
            drop = "delete from house where landlord_id="+id;
            boolean dropH = ps.execute(drop);
            result = dropL && dropH;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
}
