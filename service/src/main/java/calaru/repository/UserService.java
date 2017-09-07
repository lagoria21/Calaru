package calaru.repository;

import calaru.model.User;

public interface UserService {

	public User findUserByEmail(String email);
	
	public void saveUser(User user);
	
}
