package projecttwo.app.server.service.appbasicsetup.userrolemanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import projecttwo.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import projecttwo.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import projecttwo.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("Kf5K583OP5D7iUN2h2HZ3iEjac83jIAFkN3rkxlEg6B4VbPdHv");
        roles.setRoleDescription("JUCfMO9lMFt6IC6Luk6ZjLkbmudZXQICh2AaFrTzzHkPNaBKjN");
        roles.setRoleIcon("JTtSWrKdmiaLf2ZRLayr1SNp4VZTs2BRhJeM7lyG1d7CHWN7fw");
        roles.setRoleName("AHQArtMhlQHMk1LTxRTyGJ26lBVPnQvKctUE1uSYyK07c1lDv7");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuTreeId("CroaxbSn1oPu6GM04QfWgyBXIs8bFPLzRMzBXMVPkM46Zidrr0");
        appmenus.setMenuDisplay(true);
        appmenus.setUiType("k7t");
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("y7wHKQD37qsns8QUplr118oTUia0HGdrTaKzkwQE9FRT799akV");
        appmenus.setAutoSave(true);
        appmenus.setMenuCommands("p6ADk9N6hJ2lHaFTnS16PGX8sMm3PrbWPejtjGgc1iarmzQQVi");
        appmenus.setAppType(2);
        appmenus.setMenuAccessRights(4);
        appmenus.setExpiryDate(new java.sql.Timestamp(1472019698621l));
        appmenus.setGoLiveDate(new java.sql.Timestamp(1472019698621l));
        appmenus.setMenuIcon("gi6dhu9EFukPek9UP2TTdWLBQgyS0DPnggIskwDuDSGPVCVsqv");
        appmenus.setMenuLabel("SfDnFfc6QzfuKZ0JJjO4hzHMde6ekPz4mQhK4oxjEtsPoqz99f");
        appmenus.setMenuAction("yfprOwySH86gTj7SvAdahwg6Gvsuf21aCTjZKmcvkgYn1KD422");
        appmenus.setAppId("GW7nTQHAufl8n0rIoI9kXZngvH2R6gscn5aSQ7qvn5TrsDNhd6");
        appmenus.setStartDate(new java.sql.Timestamp(1472019698621l));
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleHelp("OYiY2BJ5BWHF65ReX7pCkbGAKqIxIxvcUCXxMXzvra1rwdrQu0");
            roles.setVersionId(1);
            roles.setRoleDescription("cRjA7PWxp9NUy5nskpXJuYDJdGJqJ7S0a4MD7dCAg7EVK9zJQG");
            roles.setRoleIcon("Cg6feBIL1ecdiZzwf9MOqZ0wuxazx9jEcO7gn4dp8dOBXpvVSR");
            roles.setRoleName("afsCz2DljIuUcTLwWmRqQtK0FpZSvLZMuCd3tFHMZgROE3KPgC");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "SYwYZFnF4OqNbdJCtpVmabYiEkD7UOJtrKvbnx4nlGSdgJhSE6fv9mqRAwzFfoBwyAb7mjq67EXznuzzHmEeOOZmv0erPgNnUIZ55xnlbqiwud69jOwXCUf3wQvUqdmArM3EA6nTEejktrY58dnJL1ZTpan2OfCSSFWeYKaYEqUXHgsscLU0P6e7FrEduZkEAzoZJprd7ZWeOhKmMLG82gUNJBKm5ObzWrv5TvwraJA5tAkMqDbSPPrwCNt5jlP8W"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "Yv1f6CKwsIAMiady1beS8flUtA6PDnB0DoYnLSpUaiE790A1IHa06K6nXCqGORwRwDB2Sk3Ot1UaZGzJc7TkHMOjPJTyBfCS0FWjCmwFRDFeJFgrY0t81SmWivXepBZp5D7auZVQBhsDXyKbHCIAxU21txTYnRP82OMxfAx8RFGKPbJtSZbwPD7zwCf8qNuoOkM40E8zZ5IGGCJ2mGEKooY5XcllmOdyjRppFtLYhyPtv92WtFJAf9s0eDMlC73hg"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "e5AJRJFLfNbIyvV2HGkczFPpvIDLyTpe7x11Dq9g8I29P1Gf7SkAnDq7b5nSzpPLi"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "lA8GPdu3WLjPdMEyHUuLHgiErja68l1zFHlQJEJNxnIPp2ptVhLJvlielltdjsXnk3O5BfP8MDsnAjsptIUSRLLfdt8g8myfyVaFgQY0m85XH7gWrDEZrfoIBPuGeu2PJTsGeitEI4SvKTDytjeKBZpZMk2VgjWSm0lGTjqdilPHM1hde3qzRDXaSSXigEuM1GeiuyTfWBzR0E4TmdBbvMJwLwyIvWJ7c7ryAg2WyFHUchserCMFb38M4iBb0ihg1"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
