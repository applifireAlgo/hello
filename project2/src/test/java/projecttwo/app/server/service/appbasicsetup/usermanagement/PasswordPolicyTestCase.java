package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.PasswordPolicyRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class PasswordPolicyTestCase extends EntityTestCriteria {

    /**
     * PasswordPolicyRepository Variable
     */
    @Autowired
    private PasswordPolicyRepository<PasswordPolicy> passwordpolicyRepository;

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

    private PasswordPolicy createPasswordPolicy(Boolean isSave) throws Exception {
        PasswordPolicy passwordpolicy = new PasswordPolicy();
        passwordpolicy.setMinSpecialLetters(5);
        passwordpolicy.setMinPwdLength(1);
        passwordpolicy.setMinCapitalLetters(5);
        passwordpolicy.setMinNumericValues(11);
        passwordpolicy.setMinSmallLetters(6);
        passwordpolicy.setPolicyDescription("Fzk1DpUNxIs18BOgWdralkL6Wb5oAkX74TteA88DVtruyw7qK4");
        passwordpolicy.setPolicyName("ncwfFxv9K8t9M52rhqsBIC9WWtDyTdDRSZ8JNy5pnWUfNIBwDO");
        passwordpolicy.setMaxPwdLength(21);
        passwordpolicy.setAllowedSpecialLetters("OPvgj34yWPOXhYPiapdPH2QDi5jcKOmpRzHT4DSdSgbQpR4kwt");
        passwordpolicy.setEntityValidator(entityValidator);
        return passwordpolicy;
    }

    @Test
    public void test1Save() {
        try {
            PasswordPolicy passwordpolicy = createPasswordPolicy(true);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordpolicy.isValid();
            passwordpolicyRepository.save(passwordpolicy);
            map.put("PasswordPolicyPrimaryKey", passwordpolicy._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            PasswordPolicy passwordpolicy = passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
            passwordpolicy.setMinSpecialLetters(8);
            passwordpolicy.setMinPwdLength(11);
            passwordpolicy.setMinCapitalLetters(11);
            passwordpolicy.setMinNumericValues(9);
            passwordpolicy.setMinSmallLetters(1);
            passwordpolicy.setPolicyDescription("pPTwqiPU68J9jLrjCEAsxbPKyV5l1Th7rnJ25Dh72dUjRuNNsm");
            passwordpolicy.setPolicyName("f9yBssRLfsHUxk1hz0bdXP3JUdgjIQZRyihvAA0CyvpoteIxxx");
            passwordpolicy.setMaxPwdLength(6);
            passwordpolicy.setVersionId(1);
            passwordpolicy.setAllowedSpecialLetters("mTWh7odfdNodkFfU797T8C2Q99CUe5G8MZH4zYXIB2yCNHyChg");
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordpolicyRepository.update(passwordpolicy);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.delete((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordPolicy(EntityTestCriteria contraints, PasswordPolicy passwordpolicy) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordpolicyRepository.save(passwordpolicy);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "policyName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "policyName", "gSllGeHsWYDEdneVP5xWG8bNRvYnt0QHOWM2GvZHwYhdfkasILJUfCil2zHYsajeD1XaqUAEFpOmMzXDzFRCJCYgj7Sow4WuyF2v2E3jKBVVawqyEOTNpEQAXCEdMCOWwnwPY1waNSUbTvH2W5YkLo7oDyRquWSwj5N3zBdX9vzII4JzOqmWlW4nJgRIa0reY7VYQ2JH7jIGMd5XWebOxEUDIe4pkJipsHsEH0AgdnHcaaEC8LDSuc3bFPsB0gfSM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "policyDescription", "VINtr6N6skPeqTCQtuqdWE48HWHRbzz8WnJnzmBvAR5MI5x2PkmxKem0RixLl7lHK7xaKUyUwJ8I83QdTQUWMEpmso0i1yR0ph6Q8bZIiaLdXF2eBovAOZegZOAptZbbodADUzjmnosLX2os28pn0hYZVKrsMOTLqRdSPhSAg8ecs1oPzwdTvfzo1eCek8CDavzdzWONYCAdETiWNAX5Zn0rqNnz7GvFENLSzKMoHQHK2Z1effxOT9DlhlVjvj687"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "maxPwdLength", 61));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "minPwdLength", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "minPwdLength", 12));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "minCapitalLetters", 22));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "minSmallLetters", 18));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "minNumericValues", 19));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "minSpecialLetters", 21));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "allowedSpecialLetters", "IgPRs39GqKtUF2TGNIEezExZSFBseU5QWeIqQxpQQY2zMj6LwIV0jLHBNrrwAh8OJqICyL9YuW01vEEIIA7uIdEYJhHJEEkOT0UgGNQruzQGsMUUPzoX8hDcEPfpayiJ2KIGIjwwzwfK4ayp9EzHEaTqRWj2ZRN77tSuZrG6dhZaRAU8BwyO3Fb4KHOsENLkSJeYFdry8P8cilGh98vAKEn9v2f7Jt7qrBg0XTRFuqcnFyfTjUxhy2x1EzQHNCmBS"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PasswordPolicy passwordpolicy = createPasswordPolicy(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordpolicy.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 2:
                        passwordpolicy.setPolicyName(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 3:
                        passwordpolicy.setPolicyDescription(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 4:
                        passwordpolicy.setMaxPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 6:
                        passwordpolicy.setMinPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 7:
                        passwordpolicy.setMinCapitalLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 8:
                        passwordpolicy.setMinSmallLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 9:
                        passwordpolicy.setMinNumericValues(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 10:
                        passwordpolicy.setMinSpecialLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 11:
                        passwordpolicy.setAllowedSpecialLetters(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
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
