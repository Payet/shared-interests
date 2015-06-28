package com.lacaze.shared;

import com.lacaze.shared.data.Interest;

import java.util.Collection;

/**
 * Created by bertrand on 18/06/2015.
 */
public interface InterestsLister {


    Collection<Interest> listAll() ;
    void addInterest(Interest interest);
    Interest findByName(String name);
}
