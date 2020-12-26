package org.datanucleus.test;

import java.time.LocalTime;
import java.util.*;
import org.junit.*;
import javax.jdo.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

public class SimpleTest
{
    @Test
    public void testSimple()
    {
        NucleusLogger.GENERAL.info(">> test START");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("MyTest");

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();

            // [INSERT code here to persist object required for testing]
            Schedule schedule = new Schedule();
            schedule.setDay("monday");
            schedule.setOpens(LocalTime.parse("07:00").toString());
            schedule.setCloses(LocalTime.parse("11:00").toString());

            Hub hub = new Hub();
            hub.setName("YogaClub");
            hub.setSchedules(Arrays.asList(schedule));

            // NOTE: This test is meant to test the converter
            // so, the database "row" must be checked for actual stored value,
            // in thi case we used MongoDB and the resulting document stored is this:
            // {"_id":{"$oid":"5fe6bb84e06a029ddcc56c15"},"name":"YogaClub","schedules":[{"opens":"07:00","day":"monday","closes":"11:00"}]}
            // which is whe "opens" and "closes" field should have been a Long value
            pm.makePersistent(hub);

            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        pmf.close();
        NucleusLogger.GENERAL.info(">> test END");
    }
}
