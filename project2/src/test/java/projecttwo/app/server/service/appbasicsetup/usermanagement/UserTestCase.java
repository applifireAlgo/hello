package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.User;
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
import projecttwo.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.PassRecovery;
import projecttwo.app.shared.appbasicsetup.usermanagement.Question;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserTestCase extends EntityTestCriteria {

    /**
     * UserRepository Variable
     */
    @Autowired
    private UserRepository<User> userRepository;

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

    private User createUser(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("T6xbaP5FLZalNaj4XpjgsAlJw6kRBr8NRgz9Z9FRO0R1OCk6an");
        useraccessdomain.setDomainName("N8uQ3tHgqDbfntc7rhuTSU2f1rU5p4nbFziNvHmje9gUEVGj9J");
        useraccessdomain.setDomainDescription("Q9MPiwkqSI39BTagasTONYaz3v8ovrO7eSpwhwcceV7x9kmazO");
        useraccessdomain.setDomainHelp("w7evXTJY0V3OrUXYzuMF4Oy2MklScHkQiKnnp07AnphWKKdkRv");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("0R7Wia33PYruMXmZ9iTNKBRdNc5uOmF9harfQGMjfeaGfuHzpY");
        useraccesslevel.setLevelHelp("8EeMPG5ctDOd5fjaIqCGA8p3azdWd5KLf0UTdUOOcDmLHYmCGB");
        useraccesslevel.setLevelIcon("hUBmWVQZEqEUYD8aST4qmbTT4Tja4QxcRRrU0XkFvHJVAUO6VV");
        useraccesslevel.setLevelDescription("Hk5dvhcc8x7BV84fyakuLsklzDVsBmOQcswrJxPXwMFE32nGVe");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        User user = new User();
        user.setUserAccessCode(27822);
        user.setGenTempOneTimePassword(1);
        user.setSessionTimeout(2470);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472019689164l));
        user.setPasswordAlgo("iqCDyJ4HmnS6K5zqPbTtwxnJYExztWglEESJKTTOqnK98WKSZV");
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472019689164l));
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("Ew1cBPcgNrtvPNkVhD57ODE33zvmvZcdUDibpHP2q7oSRTGFCt");
        Question question = new Question();
        question.setQuestionIcon("yiGP9my6Eg65DltJlRwE8a5HvixQYu4d4iDGQawEOlgTfFvRKv");
        question.setQuestionDetails("X2YtHmFZQM");
        question.setLevelid(2);
        question.setQuestion("2hCeotpkhIvmktOMLwFDzdCFn82wR7TLXA4aXMA0N5SwNRRhZm");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("ubBbxVJxMrEOi8jnCifC7im4uLTn8RiN2HHQkw5SA8tScBbo2c");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("1Y2m9LgTiEeFlaYIAL79cKGxuhFhf0N4");
        userdata.setPassword("TEMk2I7BaAtjAeSRhyDCteQzdGzQ4POiaurc4Zafx7uGOIHmZT");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472019689408l));
        userdata.setLast5Passwords("kgf12neBQkM3rINlEadQQjbcYJ8fRwpmhOLsQGnVm88Upz2zS9");
        userdata.setOneTimePasswordExpiry(8);
        userdata.setOneTimePassword("Q9pMkx6E6o8LoPw4pd2JS0wQS9PngiX1");
        userdata.setPassword("Axh1puVnBWqmH8JqrmSazgPVQ2KHajkxcj7qK05NqYqrRdvxNN");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472019689420l));
        userdata.setLast5Passwords("KlPn5YeLPIQnYWJHpv2kmGYF6cuxo29FZQsPz4YFPyoeULTbBA");
        userdata.setOneTimePasswordExpiry(2);
        userdata.setUser(user);
        user.setUserData(userdata);
        user.setEntityValidator(entityValidator);
        return user;
    }

    @Test
    public void test1Save() {
        try {
            User user = createUser(true);
            user.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            user.isValid();
            userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2findByuserAccessDomainId() {
        try {
            java.util.List<User> listofuserAccessDomainId = userRepository.findByUserAccessDomainId((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            if (listofuserAccessDomainId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2findByuserAccessLevelId() {
        try {
            java.util.List<User> listofuserAccessLevelId = userRepository.findByUserAccessLevelId((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            if (listofuserAccessLevelId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserPrimaryKey"));
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUser(EntityTestCriteria contraints, User user) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            user.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            user.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            user.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userRepository.save(user);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "userAccessCode", 117245));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "multiFactorAuthEnabled", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "genTempOneTimePassword", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "allowMultipleLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "isLocked", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isDeleted", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "changePasswordNextLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "passwordAlgo", "MS3cIRmrZraCmPGddggVUvqA2p8VpOANKARwEZzoZFHLq5hsaRhvuP8zkjvdVywqV"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "sessionTimeout", 5219));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                User user = createUser(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = user.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        user.setUserAccessCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 2:
                        user.setMultiFactorAuthEnabled(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 3:
                        user.setGenTempOneTimePassword(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 4:
                        user.setAllowMultipleLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 5:
                        user.setIsLocked(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 6:
                        user.setIsDeleted(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 7:
                        user.setChangePasswordNextLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 8:
                        user.setPasswordAlgo(contraints.getNegativeValue().toString());
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 9:
                        user.setSessionTimeout(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
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
