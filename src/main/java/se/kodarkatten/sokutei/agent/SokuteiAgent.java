package se.kodarkatten.sokutei.agent;

import java.lang.instrument.Instrumentation;

/**
 * Instrument all classes from relevant JEE packages:
 * EJB calls
 * JMS calls
 * JAXRS
 * JAXWS
 * JPA
 */
public class SokuteiAgent
{



    private static volatile Instrumentation globalInstrumenetation;

    public static void premain(String args, Instrumentation instrumentation)
    {
        globalInstrumenetation = instrumentation;
    }

}
