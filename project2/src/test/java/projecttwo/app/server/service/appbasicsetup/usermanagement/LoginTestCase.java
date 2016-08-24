package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.Login;
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
import projecttwo.app.shared.organization.contactmanagement.CoreContacts;
import projecttwo.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setUserAccessCode(51146);
        user.setGenTempOneTimePassword(1);
        user.setSessionTimeout(1459);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472019693188l));
        user.setPasswordAlgo("xLhy4tgwhlYEhWKlmiSCn6jDCHZA1ayVU6ddrVLDHpF1g5FWhK");
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472019693189l));
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("R1XjybBl4w8ROazPuiiiwnY0Zz4kKrTXwWTAdqLMLdxWbXn5Fh");
        useraccessdomain.setDomainName("HkOkzaPalPW0dgP8YUdZKpnxhNCszoLq7Paji0QnbrAvt1RRMT");
        useraccessdomain.setDomainDescription("lmDo2BA96u7Y9kL0ykcxnd7oLwWEFKG4TIBGBNkRBWy7L1dYbn");
        useraccessdomain.setDomainHelp("BjUX5TDxklUJyjvINxO1de2CfhS5OouEyEvsnlpLCWpsBb1gc7");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("9a4uX7RZ6ftQjxlaDsfdcsuY4QuZAR59XsTEmstEzeiDKatwKT");
        useraccesslevel.setLevelHelp("ugIYLlA5dvlE3R7LnLBr4EGnLKGHaeFJmq1R5MvLX6fDVOvOoe");
        useraccesslevel.setLevelIcon("6Il3NNaYUWbsQ7eESMNaHBXv2RYP99DsBAeRN7aiMhCW9npF4s");
        useraccesslevel.setLevelDescription("WObL5xgtvBeywxbdjboqY2xRkYBF9upcBuN55HEEWtSkTc5Lpx");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setUserAccessCode(22159);
        user.setGenTempOneTimePassword(1);
        user.setSessionTimeout(1641);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472019693212l));
        user.setPasswordAlgo("27AGpDBl36FoAo91jExVFstn1LLifNgKFefhqYWAxMKuAjOdm6");
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472019693213l));
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("mNnfuwUgQgG2eEE6kpHU3wafu90s5Mw6sCu4n0PMTce5QrfP3g");
        Question question = new Question();
        question.setQuestionIcon("UrgPC2fKVRxqDFBYa7zLNr3vt1Oma99KwPAoSp4sbzHiQGu4Qa");
        question.setQuestionDetails("n1pOYrTxPM");
        question.setLevelid(10);
        question.setQuestion("XhRZIrHFzQibsbH8o7WSULQmB13cycG5eSToR4W4GGBiYVWXKZ");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("7A87B2yxpejlnoHb9eIoA5jlbqngF0UKuKRqGmusS5hzWVXEeM");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("iLEs1H6747IhAAw6xAyLfE8z9F42tBya");
        userdata.setPassword("SgqL2GCEpXoekzLSeAXHXeHtqtwDt8o2X4DKyl3qSNtC6h1OOS");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472019693456l));
        userdata.setLast5Passwords("OeyYd09t5gKHQD9cGLWUBCU61s2IXxXzsmvI99QNkABMPDGtQy");
        userdata.setOneTimePasswordExpiry(11);
        userdata.setOneTimePassword("1RfCxK5dutFBHou0gPTG8dHWqh65AOXD");
        userdata.setPassword("UdokoDI30LAQOQSzYNjZHWQykWhVPz7yreaVCTMGvob7bUPaFI");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472019693473l));
        userdata.setLast5Passwords("QLOQQ1BPCezDbh0mp5ndBSmso4uNrs6EZMTySgUmAMI58k2rQY");
        userdata.setOneTimePasswordExpiry(9);
        userdata.setUser(user);
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("7ZDEeTjlthKpvyb5RYeasmOYE0Bk2MoXjypE2r9N8978zdC8q5");
        timezone.setTimeZoneLabel("sbbVyKWCSJgBo28kK4SMGm0Zsbt33cCQ23K9HXkvw7Ajh4iRro");
        timezone.setUtcdifference(5);
        timezone.setCountry("onwx7rlPQyOR49nfB03e51zXXvv6qyQP7RHfg7VyO31A8Bvt62");
        timezone.setCities("hXF9CTPfGfJCepefcpjxe3d86TmICJOI9p6KtYl7WI1zpSbsdU");
        Language language = new Language();
        language.setLanguageDescription("jtj6xWmcPvZ7JqBNs67kdvt1YlK0b7H4p6PXzTKaAqmdVjf1dt");
        language.setLanguageIcon("OXRQrAWGLcZNJYLxSTjdtjhnwF26KYCiLp7FO5C5qiAXqNlljW");
        language.setAlpha2("Mg");
        language.setAlpha4("ABKz");
        language.setLanguageType("UdBUMaQxU1oiroO4ylnapVeMCyZXVVZn");
        language.setAlpha3("CXX");
        language.setLanguage("zAHRSTcThZ0MuOSGSwwIx93hX8LXE5GDDa5lflQPojjS0a2yRL");
        language.setAlpha4parentid(3);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("uEySaYQTSoPXKgOXeVD24RyapyncwJzLGTzunOweOYoBoKGqcm");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("BueXGSeCOIbPNO1KlfLWlO7kHosDoIhzNVoZRsBq6CuXL4Ufww");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeTitle("pna6u9TkQqrjHsejs3gEXxUK9G0g40fLLrU1BZmyAB2hrwNuJF");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1472019693608l));
        corecontacts.setNativeLastName("at5ZiRfY8jYl1YDxWGWUacoK3gCHo7ZSiOQ2RYREGkGwVaJP1Q");
        corecontacts.setEmailId("dYlwdOcMchObroEJRyN8TkpHGr28SLJlCLC8i0IpFCQFxWoruY");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("qiP2v68AlZv54EegIsb7Z7dN9XReXXTrYU0NQHEgspcMBzS6zq");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1472019693640l));
        corecontacts.setFirstName("FzxWqnd8QDIkeXgjXqGPkaIyIj8JdKvHfDoDL01pwlQxuTwtze");
        corecontacts.setMiddleName("Tb8kjLi5K7NslZ3VAZPlS7kBJxa9prNPlhUuUqn3qtvyFV2bXY");
        corecontacts.setAge(96);
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("GhFpRoVwniocuqgdT4AwbXkVjzUxpdMNX1rEqw5iYYl8x6CwqS");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("jzQV7DDN5QMWIHCeLc17");
        corecontacts.setNativeFirstName("H08rNbujCk9M1Riv2VCLVpwhw01ZCbI9yLApBtpHYqtMyeF8Wf");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("59LuUfjgeU");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("VAqjxUS7fPl5d9TyIsjxn6hRnhdBf83H860BZ6ZoM72vR4CLdL");
        communicationtype.setCommTypeDescription("99HaIDBt2cJCgl5TP3GJiJDCTZsbhJ5ozC6S4mbsAsIP8eYHXv");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("jSJlWrJdmXCjHmMvvjYP1Y5tFH3nfKJSB1M7hXEyz495c876Ae");
        communicationgroup.setCommGroupName("rfNIGUjvbXmfMCW6xdtyBzow0kvS5oSHmMkzE8O5CfI7WFy1Xp");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("noJvMiwLIqb3EOvA9hdILbGrz86F5YwWIneRLM4ZaPOEy1Pf6k");
        communicationtype.setCommTypeDescription("yonompaIBkw8vgG6FPeUJvVHpS7V2GTDEinDfeAjGIA5Sxk0BL");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("E10VePDx6y");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        State state = new State();
        state.setStateCapital("qkJ69VP9GAWSVr8nZQe8G3KGyyEe7ULiiGROqo4i2tnXZB0RIf");
        state.setStateCodeChar2("nPKqRPcLl7RITWfbFjMl2I0YNOaeiKpQ");
        state.setStateFlag("Ki4XFsbpPPSN8mkdJEtSfwUD9G39yTZH1AUmWqGQfCFWjihMIr");
        state.setStateDescription("36Xu5a2ySbgWjoBaKxyoQ2KnGtWZBNqmycrMOQ8rTH06Brf9Mg");
        state.setStateCapitalLatitude(6);
        Country country = new Country();
        country.setCountryCode1("Vj5");
        country.setIsoNumeric(67);
        country.setCapitalLongitude(4);
        country.setCapitalLatitude(2);
        country.setCurrencyCode("Sxt");
        country.setCurrencyName("vIXIe78AjQbT1cXc2dcxShV7JmvaNHJfqqo7MfdfVhdfTtYvy8");
        country.setCapital("RYPrVHm1oDdYICUYT03zA3o0XDBfmbif");
        country.setCountryFlag("NORzmBSxqKTP9yB9uhfpa4xth56aYhDYeSgnquebMMKJ9gPtFz");
        country.setCountryCode2("RWc");
        country.setCountryName("bGpkuSIBDAuaFyY5XRC0XvIqnUS0PBQAfyI7U6HSQ3AT9n05kH");
        country.setCurrencySymbol("HA38xL1KNbRaPTsCTDwcSmFtXEQlpMXP");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapital("eeAj3INo3j4JjMm4v2OmSQQQoRZDbrGwZeGzOkkOBU4kbD0kFm");
        state.setStateCodeChar2("kec3l4ek2nvNKS4q3IxmJeZcA133otLw");
        state.setStateFlag("l6LRvUrrEFWZfwdS8Po3XQ6miNGvKNSaIRSQoWRgxtEUlCCNAv");
        state.setStateDescription("iAcOlR7A768oqnLcdH2kxfq3L6kPBmgc7ioLDrTGgnChDWZQRG");
        state.setStateCapitalLatitude(11);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateName("j31BcBvRboLQcMaKQcP4RyEZD933VdVkZRUACT0ClYCvNWJn4H");
        state.setStateCapitalLongitude(4);
        state.setStateCodeChar3("NNsy3ITSANY61C8gqBPo6Fmriwtwn6z4");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("cH0rpoaFJ2tC3kiuZhg3L0S7DPcJs6no");
        city.setCityName("c4hovnNsHK6a79PJWKUFUi6bOeCQR11mqqOgfzvpsHXPWyqLB3");
        city.setCityDescription("38WIO9CpznC58ILTdEUbhDAe4ypXqKiin1tk15h4R7sDnn7GWw");
        city.setCityFlag("mJqenrIZo682rB07zTbRlIlvAX9Sw8thYT3SZqtrQxgKqXOA34");
        city.setCityLatitude(5);
        city.setCityLongitude(7);
        city.setCityCode(2);
        city.setCityCodeChar2("dRhATJkhVnyMA0X4Pl6TR5EtPxmyfjey");
        city.setCityName("T7KxYaRr02kTx23zrj9rq88EUgKs9gEuM9Gn23FmoX14YwhfoN");
        city.setCityDescription("UKgTGcqxtPKJYqeCr8XreVvkxozXLV7xfKOkHGHTmZWfzYu2mM");
        city.setCityFlag("8nm9sW2s6t1HNlWyWIuh16LLKRlT9TTIvMP82fX02HbPaP8IXH");
        city.setCityLatitude(9);
        city.setCityLongitude(10);
        city.setCityCode(1);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("YdwBRzrfGbU5ZuNTUapVlwFknfpeXGtgBYZc7Ml4Z9kM62CBxM");
        addresstype.setAddressTypeIcon("2RaSq67yunSxQiKFjLESw5QbFRHyhXqMwOlNuNYP7jRYVb7Vbq");
        addresstype.setAddressType("zAvfJF8eJHXNrWiX1CFwaU7oPLIjLm25a4aQ83JrcMAfdW64JB");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("fMVWaaPHBNK");
        address.setAddress1("uyfRZhdYxlIjGhStdyWwt1U4T8A5WZgWiIoP21dyYUd6phD2eK");
        address.setAddress2("ph8onRfdLjJPiWH05J8eulhUfvvCBoof7DWvbL7Bxe918PsmcO");
        address.setZipcode("2thBoJ");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setLatitude("kHWvnzL79hQyyCQipIaV15XPH32oSTTRYMa5eDnXbiqJ2IkTss");
        address.setAddress3("7H1tddE3xySyCmGwKtHOgqKmSdTUuejgp8kZOKmQIa2PKpTyhj");
        address.setLongitude("ydlVm7Km24jtcm1H1Kkro5Vcba68HZVOCKgcoj4MEuskMVTsvd");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(9);
        login.setServerAuthText("0zqWYmPP25Arxliq");
        login.setLoginId("i38OY94X26cLpNPOYY2emmGuupCoN59KjCV1gjQuhfv9NoQuPt");
        login.setServerAuthImage("7axyorYzX3FEFomrTclikrRgXGLlsmXB");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setIsAuthenticated(true);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
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
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setFailedLoginAttempts(1);
            login.setVersionId(1);
            login.setServerAuthText("0U2zPQB2wSXWWNGY");
            login.setLoginId("IhKxFdZu9wnaiC7IUU9nS4K72tpM5477XVLGptIaTa5Nm7BOii");
            login.setServerAuthImage("oeYE6eUC1X0Mq66EK0unnARt8Etkeb4b");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "vKpjwkVIrYTvhsAiLFY8m9330Lzjx54agiNFIJqiwBqBpti4kscs5t8n8Xmr1CY6SorzXoNtsOar5cxCEh5K4XBdmTtslooomguRQKz8bbFF6N9lHc4gsoUEC1MsJtuVSJdsoLfu1XDwDWrGloNg9E0E8nQzGEH81JsCrjZr0LxIXEPAOMxngQKA0yVXNYvllDJco3iNG"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "VfZvggTkUjHQZZ7ALbKKuEJbAUJzWXZtw"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "VZL7VRdM5lXnD0Hah"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
