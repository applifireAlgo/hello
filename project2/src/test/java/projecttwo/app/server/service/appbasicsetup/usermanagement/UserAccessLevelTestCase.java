package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("ZqdtXoBAUDx8Ynk0nC2zrEklbxlCEB9m4SZgU3tGlTRNN1vkp2");
        useraccesslevel.setLevelHelp("t8Pn1eO0xjZ89EXvORwXa5Cbkmo30ALdkrqKnUUY6nCF4d25iT");
        useraccesslevel.setLevelIcon("W4DAyQ76qhN4cCzTgnyLtVbQMoxzjVBcOcEDAa9AufQtRBf34f");
        useraccesslevel.setLevelDescription("aHbXHfhfL5HKn9Y85TyzqcUKgLjv70HucUqoFglGoBMYa48nyG");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelName("tc97tdBD3Ters08YuEkuMHK7XAjhi9wKOaT9DMluzBWyWp2bKO");
            useraccesslevel.setLevelHelp("iekuBT7eHRck05sbCzDZwCQV6TXpK12a4bvhYBEYCADhtduVDI");
            useraccesslevel.setLevelIcon("TlxztAH5EcokP5HgHsd7DOpa70nkgKx5U0fvlv1G5GVIi87AnT");
            useraccesslevel.setLevelDescription("iLyK26OOXhxOrCDiDA9a73OnEJH9miT4Q9bSY32zo203a2KWl0");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setUserAccessLevel(96869);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 189822));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "JSIMuJ6rv5Xxvf9pNxu5pnw8KAiApjxlEJE69gOCCYQwwzb9Vapsuhrl2W5fftPDjcARIKjJy3V2cSUhL40ThK6HCtadgwyeDEP422AyrXGoaaPxx1XOKoL5zzonCS3LTZDg9eOavVbIyGfrDjQhbhGXSCyNLYhJE7fMXopVvakDIFZjzDufKIH8GmamzpTGiNA9LbvyNs3jIpjAVL9WKByF8dfb0pb5SAbWfEFnuRWvJKIQkUJnnqguGoaqaqbdJ"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "QMGxfpeJnB3zwhyYd2cBa5UDhTtmx3cCOhsDCJx5TSKNaCG2OdnUmRIzVzp4b5D47Fhr3GGPV5VRrtrxkNXIAvcWhByuO1oTX2RdnQX3C7Dd7ZbszhOsO6WU7OGfAfvMoAJgBByMbaqMavScwFcKxyVD20mWZciM79vuBaB1GCEIZu0gN2Sa52o0vniNsIzmxKSnrzSZvWoNuvrOHbVUCjHxqqz7KIkPgOW1YEFzNP3xJjdNjqMuB28lxl1qXyFOf"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "GaoPCeD1zfayru6Z8gcDZXzbAPCTgS7X263ct6ErXOu9dkrOCfVgmNd1nNmXC1nEDv41N1LIErr0B3UeyNXKJQyL6EwZY05EGZN8d6R0lbiFWgm5XBgawnejr3DDXhjC3hecVvLuZHrFqCbwqZyCW6DsanOl4P13rgDYvXN7G7M34hUoRBT3KUqrRtIVVA0VF9MgRMcpWWjMIyKANQEIYZKCuCsf4f8zWfIUs4YT4q6rccDSbuFLGLTEfdIzd2It6P7XcnXlJQkbz7QEpsUgSinYUrL90U1Q7svdV5Vl0m1IBCeCyDfNMCKKTnhviNZdRwco6UcyYvTv0LOinSG3HL48sKShpmD2daCbTordB2eEcyXWJaR6J7NjXdkrcYjTLs81mp0l86sfxB2BQSv0kwwczeUCBtffjBMeqvEYR0orKSoD1MwpJ0jH0Q84uR1n89mgty8UZZyuA9rzjAuP09lVkTiPqq1nDnoqDMaoFsu3sSa2uIWsAdaxcswczoc2l1aPnxhpNuY2zN2KBaXQdyU7bp8CVlG4IcpD9Rp8hC83AWprX5VMLZnbB7pVavKQHwRQvfkDdfBJaBYKcIjH3yXSpz0tepd5L6zXm3es3uDBWw54lc1YYaOB8QbA6UexgdkE4MLa9HUxk5TqJPVmxb3WXdFbz2uOfyZ5fGUEzXK9SlKCOwU4FOV8Tdnxjm9PrCXXpOvMbShO10QoH34aFDMuODTCcnazvgIcuAv6ddDCFBCr5t6XoPU8vccGE5m23rzaQ4bXvkNiGikiz0p74ruWCVtKrBmhE4MpboKafUTs50djpnfgLrGrABMXaHx3GcuRaq9PzMRM3ZJKAKuT2X7c2drjPpx6CPbuGgQRVzTpJaM36PXAhD2BklWsMlHa3Lap0oOzeFZClW1iunosrnZ7V69gIzCWRF6KuAe5zywxux49tB5GSmzkeytWfdDzi5zzaI5DyOQio9OZ1yK0APk8AA8tOhdPABrx8GKS8RJgO2VTWl5KUWkJj0CtD34BNZ8wIsj3TQjTkMKTcwosWMZhOfXWINvq5IiMNr9eGa6egJ2VtdiheENysJWlcvTOk0YxHRn06O9tBxH2z5Ngm6XwLY3V7bPNmDiLcnEGIPOaTdIN09LvexF5mrbZ1li0j8MErnDousaEccaRoKqOb7q5ALj2k756p6WvKeuON5wJqIEIdo85KRtnp3hBypdtYUFkK8uNUJbmY838IISxFR5LgOzSO88yeF7FPLJgbQECrQFQRcKRuA3i2VKvVafj4yogP9x9HTNNuprxy5E7j0pRvVO9CkXJQew4jKgdDUpnhVnF2tCPyP8iognRNL0jKhHgR9cMKQaEfJbBQ1Ge4VfhKkP6ItvfDt06drQFma5ZvHudYI5V5YpBBuMVp3JXEnMdZRqzHZkftRbHZpPMjCXPsnX9rCvwPTNBtSzowQ2AzlOfOrd1DprNyg0KFcSXn1CeC2yUNoecfXaPkpEkjRAGk9cyqLJWHu0zrtJ64cSd5fuT6kPM21N9oBamOBRCA8vEpffDBNEyinXccpJiMrpAwnP3Pcs5Bmcce6MdVKQ66tUHohgAdk7jcwkNhpjiUbGi0o4l5OYLs1m1jdLemKPHJ4R45bHENO1BxamHmIlfqPFuSEWNUk433vdlpOv3RqH0WIJiOA13AdCEwSFaImXJws4MxDAXh0aMunJ98vAQlOOGgmvcdRTwfmQNYjK9MJWV4q7gLL0Mnu4ES0eqUmF3T2of06a27Cwn4DkYXl4zSQNLo3dj61n4nNONr5FAYEQMi2pRnbUzeDhIhJTj1Rc56tzNy0jcgi0Cs7jZWILn0HRLz1hvVFCX4U4rDhyRsD0rBVZOdNGkw1gHyQ0X1tlvO7VaGZntk8E9L7RUkjeK0YLqmfij937F6riAEgWASOV9wSeN5x0h5gB3kbsniXCDb3stBV8LBF5fnZpaOIc09vGklLwM65HGCR1iTGSrjoiDk8NYYvtAojROmckt70GLDwfuIfbSMHiU5RjT5flVBK3wh92UebcVes6jaAhTq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "bH8NunbNXSVkGnqxPckBuw8fa4dOjLRlk4JuMUCtivcfjkRXlj10YkQAObfb84M3JDMcW7QEdMVnhe15qFp7KhwSFeQ7qf2SD4KSsvrjJ6pOkIm4KwN8wjXEaHIS1d8ePtaWLvSMlygIly7rwkXoD1sFmUrUiARDY4FK9Eiuh6cvzOlcm1iCXjZNsdn2lbhux7hw64zaOgIycafO5eUo0bOXzWDEwClKLb6VYKrwmjiP9yByBPhcYG5yuwE2wLzRx"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
