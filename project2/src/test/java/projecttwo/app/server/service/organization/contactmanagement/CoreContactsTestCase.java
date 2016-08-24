package projecttwo.app.server.service.organization.contactmanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import projecttwo.app.shared.organization.contactmanagement.CoreContacts;
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
import projecttwo.app.shared.organization.locationmanagement.Timezone;
import projecttwo.app.server.repository.organization.locationmanagement.TimezoneRepository;
import projecttwo.app.shared.organization.locationmanagement.Language;
import projecttwo.app.server.repository.organization.locationmanagement.LanguageRepository;
import projecttwo.app.shared.organization.contactmanagement.Title;
import projecttwo.app.server.repository.organization.contactmanagement.TitleRepository;
import projecttwo.app.shared.organization.contactmanagement.Gender;
import projecttwo.app.server.repository.organization.contactmanagement.GenderRepository;
import projecttwo.app.shared.organization.contactmanagement.CommunicationData;
import projecttwo.app.shared.organization.contactmanagement.CommunicationType;
import projecttwo.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import projecttwo.app.shared.organization.contactmanagement.CommunicationGroup;
import projecttwo.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import projecttwo.app.shared.organization.locationmanagement.Address;
import projecttwo.app.server.repository.organization.locationmanagement.AddressRepository;
import projecttwo.app.shared.organization.locationmanagement.State;
import projecttwo.app.server.repository.organization.locationmanagement.StateRepository;
import projecttwo.app.shared.organization.locationmanagement.Country;
import projecttwo.app.server.repository.organization.locationmanagement.CountryRepository;
import projecttwo.app.shared.organization.locationmanagement.City;
import projecttwo.app.server.repository.organization.locationmanagement.CityRepository;
import projecttwo.app.shared.organization.locationmanagement.AddressType;
import projecttwo.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projecttwo.app.config.JPAConfig.class, projecttwo.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("ctZIgIz5IFtRFhJSY1e6D90ZHGxHTy0rnvwdYn2rRNpe7wMDl9");
        timezone.setTimeZoneLabel("yvc9GqCnds5nQBBIfoPDFPJI5wXW7tva10fWdMvvmOtFqoXeL2");
        timezone.setUtcdifference(3);
        timezone.setCountry("5eI6ysvF2QCmzBvwzRueaNPv8vHQ1GYPv7bQX5ivW0Y4zXQrhV");
        timezone.setCities("sAEHsZEn7KDUJXiMU5A2YQR8ba9irrvzwmS6K4kMex8YpwcwTM");
        Language language = new Language();
        language.setLanguageDescription("C9r6r2TGpa4tGPORsg4RZ1RS09bKj2qoALjAb3A7LkclqPXbnK");
        language.setLanguageIcon("B0GiEa8fcJ5o3B0Kg7Voh9wzyLM6ifvzW1gFzeB7YiLyIZeroR");
        language.setAlpha2("vF");
        language.setAlpha4("NhaJ");
        language.setLanguageType("VhXI1ASuhkJZkFVPTnYdfvpdB9MkOiOe");
        language.setAlpha3("bMk");
        language.setLanguage("AeEIQqBicGtoWzAGbJ2VIg48dYbX6KTsDEK7TcCDDkFbxi5pdT");
        language.setAlpha4parentid(6);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("3lVyk54nxlqKtssr6f4ezy1BHgaCTBhTlXOBEmnGx03M4lrTez");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("pTj1VVHtvjCbGnvaKQlCgPEMQbvUPZ9rX7v7tjDssJRUnMOKHx");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeTitle("n15iCf3xqpO9Tagd39R79K6TcgIv4ZCPfIzhiSL1awlAqhxezW");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1472019681970l));
        corecontacts.setNativeLastName("1tNK0IfJ1Ajgs8gzI0og1WfghWLPxLhDrN9wq21jW3AZBuIbL7");
        corecontacts.setEmailId("1m2J55F2XvnCG4QFSXFAvnyarhRwSdmlDeNnxM1NdY6LoCs1Ix");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("pSIdLsxX5VvNGgl0srd6xjV0FB4aJZjH97PeLJYTrt5EiBSLs8");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1472019682019l));
        corecontacts.setFirstName("DP2OuNgQPPj5geY8MeuH6CqUsloeJ3Hr0PtLMcKb8DbO4gMuw5");
        corecontacts.setMiddleName("KtXwcQqv6brvc07Iw7y3UKEp6WBAskf1PqIz8zhoZVgWCJFu3E");
        corecontacts.setAge(84);
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("z8A2gAsOl17IKJt1KaeexFTI1vfOzpUHD9pMKoCfUv1od9FIao");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("ZmgbYOrtGzlydRUUMPSH");
        corecontacts.setNativeFirstName("iOz6yDczr9flMweGr7Zy5Z5g6ngwGS9PXXy2ZVQ8MgwaIEZiYN");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("9sBGfEFiJz");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("vUDFIFDZemtQk6IJPxAWrqS9oHDGfCPJd4VtPAj9fJvjUqEhri");
        communicationtype.setCommTypeDescription("lkACPLQKKXuMIQxpBzL9Ax3O8A5cL4DxhhIpzA2dtgxJzVSvEU");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("I5mJlDdBpZNkXAiOUJj5v2DSuELAxbRW1GlMrujFb8U5EoXA8o");
        communicationgroup.setCommGroupName("OuXvciFDPCaFqWeT2sjpVbG515VjlRhrGGhGV9qssuiznVwgUF");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("GCWVjAPb6LnJ2WkTjH4z79nZVv0R4pNK1210p3SeQhyECG1x1b");
        communicationtype.setCommTypeDescription("QC0AP71yv7psocUPzZ5pkPtygrOUXM283eLTM68CkoMGhML1xw");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("xVJvJxBMML");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        state.setStateCapital("jbEQzsBqJyZnuDF6SDzuCFajiq2OKUmhUOihrEl9vn90Qqyiqs");
        state.setStateCodeChar2("AEPU29ZcQTEknHz88Hyr2YdWorNptzyF");
        state.setStateFlag("jNh6Xz7pW1sMgBgwwxXVVJVVH5ENRAnn3cPZaa4uKr7z8T1Im8");
        state.setStateDescription("j0xe5pyGDQgtQl8SeTlFlZseyEQtfICzipHP4Kf2HFegwqHyo6");
        state.setStateCapitalLatitude(10);
        Country country = new Country();
        country.setCountryCode1("UQF");
        country.setIsoNumeric(560);
        country.setCapitalLongitude(6);
        country.setCapitalLatitude(11);
        country.setCurrencyCode("JYA");
        country.setCurrencyName("OBfB0uzOdW6748PI018kz7BOZb0I3p5DY6CoUaSg1wPF171nQQ");
        country.setCapital("RaYhNtAZqdIbwZMBT6GPLXAvd9uI0bYq");
        country.setCountryFlag("UCZfQW0eGEPnKklCxwpF81Jzj9dlaeMe07td53ZUOAgjcvJxSg");
        country.setCountryCode2("rPp");
        country.setCountryName("eQ3PnyqBXgA9QjR0Qz4PWEHw4PQqjnBNjCwB9o2mYDlOnEahms");
        country.setCurrencySymbol("wcvhXCJgLCUZLnevOoFmPEFVUzNZs9dd");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapital("aEdiQ1nKUtUNk0AVYlxg2phbScAyjLaJ68KD0TcbrQMXjqKfGE");
        state.setStateCodeChar2("cyRSzH9bXFQZZBSrqTI9m8dS0s7i7tIy");
        state.setStateFlag("t4zqfnun0rWPhr19ocoFofLnbQTC92IB9KFfCPYcESdUI1cQCe");
        state.setStateDescription("rSjBlx7glahT31vnnFnl3qZNZNjaEBAG8zH7W4c2OFO6bLD1AC");
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(2);
        state.setStateName("FZpg6pRwhty2r3lNQAhEt5Dh8yHdDu5m2Xkja5awm5lENZLanE");
        state.setStateCapitalLongitude(3);
        state.setStateCodeChar3("TZwnUmnFs5nO1OZS91Puys1GLKhaoEwP");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("PXwA24cEoSSCFbdWU35TrcFr4hTJOL6J");
        city.setCityName("97DB3gHFUCBBtVtDjUq9Xiy5PQYOsLuL4kXLI3ecjm6lcjSvsF");
        city.setCityDescription("XOnC55u2gfca2HG9T4r54rX4LwmhDGKj8mu8KxBenC4CPUTQ5Z");
        city.setCityFlag("e6ku5TWZtQRO6N75WiJDpBjIEBOnUoTKomAmHZFHu2TbfGug5S");
        city.setCityLatitude(3);
        city.setCityLongitude(8);
        city.setCityCode(2);
        city.setCityCodeChar2("h6TISyhEmS2SlLt75pe9hgRvFtzxteh0");
        city.setCityName("7TUKlefRMZOJhNGJsCfA4MIH0J3VbJ2SrTxeYqYzhjgh82pySo");
        city.setCityDescription("vtOqlj7hQhWmbsMWIHRLVY9nI8kVUnKnvuJg35oCUXqZe8zQBz");
        city.setCityFlag("yOUlN9OVVUVjT7hZfIb8yximB8rxvl3PFLXGuKp6zViy9xonZz");
        city.setCityLatitude(9);
        city.setCityLongitude(9);
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("ZHtzvlXJYbH7COBySnEHAjxSvhZiaL0v6CIYuaRDFFRqWNsWEO");
        addresstype.setAddressTypeIcon("uAYqaccV1oON7qkJCijFAIpX9geZxvldJLVE5koBJy8n38KPa7");
        addresstype.setAddressType("gbLDgULBaF2jtBAvJng5iBkd6KiOqnMFNrzSNoPqxQtDJAC7yc");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("krFkdkvyh9J");
        address.setAddress1("IxvBCD88QThyhknn4g3ZnyAgpbKiLABaXyG0jMclVN6ld4mY6i");
        address.setAddress2("99SOc1TTCVIV3sYv4WZKA8lRRCzsX3gJ9J6RQYvE3wtEGizqGh");
        address.setZipcode("Z0CN5M");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setLatitude("58PXNPVB39PxqwaiIO0lgiC2nEC8fBilUnMRWKYxULPnjNHYB2");
        address.setAddress3("eqC7vTNHmXHOpyelEFkrHTUn1PzKmSdin9BFFunlJD0Bit5Tmv");
        address.setLongitude("BQRKW0NiX3fy690LnqPxgqAtBJKALRZIjD356e2Q6HoGqQSYis");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeTitle("L6VU89PKjukGMEl5DeTUtoKuYe2fiuYIgOWmuku2tHzfWr5sAy");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1472019682539l));
            corecontacts.setNativeLastName("9HbyHmVepHPzSmQ59jiHkMqErc12o6s06XVoTCqBR9AWcPDI5M");
            corecontacts.setEmailId("BL6w3IX6QDqa9BylgZ3MBZ1IUVePOnjsRpwK0zTEbyBchgnWry");
            corecontacts.setVersionId(1);
            corecontacts.setNativeMiddleName("IqnpNMeVECTvJhNfOy8kx2KtqQ1fnUVxghQ8zjj4DmPFSHz8Yc");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1472019682601l));
            corecontacts.setFirstName("lgYHc6idLHZ0NB5jdCHXqUE3GhJV8AEXm0dOz7nFGHe7apQWbW");
            corecontacts.setMiddleName("P76o3CwKCa9RhEztNwNCXuKeBJ9RdNOYhR54ju1Xzv9Io38Seh");
            corecontacts.setAge(109);
            corecontacts.setLastName("vQbBWxspqyy4x0xrqa61XtXVzh0Az4C1stTKYUTQSikjBeh8Xw");
            corecontacts.setPhoneNumber("ybVXDmmqQLqJmcjhoL9G");
            corecontacts.setNativeFirstName("KtrcKIyabdhcKmX2bTNlEMvwx1hKW8BiC61eQFSpEZoMemmxt7");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "Hoq2aEJKfMR6UDWTh2umsTMelW1i0ieiZpZmDzaPKuulqcpGnxnHnBirefMoRMTl0O14qYNZeYw9m9QOhDjVF6vVHXSDMWafNsmNb5LtWQBhwfbhEqddgD7I58C7zAgOAFBkrhOe8AVFp5mpTAzIGBmpq3dmXliV6r808QJR9EXd9K9rDf3PmY5BgDtZNBWdH23njYzofXZ0rDfMd9u5sHZnrOQ49pvy6bovKsU41tY2X831kwN79zML1joFEfLXn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "A9m0WiuPcYpDaK11FmiFHb95cpOFb0PZKYz9Qg8My3eTDckfWPv5oXWXdTWkSaOloqsIZXAwbuLv95Jx99ynSiMdxgFtWL9IHTlUq5nu2Ba5iISdzeRzIu9emPDMV9V3E5ZLXFLRJPtr8mG4NXdrbExBFMBRQX6zZBELgoi62tRtuuGSdGMLYjYp5tDDSiVdFluAmC82LaSrK6AsU77DINGKVrxdxkTiQBWMVh9coZic9GCU0jeJRd9NuK0Ddhj50"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "52RwOZJHXWLyX8NmlzZouMBGGuOFtxbSPm3Oe6yroYKJIcYQBqLyGu5YUrDDu4T2RdQ7ZCIo4WqjNNsxRDJofAPQ7cERCzeP9f0ailhQnbnUwmlKkjxnKHHGS5Om4gRUKsiIstUpfPK2wC9Lg5ScwvM6Uaz6DOAz9mjW1KJrOEBgkhv4J521jA7JVLxqxQQibiVhVM9xIP1PBcsCOr2ZcmT3lfDGo6z0xU57Ny4100mBTMxkGlADvHDJ8c5ve7Oro"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "6Ftml1361QNGBBKpPboemtqKAi8RILtUnl3e1RFQNFkv8kPvAHhnaFpm02bhPXCFn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "yjLVDRHaPNzwej5AvyE7O7oeRtxldA0qKfsTDHwUuJEE0djgW2JCDMLAlAHbM5eBengI3cXSu7gcHtTW8YQLjt7dKkAMpT3IAwAdqckURvBteE2YTx5LcGCfL5ps1dZNCsshlDR6XwxK39LxrEiBlXPu0HC2fd3BmUKMd7ZwemqBL229YGSkuJSl67QpwVnoszZVrEJ8bhN5R1F4sVbNHaWkhthdd6fsSOAzjfXVeZbe8HtyZ8Q2RBJPhO71XsAeE"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "IIm1GBUKic1IG6NqcI1y5TernQUjapQ9bpxuNOrBI6pX27aihPhSMlcdts4PgSunarpFQ86Q0Zb6thaDonjQyI7Qy9octnzP6Hb1nrnLxz8kpPFgrf0LCgfmBKnUElRXqHbG7danr24EQk3kbITgw9pmWkZEDWor1SxDQCuLkD7DcN4zEQOASqBYJ6dorlSCzZQtGWWTXSCdejSoWgSrIfyoESaPNAxkB6AphHydU6gM8tKrSroXeZwo0GOG5h8s8"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "8bDhvWxGuUdG6rE2HK1ORx6PZwkbBujpLrLpW6HAanZAI4nnquZ9G5Y839Wi7XNvEjQrQsbaPBH88nOb5VPrx8atHJvGFn4WFwcFPnhtQPVLM41tDdEATlOIqXaZNewvfzNlmVRhTuUPUtKEwsoYd7MMi06BYQnTjUH35WJ00cW8tbhiFooB4qb3M5A2sy7lviy7YlEKqWmOGNW49nJU5we4I2bui9UuY4XsIEtRHra0DCdzCZoTalFh91iFw6D0G"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 234));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "MEkAxUoatF4UIOGEC1yopDQJIqQIC1otDDzSNiJiu8tKvBgQCs7Bxe3E4EHnYMbKQBLAmD7JNSSPVUB5xSG11sHuFe49Db2o7kS78NvyIKqkKPgTBBxLG00Ivnjf1jUwzTJlCWYlF9kTHlvHgpDYwE1ylTag1AKGZymamRlJtg6OXW4TPu4CjPPcL5MWixPqEgzTSWQbPWxMxzhVWiGRRrLc7JbRe0t5MbmfNowh1mFhbL6zr0JwS0RhF8Jb3tzMJ"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "S3MRv4HYSo8xe2ZP2fHZW"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
