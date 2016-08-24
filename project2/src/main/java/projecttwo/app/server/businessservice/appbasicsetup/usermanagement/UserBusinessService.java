package projecttwo.app.server.businessservice.appbasicsetup.usermanagement;
import projecttwo.app.shared.appbasicsetup.usermanagement.User;
import java.util.List;

public interface UserBusinessService {

    void update(User entity) throws Exception;

    void update(List<User> entity) throws Exception;
}
