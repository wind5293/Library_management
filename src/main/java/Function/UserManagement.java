package Function;

import User.ReaderAccount;

import java.util.ArrayList;

public class UserManagement {
    /**
     * ArrayList Quan ly tai khoan
     * Sau nay se sd DB o day.
     */
    private ArrayList<ReaderAccount> readerAccountsList;

    /**
     * Xu ly them khi Register: dang ksy -> add vao readerAccountList.
     * Kiem tra username da co
     * Neu da co -> throw exception
     * Commandline luu vao Array
     */
    public void addReaderAccount(ReaderAccount newAccount) throws Exception {
        for (ReaderAccount account : readerAccountsList) {
            if (account.getUserName().equals(newAccount.getUserName())) {
                //day sang loginExcetion.
                throw new Exception("Tên tài khoản đã tồn tại.");
            }
        }
        readerAccountsList.add(newAccount);
    }

    /**
     * Kiem tra trong du lieu nguoi dung ve tai khoan
     * Dang nhap sai -> exception.
     * Khong tim thay account, dang nhap sai mk
     */
//    public boolean checkLogin(ReaderAccount loginAccount) {
//        for(int i = 0; i < readerAccountsList.size() ; i++) {
//            ReaderAccount account = readerAccountsList.get(i);
//            if (account.getUserName().equals(loginAccount.getUserName())) {
//                    // Kiểm tra mật khẩu
//                if (account.getPassWord().equals(loginAccount.getPassWord())) {
//                    return true;
//                } else {
//                    throw new Exception("Sai mật khẩu.");
//                }
//            }
//        }
//
//        throw new Exception("Không tìm thấy tài khoản.");
//    }
}

