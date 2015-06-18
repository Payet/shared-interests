package com.lacaze.shared;

import com.lacaze.shared.data.Interest;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by bertrand on 18/06/2015.
 */
public class InterestsListerImpl implements InterestsLister {

    @Override
    public Collection<Interest> listAll() {

        Collection<Interest> interests = new ArrayList<Interest>();
        Interest interest = new Interest();
        interest.setName("French");
        interests.add(interest);
        return interests;
    }

}
