package top.yanquithor.course;

import top.yanquithor.exception.StringToLongException;
import top.yanquithor.sql.DiscussingDB;
import top.yanquithor.sql.HouseData;
import top.yanquithor.sql.LeaseDB;
import top.yanquithor.sql.TenantDB;
import top.yanquithor.table.Tenant;
import top.yanquithor.table.House;
import top.yanquithor.table.Lease;
import top.yanquithor.table.Discussing;
import top.yanquithor.table.Subscribe;
import top.yanquithor.table.People;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class is used to perform tenant operations.
 *
 * @author YanQuithor
 * @since 2024.1.4
 */
public class TenantCourse extends UserCourse {
    
    public static final TenantCourse TC = new TenantCourse();
    
    private static final Scanner sc = new Scanner(System.in);
    
    private TenantCourse() { }
    
    /**
     * This method is used to add a contract to the contract set and database.
     *
     * @param lease contract that will give to this method
     * @param set   contract gather
     */
    public static void rent(Lease lease, HashSet<Lease> set) {
        boolean add = LeaseDB.add(lease);
        System.out.println("Result of signing this rental contract:" + add);
        if (add) set.add(lease);
    }
    
    /**
     * This method adds a comment record to the comment set and database.
     *
     * @param tenant tenant id
     * @param house  house id
     * @param text   comment content
     * @param set    comment set
     */
    public static void discussing(int tenant, int house, String text, HashSet<Discussing> set) {
        Discussing discussing = new Discussing(house, tenant, text);
        boolean add = DiscussingDB.add(discussing);
        System.out.println("Whether the review was successful:" + add);
        if (add) set.add(discussing);
    }
    
    /**
     * This method adds a reservation record to the reservation recordset and database.
     *
     * @param tenant tenant id
     * @param house  house id
     * @param time   reservation time
     * @param set    reservation recordset
     */
    public static void subscribe(int tenant, int house, Date time, HashSet<Subscribe> set) {
        boolean add;
        for (var p : set)
            if (p.getHouse() == house)
                if (p.getTime() == time) add = false;
        add = set.add(new Subscribe(house, tenant, time));
        if (add) System.out.println("Subscribe " + house + " on " + time + " successful.");
        else System.out.println("Subscribe fail, " + house + " is subscribed on" + time);
    }
    
    /**
     * This method will find all house and choose include specifies the address of the field.
     *
     * @param tenant      tenant id
     * @param partAddress will choose address
     * @param houses      house set
     * @return result house set
     */
    public static HashSet<House> foundByAddress(int tenant, String partAddress, HashSet<House> houses) {
        HashSet<House> houseSet = new HashSet<>();
        Pattern regex = Pattern.compile(".*" + partAddress + ".*");
        for (var p : houses)
            if (regex.matcher(p.getAddress()).find())
                houseSet.add(p);
        return houseSet;
    }
    
    /**
     * This method will add lease house time.
     *
     * @param house  house id
     * @param tenant tenant id
     * @param month  what months number tenant will add
     */
    public static void addTime(int house, int tenant, int month) {
        HashSet<Lease> leases = LeaseDB.get(tenant);
        for (var l : leases) {
            if (l.getHouse() == house) {
                if (l.getEnd().getMonth() + month > 12) {
                    l.getEnd().setYear(l.getEnd().getYear() + (month / 12));
                    l.getEnd().setMonth(l.getEnd().getMonth() + (month % 12));
                } else l.getEnd().setMonth(l.getEnd().getMonth() + month);
                System.out.println("Data import to database is: " + LeaseDB.modify(l, house, tenant));
            }
        }
    }
    
    /**
     * This method will finish lease contract.
     *
     * @param house  house id
     * @param tenant tenant id
     */
    public static void remove(int house, int tenant) {
        HashSet<Lease> leases = LeaseDB.get(tenant);
        for (var l : leases) {
            if (l.getEnd().compareTo(new Date(System.currentTimeMillis())) > 0)
                if (l.getHouse() == house) {
                    l.setEnd(new Date(System.currentTimeMillis()));
                    System.out.println("Data update to database is: " + LeaseDB.modify(l, house, tenant));
                }
        }
    }
    
