package AppVersion;

import java.util.Scanner;

public class CommandlineVersion {
    public void CommandlineVersionInitialize() {

        System.out.println("Nhap Lua Chon:  ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean running = true;
        while(running) {
            System.out.println("Phien Ban Ma Lenh: \n");
            System.out.println("Roll: \n");
            System.out.println("[1] Management");
            System.out.println("[2] Reader");

            System.out.println("[0] Exit");
            System.out.println("[1] Add Document");
            System.out.println("[2] Remove Document");
            System.out.println("[3] Update Document");
            System.out.println("[4] Find Document");
            System.out.println("[5] Display Document");

            System.out.println("[6] Add User");
            System.out.println("[7] Borrow Document");
            System.out.println("[8] Return Document");
            System.out.println("[9] Display User Info");

            System.out.print("Nhap Lua Chon: ");
        }
    }
}
