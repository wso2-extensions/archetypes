###Product: Template for WSO2 Identity Server User operation Event listener

STEPS:

 * Navigate to `archetypes/carbon-user-operation-eventListener` and run the following command
         mvn clean install

 * run the following command to create the User operation Event listener
 
   `` mvn archetype:generate
      -DarchetypeGroupId=org.wso2.carbon.archetype
      -DarchetypeArtifactId=user-operation-eventListner-archetype
      -DarchetypeVersion=1.0.0-SNAPSHOT
      -DgroupId=<sampleID>
      -DartifactId=<sampeArtifactId>
      -Dversion=<sampleVersion>``
       

 * Enter the authenticator name after executing steps 1 & 2. Please enter the use operation event listener prefix 
    ``eg:- listener-name-prefix : : Sample``

 * Confirm the connector name
     ``Y : : Y``
     
 * After making the changes,build your project using `mvn clean install` and 
 put the generated jar into `<is server>/repository/components/dropins`  

