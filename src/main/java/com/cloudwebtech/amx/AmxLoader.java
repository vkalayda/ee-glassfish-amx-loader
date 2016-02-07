package com.cloudwebtech.amx;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Startup
@Singleton
public class AmxLoader {

    private static final Logger LOGGER = Logger.getLogger(AmxLoader.class.getName());

    private static final String JNDI_AMX_ENABLED = "resources/amx/enabled";

    private boolean enabled = true;

    private void initParameters() {
        try {
            enabled = InitialContext.doLookup(JNDI_AMX_ENABLED);
        } catch (NamingException ex) {
        }
    }

    @PostConstruct
    public void startUp() {
        initParameters();

        LOGGER.log(Level.INFO, "amx-loader enabled=''{0}''", enabled);

        if (!enabled) {
            return;
        }

        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName amxObjectName = new ObjectName("amx-support:type=boot-amx");
            Object result = mBeanServer.invoke(amxObjectName, "bootAMX", null, null);

            LOGGER.log(Level.INFO, "amx-loader ''{0}''", result);
        } catch (Exception ex) {
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

}
