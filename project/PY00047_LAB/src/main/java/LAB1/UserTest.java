package LAB1;

public class UserTest {
	public static void main(String[] args) {
		UserManagement mng = new UserManagement();
		
		System.out.println("=======TIM_TAT_CA=======");
		mng.findAll();
		
		System.out.println("=======TIM_DUA_TREN_KHOA_CHINH=======");
		mng.findById("U004");
		
		System.out.println("=======TAO=======");
		User user = new User("U005","654","Pham Ngoc Xuan Tin", "tinpy00047@gmail.com",true);
		mng.create(user);
		
		System.out.println("=======SUA=======");
		mng.update("U003");
		
		System.out.println("=======XOA=======");
		mng.deleteById("U005");
		
		System.out.println("=======TIM_KIEM_THEO_EMAIL=======");
		mng.findUsersByEmailAndRole();
		
        System.out.println("=======DANH_SACH_USER_TRANG_3=======");
        mng.findUsersByPage(2, 4);
    
		
	}
}
