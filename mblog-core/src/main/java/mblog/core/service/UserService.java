/**
 * 
 */
package mblog.core.service;

import mblog.core.pojos.User;
import mtons.commons.pojos.UserProfile;

/**
 * @author langhsu
 *
 */
public interface UserService {
	UserProfile login(String username, String password);
	void register(User user);
	UserProfile update(User user);
	User get(long id);
	UserProfile updateAvater(long id, String path);
	void updatePassword(long id, String newPassword);
	void updatePassword(long id, String oldPassword, String newPassword);
}
