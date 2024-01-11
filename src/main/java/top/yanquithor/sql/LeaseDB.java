package top.yanquithor.sql;

import top.yanquithor.table.Lease;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @author YanQuithor
 * @since 2023.12.29
 */
public final class LeaseDB {
    
    private LeaseDB() { }
    
    public static HashSet<Lease> getAll() {
        HashSet<Lease> set = new HashSet<>();
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement("select * from lease");
            ResultSet rs = ps.executeQuery();
            Lease lease;
            while (rs.next()){
                lease = new Lease(
                        rs.getInt("house_id"),
                        rs.getInt("tenant_id"),
                        rs.getDate("begin_time"),
                        rs.getDate("end_time"),
                        rs.getDouble("deposit")
                );
                lease.setTime(rs.getDate("signing_time"));
                set.add(lease);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    
    public static HashSet<Lease> get(int tenant) {
        HashSet<Lease> set = new HashSet<>();
        PreparedStatement ps;
        Lease lease;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    ).prepareStatement("select" +
                            " * from lease" +
                            " where tenant_id=?");
            ps.setInt(1,tenant);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lease = new Lease(
                        rs.getInt("house_id"),
                        rs.getInt("tenant_id"),
                        rs.getDate("begin_time"),
                        rs.getDate("end_time"),
                        rs.getDouble("deposit")
                );
                lease.setTime(rs.getDate("signing_time"));
                set.add(lease);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    
    public static boolean add(HashSet<Lease> set) {
        PreparedStatement ps;
        boolean result = true;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    )
                    .prepareStatement("insert into lease (" +
                            " house_id," +
                            " tenant_id," +
                            " signing_time," +
                            " begin_time," +
                            " end_time," +
                            " deposit) value (?,?,?,?,?,?)");
            for (Lease l : set) {
                ps.setInt(1, l.getHouse());
                ps.setInt(2,l.getTenant());
                ps.setDate(3,l.getTime());
                ps.setDate(4,l.getBegin());
                ps.setDate(5,l.getEnd());
                ps.setDouble(6,l.getDeposit());
                result &= ps.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean add(Lease lease) {
        boolean result = true;
        try {
            PreparedStatement ps = DriverManager
                    .getConnection(DBData.URL,DBData.NAME,DBData.PW)
                    .prepareStatement("insert into lease (" +
                            " house_id," +
                            " tenant_id," +
                            " signing_time," +
                            " begin_time," +
                            " end_time," +
                            " deposit) VALUE (?,?,?,?,?,?)");
            ps.setInt(1,lease.getHouse());
            ps.setInt(2,lease.getTenant());
            ps.setDate(3,lease.getTime());
            ps.setDate(4,lease.getBegin());
            ps.setDate(5,lease.getEnd());
            ps.setDouble(6,lease.getDeposit());
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean modify(Lease lease,int house,int tenant) {
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW)
                    .prepareStatement("update lease set" +
                            " house_id=?," +
                            " tenant_id=?," +
                            " signing_time=?," +
                            " begin_time=?," +
                            " end_time=?," +
                            " deposit=? where house_id=? and tenant_id=?");
            ps.setInt(1, lease.getHouse());
            ps.setInt(2,lease.getTenant());
            ps.setDate(3,lease.getTime());
            ps.setDate(4,lease.getBegin());
            ps.setDate(5,lease.getEnd());
            ps.setDouble(6, lease.getDeposit());
            ps.setInt(7,house);
            ps.setInt(8,tenant);
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
    
    public static boolean delete(Lease lease) {
        boolean result = true;
        PreparedStatement ps;
        try {
            ps = DriverManager
                    .getConnection(
                            DBData.URL,
                            DBData.NAME,
                            DBData.PW
                    )
                    .prepareStatement("delete from lease where" +
                            " house_id=? and" +
                            " tenant_id=? and" +
                            " signing_time=? and" +
                            " begin_time=? and" +
                            " end_time=? and" +
                            " deposit=?");
            ps.setInt(1, lease.getHouse());
            ps.setInt(2, lease.getTenant());
            ps.setDate(3, lease.getTime());
            ps.setDate(4, lease.getBegin());
            ps.setDate(5, lease.getEnd());
            ps.setDouble(6, lease.getDeposit());
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
                    ).prepareStatement("delete from lease where house_id="+house);
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
                    ).prepareStatement("delete from lease where tenant_id="+(int)tenant);
            result = ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return !result;
    }
}