package projecttwo.app.server.service.appbasicsetup.usermanagement;
import projecttwo.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projecttwo.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import projecttwo.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("gYafts65S7zVylTpEfXPKSlBwnlyYUxC2wcdgflpm0M1ATUoNq");
        useraccessdomain.setDomainName("yHe8BMWQEqROyNQCZQImwl7tl4hBgag6BGmeGK7KGgCsZZ2FPP");
        useraccessdomain.setDomainDescription("EMDkfdLnWTdXI7LFnaXnLFmLuZMNmB4NtRUzgI3bxj4lwBjIri");
        useraccessdomain.setDomainHelp("9kc0Q1cQ43MnhMAaXEpHvbzOYseibFnrdL9rvfF0vLhF0SE1oT");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(92891);
            useraccessdomain.setDomainIcon("i0tTrK71uvGxk4hofCq5t4RY9uVWvpqLLmKzJmu8uGQemNS4Ou");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("mY8vQOoCw95Wju9zAP0LBNOgwdogKHW3sXkBErtpcSXSJIeE0B");
            useraccessdomain.setDomainDescription("peZnHpo7FcZCnV04YHE50vv1QuSiePLh1j2N7HUiFBHa3O5XQP");
            useraccessdomain.setDomainHelp("bGdMqqXogLOy7SeaLtIIXjLLBvTf4dU45INESQLKUNwhPsM6nZ");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 196522));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "vrL8yt5hjpA3b9RhURkcEfa8ec92v8sPcCXaHnZwPIcLEidDGUvqpQqWnvBb4Dk44cPtkiltaErGq2giQBa49bS6Lia1bGsz9hhaFTRaEr5WbfUiej31eCMnuJliKpqS5zk2cTqzXLJ40fy78qZKluNDkOCd6OYDEqsTCs1G67iZ0TTquwpiprLYC39jcU6xcz6oR9sj3ecN7eQXTYD9Ijc1fIKnTM8YK487Rsumy2cyol8sQiULDxS3QYLLivcob"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "qh0tECYycWtI1rSoj4nBFvoFpUjrt48loEwooaL7ODh4jK6tqrMK0xkyaYOTRJrmqXoVwrLXvXsGg7SkBeNDOJ9fZ99z4tKkgNxbKyTfpwfUpUMkc0KJ21wf1cwBhaaDQdR8FjptNDhh5bWL9h5UYDYh4uL3GtvseMUfB6Ln56cRjabbwscLZNhKYke2ZX5TehSck9lUTii6YIu0tUGDifZUlplqHkCLSZfSLSHif5CYOpbA2WQEuCp7qUSlX1Wai"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "cLdG1J4VPZwW6tFfbzYrf82oxH5n16fogzH9yUe3Hfs5gI0gmBzITgCn9zfodGnrvmCDAPMtR8xTVISWnSA48kDA9CqZlRJQP91QVEypya9hbxQZ4n3zJCNYZbHiMkgruJGWIL80spPZA3kEwFKDUPbtQaVWMEyna2sGBCA3Fts7i0Vao9Wrt7cUpwWrwGGmPxwi2yFOKIhFZxxlA8DXfC2hB6pRRLsQvp7gTuxHQfShJ2HXasjRqqyEMiOfCkjh3"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