    public static void addHouseToTenant(Tenant tenant) {
        try {
            HashSet<House> houses = HouseData.getAll();
            HashSet<House> nullHouse = new HashSet<>();
            for (House house : houses) {
                if (house.houseEmpty()) {
                    nullHouse.add(house);
                }
            }
            System.out.println("----------------------------------------------------------");
            for (House house : nullHouse) {
                System.out.print("房子编号: " + house.getId());
                System.out.print("\t\t房租: " + house.getRent() + "元/月");
                System.out.println("\t\t房屋户型: " + house.getTypeOfHouse());
                System.out.println("所在地址: " + house.getAddress());
                System.out.println("----------------------------------------------------------");
            }
            System.out.print("输入要租的房子的房子编号:");
            int houseId = sc.nextInt();
            System.out.print("请输入日期(格式：yyyy-MM-dd):");
            String input = sc.nextLine();
            System.out.print("输入要租的时长(月): ");
            int month = sc.nextInt();
            System.out.print("输入租房押金: ");
            double price = sc.nextDouble();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(sdf.parse(input).getTime());
            System.out.print("输入密码确认租赁: ");
            if (tenant.getPassword().equals(sc.next()))
                rent(new Lease(houseId,tenant.getId(),date,month,price),LeaseDB.getAll());
            System.out.println("租赁成功！系统返回主菜单!");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            UI.tenantMenu(tenant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void moveOutHouse(Tenant tenant) {
        HashSet<Lease> lease = LeaseDB.get(tenant.getId());
        HashSet<Lease> now = new HashSet<>();
        Date nowTime = new Date(System.currentTimeMillis());
        for (Lease p : lease) {
            if (p.getEnd().compareTo(new Date(System.currentTimeMillis())) > 0) {
                now.add(p);
            }
        }
        System.out.println("---------------------未过期的合同预览---------------------");
        for (Lease out : now) {
            System.out.println("房子地址: " + HouseData.getHouse(out.getHouse()).getAddress());
            System.out.println("剩余时间: "
                    + ((out.getEnd().getYear() - nowTime.getYear()) * 12
                    + (out.getEnd().getMonth() - nowTime.getMonth()))
                    + "月" + (out.getEnd().getDay() - nowTime.getDay()));
            System.out.println("-------------------------------------------------------");
        }
        if (now.isEmpty()) {
            System.out.println("\t\t您未租任何房子!");
        } else {
            System.out.print("输入要退的房子: ");
            remove(sc.nextInt(), tenant.getId());
        }
        System.out.print("\t\t输入0返回上级菜单: ");
        if (sc.nextInt() == 0)
            UI.tenantMenu(tenant);
        else
            System.out.println("\t\t输入内容错误 ! ! !");
    }
    
    public static void displayMyHouse(Tenant tenant) {
        System.out.println("\t\t\t========租户端*信息显示========");
        System.out.println("\t\t\t*        1.未过期的租房       *");
        System.out.println("\t\t\t*        2.已过期的租房       *");
        System.out.println("\t\t\t*        3.可以租的房子       *");
        System.out.println("\t\t\t-----------------------------");
        System.out.print("\t\t\t\t请选择:");
        switch (sc.nextInt()) {
            case 1 -> {
                HashSet<Lease> leases = LeaseDB.get(tenant.getId());
                HashSet<Lease> newLease = new HashSet<>();
                for (var p : leases)
                    if (p.getEnd().compareTo(new Date(System.currentTimeMillis())) > 0)
                        newLease.add(p);
                for (var l : newLease) {
                    System.out.println("房屋地址: " + HouseData.getHouse(l.getHouse()).getAddress());
                    System.out.print("开始日期: " + l.getBegin());
                    System.out.println("\t\t\t结束日期: " + l.getEnd());
                    System.out.println("---------------------------------------------------------------------------");
                }
                System.out.print("\t输入0返回上级菜单");
                int choose = sc.nextInt();
                if (choose == 0) displayMyHouse(tenant);
                else System.out.println("\t输入错误");
            }
            case 2 -> {
                HashSet<Lease> oldLease = new HashSet<>();
                HashSet<Lease> lease = LeaseDB.get(tenant.getId());
                for (var p : lease) {
                    if (p.getEnd().compareTo(new Date(System.currentTimeMillis())) < 0) {
                        oldLease.add(p);
                    }
                }
                System.out.println("---------------------------------------------------------------------------");
                for (var l : oldLease) {
                    System.out.println("房屋地址: " + HouseData.getHouse(l.getHouse()).getAddress());
                    System.out.print("开始日期: " + l.getBegin());
                    System.out.println("\t\t\t结束日期: " + l.getEnd());
                    System.out.println("---------------------------------------------------------------------------");
                }
                System.out.print("\t输入0返回上级菜单");
                int choose = sc.nextInt();
                if (choose == 0) displayMyHouse(tenant);
                else System.out.println("\t输入错误");
            }
            case 3 -> {
                HashSet<Lease> all = LeaseDB.getAll();
                HashSet<House> houses = HouseData.getAll();
                HashSet<House> house = new HashSet<>();
                for (House p : houses) {
                    if (p.houseEmpty()) {
                        house.add(p);
                    } else {
                        for (Lease l : all) {
                            if (l.getTenant() != tenant.getId()) {
                                house.add(p);
                            }
                        }
                    }
                }
                System.out.println("---------------------------------------------------------------------------");
                for (House h : house) {
                    System.out.print("房子编号: " + h.getId());
                    System.out.print("\t\t房子类型: " + h.getTypeOfHouse());
                    System.out.println("\t\t房租: " + h.getRent() + "元/月");
                    System.out.println("房屋地址: " + h.getAddress());
                    System.out.println("---------------------------------------------------------------------------");
                }
                System.out.print("\t输入0返回上级菜单");
                int choose = sc.nextInt();
                if (choose == 0) displayMyHouse(tenant);
                else System.out.println("\t输入错误");
            }
            default -> System.out.println("ERROR: Input number mistake");
        }
    }
    
    public static void deleteMe(Tenant tenant) {
        System.out.println("\t\t您确定要注销账号吗?");
        System.out.print("\t\t输入密码确定注销");
        if (tenant.getPassword().equals(sc.next())) {
            System.out.println("\t\t您真的要注销账号吗？");
            System.out.print("\t\t再次输入密码确认注销账号: ");
            if (sc.next().equals(tenant.getPassword())) {
                System.out.println("\t\t账号注销是否成功: "
                        + TenantDB.delete(tenant.getId()));
                UI.systemMain();
            } else {
                System.out.println("\t\t密码输入有误，系统将返回主菜单!");
                UI.tenantMenu(tenant);
            }
        }
    }
    
    /**
     * This method is used to reset user password.
     *
     * @param people user object
     * @param newPW  user new password
     */
    @Override
    public void forgePassword(People people, String newPW) {
        if (people instanceof Tenant) {
            Tenant tenant = (Tenant) people;
            try {
                tenant.setPassword(newPW);
                TenantDB.modify(tenant);
            } catch (StringToLongException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}