package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.PasswordAlgoRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.PasswordAlgo;
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
public class PasswordAlgoTestCase extends EntityTestCriteria {

    /**
     * PasswordAlgoRepository Variable
     */
    @Autowired
    private PasswordAlgoRepository<PasswordAlgo> passwordalgoRepository;

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

    private PasswordAlgo createPasswordAlgo(Boolean isSave) throws Exception {
        PasswordAlgo passwordalgo = new PasswordAlgo();
        passwordalgo.setAlgoHelp("g6kzz9oTnEPcXmDHBoyle6ltGKChkWqjouyfcR4RCpezMgDVQg");
        passwordalgo.setAlgoIcon("YQOzjrrcfKswnTUzzb8OeZwCsjneFcN6XeHiHF5HsauVLkaWga");
        passwordalgo.setAlgoDescription("B4UaVHIcXFdFDQoVvmgknb7ew1xC2f01WxfOcELFk6blvbnshN");
        passwordalgo.setAlgorithm(4);
        passwordalgo.setAlgoName("X8vUSxqH2vZ1EvS2tf555JvgLQVpV3wLKkZW9790Mr5gHMRcwq");
        passwordalgo.setEntityValidator(entityValidator);
        return passwordalgo;
    }

    @Test
    public void test1Save() {
        try {
            PasswordAlgo passwordalgo = createPasswordAlgo(true);
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordalgo.isValid();
            passwordalgoRepository.save(passwordalgo);
            map.put("PasswordAlgoPrimaryKey", passwordalgo._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            PasswordAlgo passwordalgo = passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
            passwordalgo.setAlgoHelp("egVopX5in13a3bYy14JUgb3aO3MU6ouh2v2RKU3277aBq2SKZT");
            passwordalgo.setAlgoIcon("Ef89QXXCJ8mcBS11LD3ZbiXOJRirKRQ337mBpzasNpzzjlhKHG");
            passwordalgo.setAlgoDescription("JzEgAjp5MNZCDHhQeya356fPc9Tq6BuXZYSB3I5M0CCFitRddL");
            passwordalgo.setAlgorithm(2);
            passwordalgo.setVersionId(1);
            passwordalgo.setAlgoName("PcqUsMA4XfX8l8NDx6nj3qDq7kEzsT01wWL47KdDHNSONFcNYh");
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordalgoRepository.update(passwordalgo);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.delete((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordAlgo(EntityTestCriteria contraints, PasswordAlgo passwordalgo) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordalgoRepository.save(passwordalgo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "algorithm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "algorithm", 19));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "algoName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "algoName", "8mQRKyse0DAQ3mX1joUJKMBePNroVn4g4ztlWp3BNoqkunF83wAjMllGjzYpcdLIvAGUJGOq7szUeq8HTQyLID2eAOGAq9Eh8vYap5QTAMyFT2o6VcdKErGkgqCOP2VpkhiGP3ZwyGIzNz7L9DUbyO7YN3Pte3jukD7O90J4wuCXaW7dLmfgyP7TiZCsysGw8L2PdpQoSoWUaHJF2R9D9uYqQUoqZL9Xq6kGTSE6K95IFVBjfFvqRtbSRQN3HxrxC"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "algoDescription", "LUZVeCYNfKz3YhiDA2oEh8UjqoE9pOMZcKWHsMxl3ZIezZc07z0bWMweF4GJi0C9WfST7EvTrYwaHz5UkwPREMyELYLVc45MHYEVSOoHzYh5vVs7s4RRQfWUZbzaW3T71vXDqWB4WjYPIGAOZwQoYQkEiERJW53YwjjdeNVhC8P5pCVYtzqmLTti86yaH1r9NKmd75CrMmIRBxpkCu5FGbpXkXk4nD5qqTd7w8wuNflLSRTiyMdmMCIZiiOeN6iei"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "algoIcon", "OjhbyaaXkqANCL6bGzuD5ZQ2K0c8OgO77uzpRCUbZR3sVT4Tsps3ClTotMBfMxpIT"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "algoHelp", "ceiGSacC2CIEOio30d1msUCm2ixzFxHkJm9vVWSplOuJfhCIt5m4AHA1owrVWa5J9rqP2zpQ8ubA4gdLItBIUH8SSAgE2I886cY1vgVGwPRd5zF90MDiog27Ha9DGU2j583kuceHbIBtBAlhjq0aiIck64h6erxLpfE5keqe2vKs2fF0dKE07pyEMd4Zo86cM4vW1nXwkMhXuG03QZQR2ICjBACKbplFaixqga4Ob6Tpk0cx0HtLZKiS1YabvilEN"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PasswordAlgo passwordalgo = createPasswordAlgo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordalgo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 2:
                        passwordalgo.setAlgorithm(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 4:
                        passwordalgo.setAlgoName(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 5:
                        passwordalgo.setAlgoDescription(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 6:
                        passwordalgo.setAlgoIcon(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 7:
                        passwordalgo.setAlgoHelp(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
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
