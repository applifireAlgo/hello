package projecttwo.app.server.service.organization.locationmanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.organization.locationmanagement.TimezoneRepository;
import projecttwo.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    /**
     * TimezoneRepository Variable
     */
    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("O60LvXfR4CvYudBySFyCKAInHyAlLtHHqkkRx9MC1axIpztCCi");
        timezone.setTimeZoneLabel("aKl6hMcELlhAMhxxFqfaYI8UfJgC82BJ3RuFINsH08AjlvnKry");
        timezone.setUtcdifference(6);
        timezone.setCountry("vheWFrcqXIDUbo6ejlFdiHSqbUzHSsDyg4t007Iak2gMulOnQl");
        timezone.setCities("O22NAfHmPgS7aBTGSAKPzZdXdzmUHseobmYhYXUnxnbyjB1uwk");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setGmtLabel("qxe8igasTKWhmxmEgOZRgmBOcEjeQfB7mIMHbw7DSPmwSPe09g");
            timezone.setTimeZoneLabel("b7qhetLFJf0zqq2EjL4axGY2217lDdxT65DpitiamzBXNLYryj");
            timezone.setVersionId(1);
            timezone.setUtcdifference(3);
            timezone.setCountry("HDoQRPHnAm7u2Vc5Iwfs2JqYMmIssV0HG3gMfva6zgXNH6fSG8");
            timezone.setCities("DsTMwAj3LgF8wXtCvmi8cVHA2O6qYblQQUV5FKwZbSTExie3lK");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 16));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "ysbVVkmrZNdJn2ollGX3oDi2i7QT29dzaFi8uz2BBPBS9M4BlyPK4Ny4xH9YBf1b6kw7AtsjkmdGHTw0tPaz29TA0eJ4X7g2ESB6iJ83Ogf87geLe8TexlBFskBabY5TDu4l9TS1U5S1ZPOcoChwmb34BUoEuRhDNHtohSn9Y2yA7uYxx9FSWGArxCBCqbfcIbGn3y9eGXDl9izlva8fqzkMkFXTcqFaDeuIMuAbUxfgtpzPhjy5GVHywrIob0qHo"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "C88QBa4RGm4SF8tyM6IfGTGIJn5CMiIf4YW8UNFLf9Glr4jVvJmfVRRtVp1KZz0Z9QHzMWJw0SarD9u2BQ2dSdKBA0jjwmXsj4V5sZjJqi8gcpqfWUgKyqQU32LLCTdiN9qvt65y04b9aAcABR8gDI5ckW7po1VsDywo2f1CqNMJGjAqKqmEAZGppoG7ZBM6xdOFycIvAyBH83auWwB7ef32MZ5JdXmY6A3cRjg3oTV9nBXdNYR8mqmHr6C5ChDz2"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "N4shGncaZ9NIluBVdfnQf7bSX1oRMrvA1esrIyyKtZL2WHukXpJK4VmdrvJo6nk0X3OLFB062rieokUgnYX93r0CY918ToWQrJnEPlJLNuMB7cb5m5fOkL4lGOsdz20mBqaxDBbMEXRO5XKJzUUxIWYV7Ub0aXAwW9LqHtutui8RqDv4l8lYtacIxT3JeYydQ5nytxmnsk8GmroCBtIGOvWJBqEnqvtNKVDhhpnmnaGya4QF3DOGOsIuhE3rSy7r5"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "JpanuoLy2gTkOZVAZu74jb2M6de8UA1FxYMZNBsVGrBGbcDXcQ95lsjPAwxgYrDNJxgVcRsdxg7zEv38NBLFyrLVcPb43SHEpMRVvM58u33y6gqTMWLVGAv13hNJGj2zVCayzgDoHfmVCKgXFN6hoZ7NGWArxn05aaOsYDGtHHrDDf0wLhOThsA4lZrUmo4NYwl7khOfS2F4irA5Tz0sbokhCTI29oH3g9H6lpdzQYCoraEhsU5MyTfJQs2f67HKX"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
