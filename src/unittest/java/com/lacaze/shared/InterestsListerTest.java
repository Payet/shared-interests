package com.lacaze.shared;


import com.lacaze.shared.data.Interest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by bertrand on 18/06/2015.
 */
public class InterestsListerTest {

    private InterestsLister interestsLister;

    @Before
    public void setUp()
    {
        interestsLister = new InterestsListerImpl();
    }

    @Test
    public void testListWithFrenchInterest()
    {
        Collection<Interest> interests = interestsLister.listAll();

        Assert.assertNotNull(interests);
        Assert.assertEquals("French", interests.iterator().next().getName());
    }
}
