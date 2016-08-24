package projecttwo.app.server.service.appbasicsetup.userrolemanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuTreeId("epRIR3IcXcztdXZL74iupB19VVGjYuQF5udeMoGRWAZba1FSZa");
        appmenus.setMenuDisplay(true);
        appmenus.setUiType("Wsn");
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("WRbB0ETBq6OUg6CNqnqpqg3UtB7JNVaGjwPfMaOlSaZ2FCbR3j");
        appmenus.setAutoSave(true);
        appmenus.setMenuCommands("cGrt4LBazODKsRU41IJ4EgI2xzvdzfBy1dcmmR66em1HsPkJGR");
        appmenus.setAppType(2);
        appmenus.setMenuAccessRights(10);
        appmenus.setExpiryDate(new java.sql.Timestamp(1472019699694l));
        appmenus.setGoLiveDate(new java.sql.Timestamp(1472019699694l));
        appmenus.setMenuIcon("q4nKHLrDu2IxTSPvXmnGEIfsBHWVPpfxABXbjBc7VprHvjd1T6");
        appmenus.setMenuLabel("dVcQBf3ITW9GjHuLmChOOu0wMLz3t8LvdBycRnG1nrXElF8Igy");
        appmenus.setMenuAction("Pw4SDwfsySEc8eJkUmbiztsC7pttelWnrOhPvlS9eyTTMQWQ0S");
        appmenus.setAppId("iD9VwwL3O0EcwfstJiv6ZAfvo2UuvXGSo0o63MDuH4B0S3CWPa");
        appmenus.setStartDate(new java.sql.Timestamp(1472019699694l));
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuTreeId("g5oFKUukB9q8LsBQ1gaVaWHmbTw4nMP1ov54xBVwXcJI3I1IFD");
            appmenus.setUiType("BtK");
            appmenus.setRefObjectId("G1NCtQOVvrSIqbEknZUecj7vYr6GKFePMdhbLdt1770JbMI70D");
            appmenus.setMenuCommands("onccDfGd9H1juOxYLRDFObmHBnd0CfbmZc8Anb1Txp8u97Ndvv");
            appmenus.setAppType(2);
            appmenus.setMenuAccessRights(5);
            appmenus.setExpiryDate(new java.sql.Timestamp(1472019699712l));
            appmenus.setGoLiveDate(new java.sql.Timestamp(1472019699712l));
            appmenus.setMenuIcon("lQBdZUihncFZOokpHPUGd6pqin8kxb1m8OGCRsD4eDdQcQLI5f");
            appmenus.setMenuLabel("SEj1SN54nw9l2ItJWUmqsdHt6yKNY7bWb3PCwhgYTTz5XFkuq1");
            appmenus.setMenuAction("hfpKbndigEefTrj9YffwztMx5ar85dsxEggCTx2luo68GgzJmW");
            appmenus.setAppId("gt1923kmwObYo5ZqjVCROVoWUEhjGlQumpWUWZhpUYiptqPZJy");
            appmenus.setVersionId(1);
            appmenus.setStartDate(new java.sql.Timestamp(1472019699715l));
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "UmrYgcrYH6mnzCD1XiAP4fVtyW1D6KxCMetMYxcrkfnQTgKf3o51vahfOYdYzEHR8kxB64r3MJ88UoG85em2l1yhunF9cW9VPvoqyAU5S6Kj2E7pAQdA1mF2N17m1zXmPWAiIMcqLKJbBfzYxGCPLOMmvO8G2M3al8nRDccV7JhlHsD0JhBUa0ob3YUsTT5dB9GvMJg4s9cI6F4CcPxf9AqqfRCJXbyvSArIxwxV9JDoky60UMCaeTyP0RN9wTsQ8"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "3SmP46qJBrBDyE9iiocqzkopbVeRLyMYi8ggAZ6NHgFaRIuY1vVHJZB1xMqo3rbrjEQDNEzxTssO8f9GrHtslAgdr8HTop34RoTSbgPEzcpNG2XHAqhJ16bUkomT87UEKUL6byrSQaE077lBvLHPoZo7W900SibgNFkpkukMXqnxODmLKU5IGbB7Xnxhxg4FpqWrWzLiclTEyqguR5YU7Z93JqTq5fxbNdtyPfdPQ2swmkprVTGpogGkggUi78Fl6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "6KA8NCQ8nO5v0tWM7LL8GytoA6zbgnRCTs79uXAuqsko7rbtfCuvjISTLwxjrDkUFB3bSfifuwPcTlvJnL0VVl2KDmuejycUifK37ClK64invlNgnqriYH3R9kQjD3gWPkkduNETQ5F4SoF8sdCDgHEiPtpgSPq9n2KgU2O4KVkw2XbM9R7z0cZn5vLe8rBtJSo67C6xPWRKkxaqScT8XxZcSiYGa1v1CZj7Sm5QNaoNoB9AjecCD0FUmvkXzh5kf"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "nHmhmgEaQQD855S9eZSGiGvw6Ae5EqeEjTaLYdzT75TctUYGAKoish5I7XwX9L79S"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "LNRwvzKWK8yi9SOQye9a3Ys9HPD7zShjQ0P088bN3UWFuO1Zoa1l7CrwtYNtbhRQ74Lkfd6CMO7zgF5NwdxyeGeQPg1jiATtQ00iSibZ0SKzLtOGLbR498tsciTktpbEfnzKSn4JNjsxG6YJM3y7VxGGeJxdBIfqO83yyCOJgDtozDtuB45DZWGE4PseThYfPF6HPrzecufvc1dNMxCdp8fJaPzdG3RloDkjrpbqTjQSe8W0zcAkadwV6MqJ0qudV"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "rQQS"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "tBJtqLMG98GqmgLmcVQOBbq2aznKAffBLtrIqVypoEVaxSr2ZigGVEg0zDDpDjLjk3W0fNkiIQbKJ8e8PyoEuJ767hunomscQUBHwxbX9m7uK9JDpECWbwvTr3CxUObMcbk5IfqOmFd582Zv12CwMOKZhZjH83HAyvBR9zJ08WPQovKcIQdsBnioZrWUJh5LTPreADDSNzEVKWjNtpbz6iLx72zik9qMM1sYer7tV0aPR6j96r2PfaRz3TESwpaj5"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 22));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "TjgAWt7l61iE00P5Q5RV37XvorOZKdRqxwRLaMdg70oeGlSBfUdoM4yoXZTO3a8AvxlsYN50blTejnrDZpXfQsvk5o1goG1L49XbvKwyDYBC6abjYhuN3yRpUQM9oGQfOyvzvyMGyAGBhLUgyaGiraj56Uqf6buaJ8JRd4i1qj00CPyyAyJ1H1Y0NspzazWawFEA845zTjdyqOmAk3HLGyoxh63vkt5FM9YpubERCJ7WghJQUWZRGCEMi3owZkqg9"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
