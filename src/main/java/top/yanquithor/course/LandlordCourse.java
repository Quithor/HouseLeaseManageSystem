package top.yanquithor.course;

import top.yanquithor.exception.StringToLongException;
import top.yanquithor.sql.HouseData;
import top.yanquithor.sql.LandlordDB;
import top.yanquithor.table.House;
import top.yanquithor.table.Landlord;
import top.yanquithor.table.People;

import java.util.HashSet;
import java.util.Scanner;

public class LandlordCourse extends UserCourse{
    
    public static final LandlordCourse LC = new LandlordCourse();
    
    private static final Scanner sc = new Scanner(System.in);
    
    /**
     * This method will add one house to landlord.
     * @param house house object
     * @param landlord landlord object
     */
    public static void addHouse(House house,Landlord landlord) {
        System.out.println("Data import to database is: " + HouseData.add(house));
        System.out.println("House add to landlord object is: " + landlord.addHouse(house.getId()));
    }
    
    /**
     * This method will add any house to landlord.
     * @param houses house set
     * @param landlord landlord object
     */
    public static void addHouses(HashSet<House> houses,Landlord landlord) {
        for (House house : houses) {
            System.out.println("House add to landlord object is: "+landlord.addHouse(house.getId()));
        }
        for (House house : houses) {
            System.out.println("Data import to database is: " + HouseData.add(house));
        }
    }
    
    /**
     * This method will remove house from landlord and database;
     * @param house house id
     * @param landlord landlord object
     */
    public static void remove(int house,Landlord landlord) {
        System.out.println("House remove from database: " + HouseData.delete(house));
        System.out.println("House remove from landlord: " + landlord.removeHouse(house));
    }
    
    /**
     * This method will modify house information.
     * @param landlord landlord id
     * @param house new house (include new information house object)
     */
    public static void modify(int landlord, House house, HashSet<House> set) {
        for (var p : set) {
            if (p.getId() == house.getId()) {
                p = house;
                System.out.println("New information write to databases is: " + HouseData.modify(p));
            }
        }
    }
    
    public static void addHouseToLandlord(Landlord landlord) {
        boolean quit = false;
        do {
            try {
                House house = new House();
                System.out.print("\t\t输入房子编码:");
                house.setId(sc.nextInt());
                System.out.print("\t\t输入房子的租金:");
                house.setRent(sc.nextDouble());
                System.out.print("\t\t输入房子的户型(r表示室, h表示厅, 三室一厅写为3r1h):");
                house.setTypeOfHouse(sc.next());
                System.out.print("\t\t输入房子的地址:");
                house.setAddress(sc.next());
                house.setHouseIsEmptyOrNot(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!quit);
    }
    
    public static void deleteHouseToLandlord(Landlord landlord) {
        HashSet<House> houses = new HashSet<>();
        HashSet<Integer> houseId = landlord.getHouses();
        for (Integer id : houseId) {
            houses.add(HouseData.getHouse(id));
        }
        System.out.println("--------------------------------------------------------");
        for (House house : houses) {
            System.out.print("房屋编号: " + house.getId());
            System.out.print("\t\t房屋状态: ");
            if (house.houseEmpty())
                System.out.print("空置");
            else
                System.out.print("有租户");
            System.out.print("\t\t房屋户型: " + house.getTypeOfHouse());
            System.out.println("房屋地址: " + house.getAddress());
            System.out.println("--------------------------------------------------------");
        }
        System.out.print("输入要删除的房子编号: ");
        LandlordCourse.remove(sc.nextInt(),landlord);
        System.out.println("输入任意字符返回主菜单");
        sc.next();
        UI.landlordMenu(landlord);
    }
    
    public static void modifyHouseToLandlord(Landlord landlord) {
        HashSet<House> houses = new HashSet<>();
        HashSet<Integer> houseId = landlord.getHouses();
        for (Integer id : houseId) {
            houses.add(HouseData.getHouse(id));
        }
        System.out.println("--------------------------------------------------------");
        for (House house : houses) {
            System.out.print("房屋编号: " + house.getId());
            System.out.print("\t\t房屋状态: ");
            if (house.houseEmpty())
                System.out.print("空置");
            else
                System.out.print("有租户");
            System.out.print("\t\t房屋户型: " + house.getTypeOfHouse());
            System.out.println("房屋地址: " + house.getAddress());
            System.out.println("--------------------------------------------------------");
        }
        System.out.println("\t\t输入要修改的房子编号: ");
        for (House house : houses) {
            if (house.getId() == sc.nextInt()) {
                System.out.println("当前系统维护，无法修改");
            }
        }
        System.out.println("输入任意字符返回组菜单");
        sc.next();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        UI.landlordMenu(landlord);
    }
    
    public static void displayMyHouse(Landlord landlord) {
        HashSet<House> houses = new HashSet<>();
        HashSet<Integer> houseId = landlord.getHouses();
        for (Integer id : houseId) {
            houses.add(HouseData.getHouse(id));
        }
        System.out.println("--------------------------------------------------------");
        for (House house : houses) {
            System.out.print("房屋编号: " + house.getId());
            System.out.print("\t\t房屋状态: ");
            if (house.houseEmpty())
                System.out.print("空置");
            else
                System.out.print("有租户");
            System.out.print("\t\t房屋户型: " + house.getTypeOfHouse());
            System.out.println("房屋地址: " + house.getAddress());
            System.out.println("--------------------------------------------------------");
        }
        System.out.print("\t\t输入任意字符返回主菜单");
        sc.next();
        UI.landlordMenu(landlord);
    }
    
    public static void deleteMe(Landlord landlord) {
        System.out.println("\t\t您确定要注销账号吗?");
        System.out.print("\t\t输入密码确定注销");
        if (landlord.getPassword().equals(sc.next())) {
            System.out.println("\t\t您真的要注销账号吗？");
            System.out.print("\t\t再次输入密码确认注销账号: ");
            if (sc.next().equals(landlord.getPassword())) {
                System.out.println("\t\t账号注销是否成功: "
                        + LandlordDB.delete(landlord.getId()));
                UI.systemMain();
            } else {
                System.out.println("\t\t密码输入有误，系统将返回主菜单!");
                UI.landlordMenu(landlord);
            }
        }
    }
    
    /**
     * This method will reset user password.
     * @param people user object
     * @param newPW user new password
     */
    @Override
    public void forgePassword(People people, String newPW) {
        if (people instanceof Landlord){
            Landlord landlord = (Landlord) people;
            try {
                landlord.setPassword(newPW);
                System.out.println("User " + landlord.getId() + " password reset is: "+LandlordDB.modify(landlord));
            } catch (StringToLongException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
