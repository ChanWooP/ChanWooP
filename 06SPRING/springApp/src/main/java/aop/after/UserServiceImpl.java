package aop.after;

public class UserServiceImpl implements UserService {
	private String value;
	
	@Override
	public void save(String value) {
		System.out.println("save...");
		this.value = value;
	}

	@Override
	public void write() {
		System.out.println("°á°ú => " + value);
	}
}
