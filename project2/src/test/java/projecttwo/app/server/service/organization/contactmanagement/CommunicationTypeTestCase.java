package projecttwo.app.server.service.organization.contactmanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import projecttwo.app.shared.organization.contactmanagement.CommunicationType;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import projecttwo.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import projecttwo.app.shared.organization.contactmanagement.CommunicationGroup;
import projecttwo.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CommunicationTypeTestCase extends EntityTestCriteria {

    /**
     * CommunicationTypeRepository Variable
     */
    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private CommunicationType createCommunicationType(Boolean isSave) throws Exception {
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("QX5rTT8oe1tSN03iWunDYU7LvHcKuamQerHkuHS2NuSxbbo9E5");
        communicationgroup.setCommGroupName("wq0yiYaAr5KSRTmVoWeQcfJXzQxAVAl5QnFLBDqVZHtN64ehvO");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("GgpbwtiYxBG1YGILLkasXTAT588yGHNS0ZrPu9c4OHxjF9YUtC");
        communicationtype.setCommTypeDescription("m8XpJ6Gs8tnIBClr2r3qjorGYDDJWkX1BqWWNOadXAPRK71vlT");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        communicationtype.setEntityValidator(entityValidator);
        return communicationtype;
    }

    @Test
    public void test1Save() {
        try {
            CommunicationType communicationtype = createCommunicationType(true);
            communicationtype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            communicationtype.isValid();
            communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            CommunicationType communicationtype = communicationtypeRepository.findById((java.lang.String) map.get("CommunicationTypePrimaryKey"));
            communicationtype.setCommTypeName("dYdbDCLkMrr104APWpMoAt8SOmaKtPkEFrSYK3D0CTXr285RRV");
            communicationtype.setVersionId(1);
            communicationtype.setCommTypeDescription("ffjGwFJ5caMjmZgso9RwH3EgWr5BJQbIrk2wpha06YKEgTZETH");
            communicationtype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            communicationtypeRepository.update(communicationtype);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            communicationtypeRepository.findById((java.lang.String) map.get("CommunicationTypePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCommunicationType(EntityTestCriteria contraints, CommunicationType communicationtype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            communicationtypeRepository.save(communicationtype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "commTypeName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "commTypeName", "0voZbExxg7rLBWUXSYjjpooBuXUx6fXqUkd9sjFa0S6tzZOaknaiScizQTvEXvsHsEgH2NB4gxgn59ycKF8xPgYCxsQTt3Z3GB2uF2kNvk48AdQzr6ZNhYKPgy3S7Vvku"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "commTypeDescription", "QGwARi3eqdVP8ws8XcJgDpwczBkfX1txaJWXk1lRUNBwMZjgVZ60SEkAb5iSW3YxhtKOBn9FOBN6X9JShkDSih1jsF1qbCn1I8Kj3Uulgku5pEQAJ0pNMoNRbgAfABkUE4VjpKSsbybM3ek5rg8lrXe7ItEkhQWcBAjMZnwfCwwbmCb4cQ7TVRWicpnhV3tYnWoMT0OQPdwNixu4NVqjoeQXUUFHaCUIRUtFnThEP357WosId8abGSItabqZlHF3k"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CommunicationType communicationtype = createCommunicationType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = communicationtype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(communicationtype, null);
                        validateCommunicationType(contraints, communicationtype);
                        failureCount++;
                        break;
                    case 2:
                        communicationtype.setCommTypeName(contraints.getNegativeValue().toString());
                        validateCommunicationType(contraints, communicationtype);
                        failureCount++;
                        break;
                    case 3:
                        communicationtype.setCommTypeDescription(contraints.getNegativeValue().toString());
                        validateCommunicationType(contraints, communicationtype);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
