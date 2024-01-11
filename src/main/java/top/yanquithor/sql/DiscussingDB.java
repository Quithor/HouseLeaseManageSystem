package top.yanquithor.sql;

import top.yanquithor.table.Discussing;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public final class DiscussingDB {
    
    private DiscussingDB() { }
    
    public static HashSet getAll() {
        HashSet<Discussing> set = new HashSet<>();
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement("select * from discuss");
            ResultSet rs = ps.executeQuery();
            Discussing discussing;
            while (rs.next()){
                discussing = new Discussing(
                        rs.getInt("house_id"),
                        rs.getInt("tenant_id"),
                        rs.getString("comment_content")
                );
                discussing.setTime(rs.getDate("reservation_time"));
                set.add(discussing);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    
    public static HashSet get(int tenant) {
        HashSet<Discussing> set = new HashSet<>();
        PreparedStatement ps;
        Discussing discussing;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    ).prepareStatement("select" +
                            " * from subscribe" +
                            " where tenant_id=?");
            ps.setInt(1,tenant);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                discussing = new Discussing(
                        rs.getInt("house_id"),
                        rs.getInt("tenant_id"),
                        rs.getString("comment_content")
                );
                discussing.setTime(rs.getDate("reservation_time"));
                set.add(discussing);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    
    public static boolean add(HashSet<Discussing> set) {
        PreparedStatement ps;
        boolean result = true;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    )
                    .prepareStatement("insert into discuss (" +
                            " house_id," +
                            " tenant_id," +
                            " comment_time," +
                            " comment_content" +
                            " ) value (?,?,?,?)");
            for (Discussing l : set) {
                ps.setInt(1, l.getHouse());
                ps.setInt(2,l.getTenant());
                ps.setDate(3,l.getTime());
                ps.setString(4,l.getContent());
                result &= ps.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean modify(Discussing discussing,int house,int tenant) {
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW)
                    .prepareStatement("update discuss set" +
                            " house_id=?," +
                            " tenant_id=?," +
                            " comment_time=?," +
                            " comment_content=?" +
                            "where house_id=? and tenant_id=?");
            ps.setInt(1, discussing.getHouse());
            ps.setInt(2,discussing.getTenant());
            ps.setDate(3,discussing.getTime());
            ps.setString(4,discussing.getContent());
            ps.setInt(5,house);
            ps.setInt(6,tenant);
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean delete(Discussing discussing) {
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    )
                    .prepareStatement("delete from discuss where" +
                            " house_id=? and" +
                            " tenant_id=? and" +
                            " comment_time=? and" +
                            " comment_content=?");
            ps.setInt(1, discussing.getHouse());
            ps.setInt(2, discussing.getTenant());
            ps.setDate(3, discussing.getTime());
            ps.setString(4,discussing.getContent());
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean delete(int house) {
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    ).prepareStatement("delete from discuss where house_id="+house);
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean delete(double tenant){
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    ).prepareStatement("delete from discuss where tenant_id="+(int)tenant);
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean add(Discussing discussing) {
        boolean result = true;
        try {
            PreparedStatement ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement("insert into discuss (" +
                            " tenant_id," +
                            " house_id," +
                            " comment_time," +
                            " comment_content) VALUE (?,?,?,?)");
            ps.setInt(1,discussing.getTenant());
            ps.setInt(2,discussing.getHouse());
            ps.setDate(3,discussing.getTime());
            ps.setString(4,discussing.getContent());
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
}