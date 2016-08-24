package projecttwo.app.server.service.organization.locationmanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.organization.locationmanagement.CityRepository;
import projecttwo.app.shared.organization.locationmanagement.City;
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
import projecttwo.app.shared.organization.locationmanagement.Country;
import projecttwo.app.server.repository.organization.locationmanagement.CountryRepository;
import projecttwo.app.shared.organization.locationmanagement.State;
import projecttwo.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    /**
     * CityRepository Variable
     */
    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode1("eIS");
        country.setIsoNumeric(884);
        country.setCapitalLongitude(2);
        country.setCapitalLatitude(3);
        country.setCurrencyCode("vet");
        country.setCurrencyName("0gvAUEsRJFRy0THZVTVjbS1MmQJGDGYOelzxUuWmG4ULleZ6BH");
        country.setCapital("WxZejcFfs3AuX1CWo5M7HZLo6SkxKGkg");
        country.setCountryFlag("2LVXPa9Dc9XKR0dXPDPAEAdEpRT0qBouYV2vPCF4l9VDknKWDU");
        country.setCountryCode2("hLg");
        country.setCountryName("Ae9R9Svcla8QkQqD3XzkIH39gzxjx94Mz8F3dI0cJTKuwOjKzX");
        country.setCurrencySymbol("cOP7qGNvTlVZogaA4AKDLIyb99HJcM7x");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("qjlOlZuTotwdpSpsEtL1uikfI0yS1Bbuoyf0zLqSbiB6Te399G");
        state.setStateCodeChar2("Nw6GU1LCMjBm6ac2ROkkd1VBMgNY5ixK");
        state.setStateFlag("B2Rf7I5WcP1ThjAxScCEMVOneCCBZXVyC4lQlguYzE92SP78X5");
        state.setStateDescription("jvbSwvhNEyqlv4rAnTn5F9klljvUgiPI2Bl9qjE8dgxLSN7wpj");
        state.setStateCapitalLatitude(11);
        state.setStateCapital("1QRcUrjYiXb9QRcPIhvSGKOjIiHqjO3PjdGhBcOGNUHIQszqXv");
        state.setStateCodeChar2("dPArCRL9yogC3Azdc5cBZg8cTkm8TUh0");
        state.setStateFlag("X6VUf47r2RBtjQVSiidDEq5ClalYXXmZ0wq1fdf2swARLD7vft");
        state.setStateDescription("jGdGGbEQuomRWe0WE07LHbkPyUPPZZXvKal4rEsYc9WOZKLGSn");
        state.setStateCapitalLatitude(10);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(2);
        state.setStateName("fzdlUpqIODzGfaV4RMnXdu8ut1rmaRLQxxxL7ZfB39lzbIWZtV");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar3("99gvXNj3WQaiYSeBo4lHzb3XtDGtbBXJ");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("nGTsUd1LuYP2rH8eG9gxt7EpfzT2bFgB");
        city.setCityName("cVRvOjXfVES6Xb3LGv3xmY5LkOpD7uK69GPNJh1b7BcPRvUsLA");
        city.setCityDescription("C4KMeUISEcljrNW8UyoY0EyCzmh5sglRx4lVk396ip80rBtlPm");
        city.setCityFlag("OwzF2dJEAz0EBFgVUF67FFEakihVfFzN15oqvWBjp4ePpE4nuw");
        city.setCityLatitude(11);
        city.setCityLongitude(7);
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCodeChar2("xwQvvtn8eStJY1NjuQBNpu163hZDV3fS");
            city.setCityName("iE6mkaq6itUL7X26uQAVdhiGnYaOgP8pi3k1dD4lnxPfdg5UoJ");
            city.setCityDescription("WruisffsqomUzooRm30ZRbdL7ZfAYjXmG11Ry2VuPiqC8ighFt");
            city.setCityFlag("65AwJcBGfcWs7CsLj7wfMEzhJcn5DpEEDpPpjIvPyUqp0iHr1o");
            city.setCityLatitude(2);
            city.setCityLongitude(1);
            city.setVersionId(1);
            city.setCityCode(3);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "U4wslj3rFNp4Jk20qRqOlkZiqM0mi3DZX4iaKtQNGxCB5hBkWKkhqSUeWNNv5ptTHV1QN3r8azq8Z6z5sAK2haAmpXb4WI0LBxFzYucHUdvclpPZQ9mcUWZdAn2kI9Ou09D9n1b6BhPoNaE2ACucm78PDkWwwYrgCfAj1HbsH1stIbLvXRA71GYpkm12iWoRn56KRWgN88yuBHHoruIrusMm7qdLJ7v6TNpmwAvLFffYMXtLZCydsGLFJ98Ow2jo1"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "vPqCPwKg2muN8KSqP2E3GcrvLuGVqKqRj"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 5));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "8WzGWhA3JWng1J6DYIwGyel60SIigod9vTy3ZpeSjBza7xd4UoZLPpBZses2CtTqNT11goOTE00rl6PVMsoQsOASJV852NLXCOh6E0InDVIb1c3dRrd0tpzJtiguc0VVD"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "fL185yk0XrTSlblxVgMjp0mdUt7VaaBn6VV7P7SQgssMZn9fG1IYoRd7IUv8vccyzmgW9TG4Cs8yyx4XzzzHlsf4x9FSPDZlD6piN6YeWhEwZfb6S9yUhOyHquhQ9GzGn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 22));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 22));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
