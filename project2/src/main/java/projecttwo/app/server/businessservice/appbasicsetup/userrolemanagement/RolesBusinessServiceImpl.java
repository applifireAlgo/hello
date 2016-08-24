package projecttwo.app.server.businessservice.appbasicsetup.userrolemanagement;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projecttwo.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.Roles;
import java.util.List;

@Component
public class RolesBusinessServiceImpl implements RolesBusinessService {

    @Autowired
    private RolesRepository rolesRepository;

    /**
     * Update the <Roles> object
     * @Params Object of Roles
     * @throws java.lang.Exception
     */
    @Override
    public void update(Roles entity) throws Exception {
        if (entity.isHardDelete()) {
            rolesRepository.delete(entity.getRoleId());
        } else {
            rolesRepository.deleteRoleMenuBridge(entity.getDeletedRoleMenuBridgeList());
            rolesRepository.update(entity);
        }
    }

    /**
     * Update the list of <Roles> object
     * @Params List of Roles Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<Roles> entity) throws Exception {
        for (Roles _roles : entity) {
            if (_roles.isHardDelete()) {
                rolesRepository.delete(_roles.getRoleId());
            } else {
                rolesRepository.deleteRoleMenuBridge(_roles.getDeletedRoleMenuBridgeList());
                rolesRepository.update(_roles);
            }
        }
    }
}
