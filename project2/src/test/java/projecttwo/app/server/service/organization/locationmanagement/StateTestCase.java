package projecttwo.app.server.service.organization.locationmanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.organization.locationmanagement.StateRepository;
import projecttwo.app.shared.organization.locationmanagement.State;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode1("Vof");
        country.setIsoNumeric(6);
        country.setCapitalLongitude(9);
        country.setCapitalLatitude(9);
        country.setCurrencyCode("Se2");
        country.setCurrencyName("duQFhaN2Vq90ZfpOAFhgmsQ47fYLOIwGliX1P36rPcSaICfqjR");
        country.setCapital("8nMgxOkGmz2uCjkTp9ejP5b2lvmHUjBs");
        country.setCountryFlag("2AvQwcsbDVxQ2fSpb24GzfHvgMWyX9Il68iCH7oPdokLUQgddE");
        country.setCountryCode2("gah");
        country.setCountryName("17Gu8bPrZD9Yg57stCVG8POhVpmRH5YLpfIEc80gpQWjomfIGk");
        country.setCurrencySymbol("JAc1u9HZjvBsXr5PW6redu5JdsoYF4kK");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("JAy1zI4NiPmOOKuMYNEuKZJlrKn1sCLBQN0FdI5FWgUb6X1beG");
        state.setStateCodeChar2("TbHMjL86BlroavCmqA6MkGbXErxMDuc2");
        state.setStateFlag("vApwH99KexRibyib8h2me3OASUQQBrsp9zfd76bzkP5r2HFa5i");
        state.setStateDescription("XlS7o4svqAr9B1vl3b0YSxmPHaXnbP6VaYQZ6dcdZBN9gVv2ut");
        state.setStateCapitalLatitude(8);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCode(2);
        state.setStateName("MJVFSWS7ftHTvajJbMpUDTY8xVBI6Yp4XkFSuL0OJ5g4vquPkB");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar3("B88G6XD5NtcwqdxgLZhy3eXu1CpdE4LM");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapital("gHwAuck8ylR5qeJz4XrLtNSlgKmgnXzw6It1sLwvuRLXRLfH5d");
            state.setVersionId(1);
            state.setStateCodeChar2("m9DILGYqCcemT1s6Fx2iUC7OkMi18KuI");
            state.setStateFlag("0xae8pIYSLZqj13NiBf9Wf7x92E1hGsjoOj1n6qH7r0UIrBUx7");
            state.setStateDescription("bgmq6uCBsVnJUEmVUxztbgntMKi4PVenALg0qnPTskVk8AsGJA");
            state.setStateCapitalLatitude(7);
            state.setStateCode(1);
            state.setStateName("uAssIFZ9hNazlgAIIyFfqLgyBktZuvOpHw7jLqthQGQvylXM75");
            state.setStateCapitalLongitude(8);
            state.setStateCodeChar3("oHClAxab2cKscHXl3y1Zt4qRsQLCQ39W");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "QA7pTLmXXfL30qA4xJ4pr0flPVppK6PBDzfh6DFxxpfBlvDgiBTXV5cGTi8l4dkvue2ywv3I1gwGBK7jyQsUevgl3fcXqbKE5ncZXeyR1BWyw7JQ7ZwbYs0NGfBLmsO7h5CAscZckcjOA8e5FNtfF6pe68TvquSp7lTqo5iv3y1dGTHcLXkFEDyp442MHdmSnCXhtWiFDMAl4cLft0mfRnPKFOlwEDmPmWDBtxkh2f9Y0JI6vHvWWuFm1CGPw4Ayt"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "6qlcVEt1K0ddCz3sD7zDaxyD5NYHM1eXc"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "yNO0PODSbSzBBUFHZtMnyKGHwKVD5ELgq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "o4ajouyvfMk6LPeHKYjTmFqZroFbBPfS9OlWf56rF8bNiPIB8unhTDZXVtkKiLnTAF8WH0r3zgYkCPfbNENAKcEkU24Rdo2qfnjdo8zMxWpQRVORj8efNcLwVf07KCDGo0DcKUnk9aKHi8dVb0D9rg7vHBBYbVF3UjBrmAT40VMRolIvBYUE25JriEHNLHBEVCh99MIyUHZl5DGAgDImwm0pEYbFE1ertL2fSfOHaxA1RJ1W0MsqruHLUmHVDc2g0"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "6Dq5bGaoLEStzYDRFIwRry3b8lIw31l4u4HucyZc7VOkugiei8js52dR6zGGI2crvHuUN10FtKJSjYvvfigc3TZUt8UIOlaElMwNLu2QRqcKEbLfGePxRSI0ravmmg9zh"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "72SkO0HALDPggOvCe1VPvKMmgO7q0rAFxb6lU2Ih7Rj0iTP5q4JRrV74skLd5EwXLotJ7xEOG2YqKBVPVIhCmJxnAZ5gQ2j1h5alQcdN8ldj9WhXyNGZlYSobULLUe9oy"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 19));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
