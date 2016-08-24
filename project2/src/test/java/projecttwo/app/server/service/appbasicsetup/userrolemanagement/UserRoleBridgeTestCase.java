package projecttwo.app.server.service.appbasicsetup.userrolemanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.userrolemanagement.UserRoleBridgeRepository;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
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
import projecttwo.app.shared.appbasicsetup.usermanagement.User;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.PassRecovery;
import projecttwo.app.shared.appbasicsetup.usermanagement.Question;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserData;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.Roles;
import projecttwo.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserRoleBridgeTestCase extends EntityTestCriteria {

    /**
     * UserRoleBridgeRepository Variable
     */
    @Autowired
    private UserRoleBridgeRepository<UserRoleBridge> userrolebridgeRepository;

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

    private UserRoleBridge createUserRoleBridge(Boolean isSave) throws Exception {
        User user = new User();
        user.setUserAccessCode(15188);
        user.setGenTempOneTimePassword(1);
        user.setSessionTimeout(2754);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472019700688l));
        user.setPasswordAlgo("mbpfasrOZWV04eaY5GA3uoQKiMnTXRzCMySwunSV0lx6oJrMqD");
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472019700688l));
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("QN6pqPgEr9onRltoHpVrEoBXMqoXUcKaBnE2wnPMi87IUbIvHw");
        useraccessdomain.setDomainName("Slqw4jfwIAlcU8Ltgmk89uDVeiIGDss06tDSrBmNrPuRkn7dq0");
        useraccessdomain.setDomainDescription("jJPYWweuN9nhwiUynvJNcSh52KpyFXfhOqAvZ3760EYp2wf8vS");
        useraccessdomain.setDomainHelp("ys4lu5CCbzvddgOUJvQZzmBOSllyN0Wn9kIwwbhpE1eFF0FOTK");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("012gWGcRt7VZK97tMC8AvOBgu4qWRQZiUzElMvyqxvLi8JnL3V");
        useraccesslevel.setLevelHelp("0fQqt2YS6FdXgXolpRibU1Z8hELMRVqhWrQTzPNKyfaQxS1bBb");
        useraccesslevel.setLevelIcon("TwjO5LV0Nd5RJJ4vEife0KJ5QSPx3ayuLRwSG2T9aOoAQ9blSe");
        useraccesslevel.setLevelDescription("qDCWNkPk08YiAt8Ooy4hDX7bnnHbD6z3UfeHmukQhftvlNvxoG");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setUserAccessCode(902);
        user.setGenTempOneTimePassword(1);
        user.setSessionTimeout(2032);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472019700697l));
        user.setPasswordAlgo("SsCUnQePlOsbCxNSz9EAAbKvHWPGdnSve3k95McAc6rzLHMTfS");
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472019700697l));
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("W7NX3hHz5nCFl6Upij8wG8VOuGpkDgh8ngNaaFFwNVKtLQ0e2a");
        Question question = new Question();
        question.setQuestionIcon("Ym3YZdapcLUqmTIF2iYnr1gz7pKtOyTAVcSjuaFaGX2S2IpQNA");
        question.setQuestionDetails("qG4lymmbOP");
        question.setLevelid(9);
        question.setQuestion("fsdlldR8PLbh8ev5CKtlmUauKoa0lvdo8H6dVB3BZIXpQFllpj");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("hMGjhOijVRJGo042xOSOtl7sfX8EDhyG9OgA9JemNIPyDOsgL0");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("8gRIhsl2SduqxmGBMKqrOzhpXrjpTDx3");
        userdata.setPassword("9vuFmq1E35DaTIBAGFGiJiWvaI4dkBu8KvrfsY6cI01ilOKqek");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472019700905l));
        userdata.setLast5Passwords("5hNOCzlAeyyoJoZ9GQFworhwnde6Sl7bEoBHMho6bKNLkOBN12");
        userdata.setOneTimePasswordExpiry(3);
        userdata.setOneTimePassword("64VZgKTOnua0DZuQ5tcjPrNgujJ9Q7ro");
        userdata.setPassword("NqROvGMxxgnSV5pSz5yzW0mwGT0sLNrXrKNO0XTQxfobUDEUi9");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472019700916l));
        userdata.setLast5Passwords("uhZu6LaTDzY83wrz5RpeO7MCsF0rCuXx3WODp0U4Qh1zvncZnS");
        userdata.setOneTimePasswordExpiry(7);
        userdata.setUser(user);
        user.setUserData(userdata);
        User UserTest = new User();
        if (isSave) {
            UserTest = userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        }
        Roles roles = new Roles();
        roles.setRoleHelp("aqxfjPIWlkDv7powFO8dVGcDmKjl8YYme61IddliDArXfrZxVi");
        roles.setRoleDescription("J51rZXl7n5UjqhWb7uTu1yaQPkYhZ2HQlLot7PSXpb39fc6VN4");
        roles.setRoleIcon("m7P3Nj481lmLFPzAX442roq0q27ivdQZNAdId3q3S60GcyDIOL");
        roles.setRoleName("xk6vPHpHeEZO94BmZ3ET2LraKCsxvGRgxTQpCGSoMyHWiXZUGg");
        Roles RolesTest = new Roles();
        if (isSave) {
            RolesTest = rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        }
        UserRoleBridge userrolebridge = new UserRoleBridge();
        userrolebridge.setUserId((java.lang.String) UserTest._getPrimarykey()); /* ******Adding refrenced table data */
        userrolebridge.setRoleId((java.lang.String) RolesTest._getPrimarykey());
        userrolebridge.setEntityValidator(entityValidator);
        return userrolebridge;
    }

    @Test
    public void test1Save() {
        try {
            UserRoleBridge userrolebridge = createUserRoleBridge(true);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userrolebridge.isValid();
            userrolebridgeRepository.save(userrolebridge);
            map.put("UserRoleBridgePrimaryKey", userrolebridge._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private RolesRepository<Roles> rolesRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            UserRoleBridge userrolebridge = userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
            userrolebridge.setVersionId(1);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            userrolebridgeRepository.update(userrolebridge);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<UserRoleBridge> listofuserId = userrolebridgeRepository.findByUserId((java.lang.String) map.get("UserPrimaryKey"));
            if (listofuserId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByroleId() {
        try {
            java.util.List<UserRoleBridge> listofroleId = userrolebridgeRepository.findByRoleId((java.lang.String) map.get("RolesPrimaryKey"));
            if (listofroleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.delete((java.lang.String) map.get("UserRoleBridgePrimaryKey")); /* Deleting refrenced data */
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserRoleBridge(EntityTestCriteria contraints, UserRoleBridge userrolebridge) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userrolebridgeRepository.save(userrolebridge);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
    }
}
