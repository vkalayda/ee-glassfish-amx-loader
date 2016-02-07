##A EJB AMX Loader For GlassFish and Payara Application Servers

Tested with Payara and GlassFish,

###Installation:

Deploy the EJB JAR https://github.com/vkalayda/ee-glassfish-amx-loader/releases/download/1.0/ee-glassfish-amx-loader.jar into your application server with low Deployment Order.

By default amx-loader is enabled. You can disable loader with JNDI - resources/amx/enabled set to false.