package projecttwo.app.server.businessservice.organization.contactmanagement;
import projecttwo.app.shared.organization.contactmanagement.CoreContacts;
import java.util.List;

public interface CoreContactsBusinessService {

    void update(CoreContacts entity) throws Exception;

    void update(List<CoreContacts> entity) throws Exception;
}
