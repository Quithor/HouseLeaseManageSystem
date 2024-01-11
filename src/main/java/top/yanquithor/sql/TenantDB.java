package top.yanquithor.sql;

import top.yanquithor.table.Tenant;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @author YanQuithor
 * @since 2023.12.28
 */
public final class TenantDB {
    
    private TenantDB() { }
    
    public static Tenant getTenant(int id) {
        Tenant tenant = null;
        PreparedStatement ps;
        String select = "select * from tenant where tenant_id=" + id;
        try {
            tenant = new Tenant();
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(select);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                tenant.setId(set.getInt("tenant_id"));
                tenant.setUsername(set.getString("tenant_name"));
                tenant.setPassword(set.getString("tenant_password"));
                tenant.setAge(set.getInt("tenant_age"));
                tenant.setSex(set.getString("tenant_sex"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenant;
    }
    
    public static boolean add(Tenant tenant) {
        boolean result = true;
        PreparedStatement ps;
        String insert = "insert into tenant (" +
                " tenant_id," +
                " tenant_name," +
                " tenant_password," +
                " tenant_age," +
                " tenant_sex) value (?,?,?,?,?)";
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(insert);
            ps.setInt(1,tenant.getId());
            ps.setString(2,tenant.getUsername());
            ps.setString(3,tenant.getPassword());
            ps.setInt(4,tenant.getAge());
            ps.setString(5,tenant.getSex());
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }
    
    public static boolean delete(int id) {
        boolean result = true;
        PreparedStatement ps;
        String drop = "delete from tenant where tenant_id="+id;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(drop);
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }
    
    public static boolean modify(Tenant tenant) {
        boolean result = true;
        PreparedStatement ps;
        String update = "update tenant set " +
                "tenant_name="+tenant.getUsername()+"," +
                "tenant_password="+tenant.getPassword()+"," +
                "tenant_age="+tenant.getAge()+"," +
                "tenant_sex="+tenant.getSex() +
                " where tenant_id="+tenant.getId();
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement(update);
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }
}
