package com.lacaze.shared.impl;

import com.lacaze.shared.InterestsLister;
import com.lacaze.shared.data.Interest;
import org.apache.log4j.Logger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;


/**
 * Created by bertrand on 18/06/2015.
 */
public class InterestsListerImpl implements InterestsLister {

    private static final Logger LOG = Logger.getLogger(InterestsListerImpl.class);
    private EntityManager em;
    private JinqJPAStreamProvider streams;

    @Override
    public Collection<Interest> listAll() {
        Query query = em.createQuery("SELECT interest FROM Interest interest");

        return query.getResultList();
    }

    @Override
    public void addInterest(Interest interest) {
        Interest foundInterest = findByName(interest.getName());
        if (foundInterest == null)
        {
            em.persist(interest);
        }

    }

    @Override
    public Interest findByName(String name) {

        JinqStream<Interest> stream = streams.streamAll(em, Interest.class);

        List<Interest> results = streams.streamAll(em, Interest.class)
               .where( i -> i.getName().equals(name) )
               .toList();

        if (results.size() == 0){
            return null;
        }
        if (results.size() > 1){
            LOG.warn("Multiple results found for Interest with name: " + name);
        }
        return results.get(0);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public JinqJPAStreamProvider getStreams() {
        return streams;
    }

    public void setStreams(JinqJPAStreamProvider streams) {
        this.streams = streams;
    }
}
