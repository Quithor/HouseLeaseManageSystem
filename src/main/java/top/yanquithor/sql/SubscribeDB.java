package top.yanquithor.sql;

import top.yanquithor.table.Subscribe;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @author YanQuithor
 * @since 2023.12.29
 */
public class SubscribeDB {
    
    private SubscribeDB() {}
    
    public static HashSet getAll() {
        HashSet<Subscribe> set = new HashSet<>();
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement("select * from subscribe");
            ResultSet rs = ps.executeQuery();
            Subscribe subscribe;
            while (rs.next()){
                subscribe = new Subscribe(
                        rs.getInt("house_id"),
                        rs.getInt("tenant_id"),
                        rs.getDate("reservation_time")
                );
                set.add(subscribe);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    
    public static HashSet get(int tenant) {
        HashSet<Subscribe> set = new HashSet<>();
        PreparedStatement ps;
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
                set.add(new Subscribe(
                        rs.getInt("house_id"),
                        rs.getInt("tenant_id"),
                        rs.getDate("reservation_time")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    
    public static boolean add(HashSet<Subscribe> set) {
        PreparedStatement ps;
        boolean result = true;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    )
                    .prepareStatement("insert into subscribe (" +
                            " house_id," +
                            " tenant_id," +
                            " reservation_time) value (?,?,?)");
            for (Subscribe l : set) {
                ps.setInt(1, l.getHouse());
                ps.setInt(2,l.getTenant());
                ps.setDate(3,l.getTime());
                result &= ps.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean add(Subscribe subscribe) {
        boolean result = true;
        try {
            PreparedStatement ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement("insert into subscribe (" +
                            " tenant_id," +
                            " house_id," +
                            " reservation_time) value (?,?,?)");
            ps.setInt(1,subscribe.getTenant());
            ps.setInt(2,subscribe.getHouse());
            ps.setDate(3,subscribe.getTime());
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean modify(Subscribe subscribe,int house,int tenant) {
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW)
                    .prepareStatement("update subscribe set" +
                            " house_id=?," +
                            " tenant_id=?," +
                            " reservation_time=?" +
                            "where house_id=? and tenant_id=?");
            ps.setInt(1, subscribe.getHouse());
            ps.setInt(2,subscribe.getTenant());
            ps.setDate(3,subscribe.getTime());
            ps.setInt(4,house);
            ps.setInt(5,tenant);
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean delete(Subscribe subscribe) {
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    )
                    .prepareStatement("delete from subscribe where" +
                            " house_id=? and" +
                            " tenant_id=? and" +
                            " reservation_time=?");
            ps.setInt(1, subscribe.getHouse());
            ps.setInt(2, subscribe.getTenant());
            ps.setDate(3, subscribe.getTime());
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
                    ).prepareStatement("delete from subscribe where house_id="+house);
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
                    ).prepareStatement("delete from subscribe where tenant_id="+(int)tenant);
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
}

