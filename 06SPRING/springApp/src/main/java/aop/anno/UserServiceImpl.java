package aop.anno;

public class UserServiceImpl implements UserService {
	private String value;
	
	@Override
	public void save(String value) {
		System.out.println("save...");
		this.value = value;
	}

	@Override
	public void write() {
		// Integer.parseInt(value);
		System.out.println("°á°ú => " + value);
	}
}
