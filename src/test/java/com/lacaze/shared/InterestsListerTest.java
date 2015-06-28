package com.lacaze.shared;


import com.lacaze.shared.data.Interest;
import com.lacaze.shared.impl.InterestsListerImpl;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

/**
 * Created by bertrand on 18/06/2015.
 */
public class InterestsListerTest {

    private InterestsListerImpl interestsLister;
    protected static EntityManagerFactory emf;
    protected EntityManager em;

    @BeforeClass
    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("InterestPU");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

    @Before
    public void setUp()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        JinqJPAStreamProvider streams = new JinqJPAStreamProvider(emf);
        interestsLister = new InterestsListerImpl();
        interestsLister.setEntityManager(em);
        interestsLister.setStreams(streams);
    }

    @After
    public void rollbackTransaction() {

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    public void testListOfOneAddedInterest()
    {
        Interest frenchInterest = new Interest();
        frenchInterest.setName("French");
        interestsLister.addInterest(frenchInterest);
        Collection<Interest> interests = interestsLister.listAll();

        Assert.assertTrue(interests.contains(frenchInterest));
    }


    @Test
    public void testListOfTwoAddedInterests()
    {
        Interest frenchInterest = new Interest();
        frenchInterest.setName("French");
        interestsLister.addInterest(frenchInterest);

        Interest germanInterest = new Interest();
        germanInterest.setName("German");
        interestsLister.addInterest(germanInterest);
        Collection<Interest> interests = interestsLister.listAll();

        Assert.assertTrue(interests.contains(frenchInterest));
    }


    @Test
    public void testDuplicatedInterests()
    {
        Interest frenchInterest = new Interest();
        frenchInterest.setName("French");
        interestsLister.addInterest(frenchInterest);

        Interest duplicatedFrenchInterest = new Interest();
        duplicatedFrenchInterest.setName("French");
        interestsLister.addInterest(duplicatedFrenchInterest);

        Collection<Interest> interests = interestsLister.listAll();

        Assert.assertEquals(1, interests.size());
        Assert.assertTrue(interests.contains(frenchInterest));
    }
}
