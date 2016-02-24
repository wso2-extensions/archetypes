Product: Template for WSO2 IS Authenticator

STEPS:

 1. Navigate to "<IS_CONNECTORS_HOME>/org.wso2.carbon.authenticator.connector-archetype" and run the following command
         mvn clean install

 2. Navigate to "<IS_CONNECTORS_HOME>" and run the following command to create the IS authenticator
    mvn archetype:generate
        -DarchetypeGroupId=org.wso2.carbon
        -DarchetypeArtifactId=org.wso2.carbon.authenticator.connector-archetype
        -DarchetypeVersion=4.4.3
        -DgroupId=org.wso2.carbon.identity.authenticator.<connector_name>
        -DartifactId=org.wso2.carbon.identity.authenticator
        -Dversion=1.0.0
        
    Please enter the <connector_name> in lower camel case in above DgroupId command line 
    eg:- org.wso2.carbon.identity.authenticator.linkedIn

 3. Enter the authenticator name after executing steps 1 & 2. Please enter the authenticator name in upper camel case
    eg:- connector_name : : LinkedIn

 4. Confirm the connector name
     Y : : Y