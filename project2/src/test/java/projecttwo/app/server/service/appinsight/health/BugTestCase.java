package projecttwo.app.server.service.appinsight.health;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appinsight.health.BugRepository;
import projecttwo.app.shared.appinsight.health.Bug;
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
public class BugTestCase extends EntityTestCriteria {

    /**
     * BugRepository Variable
     */
    @Autowired
    private BugRepository<Bug> bugRepository;

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

    private Bug createBug(Boolean isSave) throws Exception {
        Bug bug = new Bug();
        bug.setBugName("Td7C8khTGMU0nF6RIeJlgo0ogzCQ1BB1onzUJlNLAzfPJB3uZa");
        bug.setComponent("5rTz6JtuyAk31KJ3QF0KXXVWYfHJJSWWnLADbDiNzM18bGDz8D");
        bug.setPriority("L7v3xLBTVXnYHSA9WXcCiPjbOGeZAnoVaG0d9cLBgDUKj6WrrV");
        bug.setEntityValidator(entityValidator);
        return bug;
    }

    @Test
    public void test1Save() {
        try {
            Bug bug = createBug(true);
            bug.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            bug.isValid();
            bugRepository.save(bug);
            map.put("BugPrimaryKey", bug._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("BugPrimaryKey"));
            Bug bug = bugRepository.findById((java.lang.String) map.get("BugPrimaryKey"));
            bug.setVersionId(1);
            bug.setBugName("yAGtIaxrWT4CC1oyYZaFqimFNh9H8FbzWt8BCVn63qrMkPkqyL");
            bug.setComponent("8jybXhPnmxJYHATMqP3jf8MiY9MhXANfNfXTRBBRtVd4edjsI3");
            bug.setPriority("bTQo55QUGpzTW4Y1Dplq3nRJ28ks9pzXkImfe21lSQqagiLGCx");
            bug.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            bugRepository.update(bug);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("BugPrimaryKey"));
            bugRepository.findById((java.lang.String) map.get("BugPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("BugPrimaryKey"));
            bugRepository.delete((java.lang.String) map.get("BugPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateBug(EntityTestCriteria contraints, Bug bug) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            bug.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            bug.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            bug.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            bugRepository.save(bug);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "bugName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "bugName", "DiSug7IXjiJIacy7rJubZY4i94ABBHPMOt4jOItMiGsRDxLlyxeNGCCopIf6iviHy2wwMwRIafINqoN3Ack1tr2rQiY2KJEn2oS5DtsPOeMAVVGNAEwwDZuc6mu50GzrZRXbVlYEEbB5l6z04WXEoL22YHIYdv71FKKGWidAMSk9jzmnApR7kV8FPCVl1kN4twKQp9EpxG7tZbUOrb8hULgMRmzgcjjOXI4hmNU4uS2GX2SEYpR6pXysDQFYDoA7V"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "component", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "component", "FtHt4D9XtCyAiqrPKau11nrmehZyldD45m4toeh8SGmTAzpgoM9stDeWq0aUBeA5umCvB3gZb0U4g4JGefF0TbN7HSYRjFZNRq4J7jKcHOUFBR28t4tIW15wbiVxk6eYWFaFzUmHxDI3le48XjpDXia8wBhn8GGKNw6MRfw9v4kOmDjHBDBTNqqJA6d9NA0viFrKXW3NgyjqMiNi4r4cipP4wgN8zz2dazOpGVHtsp7wRrt3G4klyLm2vwDH9UZ2e"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "priority", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "priority", "qxqKSGHquouYu2UUztZDAXXoBFLY9E7rbHaktPhzX6hbrI6zBVw9mz5Jex9jaW0LUKHCMKZvjRsvEN6qhmKTlPT7iXwOnQetsxpYXAT824RwYrAh4qh6hOGv0mk93XC8vCJtgdaYBFqOFJnXtVqhGCSBDG3emO8G38IopIHd5WXyWaeL6OeYdWI6y1Zed9NCD68QCUUHnF7aT43LufdxtD7DKzajwTpfgApGCR9RUWl4DRMEboLWGMdV7YyvIn4Rt"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Bug bug = createBug(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = bug.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(bug, null);
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 2:
                        bug.setBugName(contraints.getNegativeValue().toString());
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(bug, null);
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 4:
                        bug.setComponent(contraints.getNegativeValue().toString());
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(bug, null);
                        validateBug(contraints, bug);
                        failureCount++;
                        break;
                    case 6:
                        bug.setPriority(contraints.getNegativeValue().toString());
                        validateBug(contraints, bug);
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
