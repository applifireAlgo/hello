package projecttwo.app.server.businessservice.organization.contactmanagement;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projecttwo.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import projecttwo.app.shared.organization.contactmanagement.CoreContacts;
import java.util.List;

@Component
public class CoreContactsBusinessServiceImpl implements CoreContactsBusinessService {

    @Autowired
    private CoreContactsRepository coreContactsRepository;

    /**
     * Update the <CoreContacts> object
     * @Params Object of CoreContacts
     * @throws java.lang.Exception
     */
    @Override
    public void update(CoreContacts entity) throws Exception {
        if (entity.isHardDelete()) {
            coreContactsRepository.delete(entity.getContactId());
        } else {
            coreContactsRepository.deleteCommunicationData(entity.getDeletedCommunicationDataList());
            coreContactsRepository.deleteAddress(entity.getDeletedAddressList());
            coreContactsRepository.update(entity);
        }
    }

    /**
     * Update the list of <CoreContacts> object
     * @Params List of CoreContacts Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<CoreContacts> entity) throws Exception {
        for (CoreContacts _corecontacts : entity) {
            if (_corecontacts.isHardDelete()) {
                coreContactsRepository.delete(_corecontacts.getContactId());
            } else {
                coreContactsRepository.deleteCommunicationData(_corecontacts.getDeletedCommunicationDataList());
                coreContactsRepository.deleteAddress(_corecontacts.getDeletedAddressList());
                coreContactsRepository.update(_corecontacts);
            }
        }
    }
}
