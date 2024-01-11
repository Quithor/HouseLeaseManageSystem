package top.yanquithor.course;

import top.yanquithor.func.Login;
import top.yanquithor.func.Registry;
import top.yanquithor.sql.LandlordDB;
import top.yanquithor.sql.TenantDB;
import top.yanquithor.table.Landlord;
import top.yanquithor.table.Tenant;

import java.util.Scanner;

public class UI {
    
    private static final Scanner sc = new Scanner(System.in);
    
    private UI() {
    }
    
    private static void mainMenu() {
        System.out.println("\t\t\t========房屋租赁系统========");
        System.out.println("\t\t\t*       1.房东端          *");
        System.out.println("\t\t\t*       2.租户端          *");
        System.out.println("\t\t\t*       0.关  闭          *");
        System.out.println("\t\t\t**************************");
        System.out.print("\t\t\t\t请选择:");
    }
    
    public static void systemMain() {
        mainMenu();
        while (true)
            switch (sc.next()) {
                case "1" -> landlordMain();
                case "2" -> tenantMain();
                case "0" -> {
                    System.out.println("\t\t感谢使用本系统!");
                    System.out.println("\t\t再见!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("\t\tERROR: Input mistake!");
                    System.out.print("\t\tReenter, please: ");
                }
            }
    }
    
    private static void landlordMain() {
        System.out.println("\t\t\t========房屋租赁系统*房东端========");
        System.out.println("\t\t\t|           1.登录             |");
        System.out.println("\t\t\t|           2.注册             |");
        System.out.println("\t\t\t|          3.忘记密码           |");
        System.out.println("\t\t\t*******************************");
        System.out.print("\t\t\t\t请选择:");
        boolean isContinue = true;
        do {
            switch (sc.nextInt()) {
                case 1 -> {
                    int id;
                    String pw;
                    System.out.print("\t\t输入您的id:");
                    id = sc.nextInt();
                    System.out.print("\t\t输入您的密码:");
                    pw = sc.next();
                    Landlord landlord = Login.landlordLogin(id, pw);
                    while (landlord == null) {
                        System.out.println("\t\tReenter, please!");
                        System.out.print("\t\t输入您的id:");
                        id = sc.nextInt();
                        System.out.print("\t\t输入您的密码:");
                        pw = sc.next();
                        landlord = Login.landlordLogin(id, pw);
                    }
                    isContinue = false;
                    landlordMenu(landlord);
                }
                case 2 -> {
                    int id;
                    String username;
                    String password;
                    String pw;
                    do {
                        System.out.print("\t\t输入id:");
                        id = sc.nextInt();
                        System.out.print("\t\t输入用户名:");
                        username = sc.next();
                        System.out.print("\t\t输入密码:");
                        password = sc.next();
                        System.out.print("\t\t输入密码:");
                        pw = sc.next();
                        if (password.equals(pw)) {
                            System.out.println("\t\t两次输入密码不一致!");
                        }
                    } while (!password.equals(pw));
                    while (true) {
                        try {
                            if (!Registry.registry(new Landlord(id, username, password))) {
                                System.out.println("\t\t注册成功!");
                                System.out.println("\t\t系统将自动退出以保证您的账号安全!");
                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\t\tReenter, please!");
                    }
                }
                case 3 -> {
                    int id;
                    String password;
                    boolean equality = false;
                    System.out.print("\t\t输入id:");
                    id = sc.nextInt();
                    do {
                        System.out.print("\t\t输入密码:");
                        password = sc.next();
                        System.out.print("\t\t输入密码:");
                        equality = sc.next().equals(password);
                        if (equality) {
                            System.out.println("\t\t两次输入密码不一致!");
                            System.out.println("\t\t请重新输入");
                        }
                    } while (!equality);
                    LandlordCourse.LC.forgePassword(LandlordDB.getLandlord(id),password);
                }
                default -> System.out.println("\t\tERROR: Input mistake!\n\t\tReenter, please!");
            }
        } while (isContinue);
    }
    
    public static void landlordMenu(Landlord landlord) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\t\t\t========房屋租赁系统*房东端========");
        System.out.println("\t\t\t*        1. 添 加 房 子         *");
        System.out.println("\t\t\t*        2. 删 除 房 子         *");
        System.out.println("\t\t\t*        3. 修改 房子 信息       *");
        System.out.println("\t\t\t*        4.显示我的房子情况       *");
        System.out.println("\t\t\t*        5. 注销该账号           *");
        System.out.println("\t\t\t*        0. 退出并关闭系统        *");
        System.out.println("\t\t\t********************************");
        System.out.print("\t\t\t\t请选择要使用的功能:");
        while (true) {
            switch (sc.nextInt()) {
                case 1 -> LandlordCourse.addHouseToLandlord(landlord);
                case 2 -> LandlordCourse.deleteHouseToLandlord(landlord);
                case 3 -> LandlordCourse.modifyHouseToLandlord(landlord);
                case 4 -> LandlordCourse.displayMyHouse(landlord);
                case 5 -> LandlordCourse.deleteMe(landlord);
                case 0 -> {
                    System.out.println("\t\t\t感谢使用本系统!");
                    System.exit(0);
                }
                default -> System.out.println("\t\tERROR: Input mistake!\n\t\tReenter, please!");
            }
        }
    }
    
    private static void tenantMain() {
        System.out.println("\t\t\t========房屋租赁系统*租户端========");
        System.out.println("\t\t\t|           1.登录             |");
        System.out.println("\t\t\t|           2.注册             |");
        System.out.println("\t\t\t|          3.忘记密码           |");
        System.out.println("\t\t\t*******************************");
        System.out.print("\t\t\t\t请选择:");
        boolean isContinue = true;
        do {
            switch (sc.nextInt()) {
                case 1 -> {
                    int id;
                    String pw;
                    System.out.print("\t\t输入您的id:");
                    id = sc.nextInt();
                    System.out.print("\t\t输入您的密码:");
                    pw = sc.next();
                    Tenant tenant = Login.tenantLogin(id, pw);
                    while (tenant == null) {
                        System.out.println("\t\tReenter, please!");
                        System.out.print("\t\t输入您的id:");
                        id = sc.nextInt();
                        System.out.print("\t\t输入您的密码:");
                        pw = sc.next();
                        tenant = Login.tenantLogin(id, pw);
                    }
                    isContinue = false;
                    tenantMenu(tenant);
                }
                case 2 -> {
                    int id;
                    String username;
                    String password;
                    String pw;
                    do {
                        System.out.print("\t\t输入id:");
                        id = sc.nextInt();
                        System.out.print("\t\t输入用户名:");
                        username = sc.next();
                        System.out.print("\t\t输入密码:");
                        password = sc.next();
                        System.out.print("\t\t输入密码:");
                        pw = sc.next();
                        if (password.equals(pw)) {
                            System.out.println("\t\t两次输入密码不一致!");
                        }
                    } while (!password.equals(pw));
                    while (true) {
                        try {
                            if (!Registry.registry(new Tenant(id, username, password))) {
                                System.out.println("\t\t注册成功!");
                                System.out.println("\t\t系统将自动退出以保证您的账号安全!");
                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\t\tReenter, please!");
                    }
                }
                case 3 -> {
                    int id;
                    String password;
                    boolean equality = false;
                    System.out.print("\t\t输入id:");
                    id = sc.nextInt();
                    do {
                        System.out.print("\t\t输入密码:");
                        password = sc.next();
                        System.out.print("\t\t输入密码:");
                        equality = sc.next().equals(password);
                        if (equality) {
                            System.out.println("\t\t两次输入密码不一致!");
                            System.out.println("\t\t请重新输入");
                        }
                    } while (!equality);
                    TenantCourse.TC.forgePassword(TenantDB.getTenant(id),password);
                }
                default -> System.out.println("\t\tERROR: Input mistake!\n\t\tReenter, please!");
            }
        } while (isContinue);
    }
    
    public static void tenantMenu(Tenant tenant) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\t\t\t========房屋租赁系统*租户端========");
        System.out.println("\t\t\t*        1. 我 要 租 房         *");
        System.out.println("\t\t\t*        2. 我 要 退 房         *");
        System.out.println("\t\t\t*        3. 显示 房子 信息       *");
        System.out.println("\t\t\t*        4. 注销该账号           *");
        System.out.println("\t\t\t*        0. 退出并关闭系统        *");
        System.out.println("\t\t\t********************************");
        System.out.print("\t\t\t\t请选择要使用的功能:");
        while (true) {
            switch (sc.nextInt()) {
                case 1 -> TenantCourse.addHouseToTenant(tenant);
                case 2 -> TenantCourse.moveOutHouse(tenant);
                case 3 -> TenantCourse.displayMyHouse(tenant);
                case 4 -> TenantCourse.deleteMe(tenant);
                case 0 -> {
                    System.out.println("\t\t\t\t感谢使用本系统!");
                    System.exit(0);
                }
                default -> System.out.println("\t\tERROR: Input mistake!\n\t\tReenter, please!");
            }
        }
    }
}