package org.wso2.carbon.extension.esb.connector.automation.wsdl;
/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import org.exolab.castor.xml.schema.Schema;
import org.exolab.castor.xml.schema.reader.SchemaReader;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


/**
 * XML support and utilities
 */

public class XMLSupport {

    /**
     * Returns a string representation of the given jdom element.
     *
     * @param element The jdom element to be converted into a string.
     * @return The string representation of the given jdom element.
     */
    public static String outputString(Element element) {
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        return xmlWriter.outputString(element);
    }

    /**
     * It reads the given reader and returns the castor schema.
     *
     * @param reader The reader to read.
     * @return The castor schema created from the reader.
     * @throws IOException If the schema could not be read from the reader.
     */
    public static Schema readSchema(Reader reader) throws IOException {
        // create the sax input source
        InputSource inputSource = new InputSource(reader);
        // create the schema reader
        SchemaReader schemaReader = new SchemaReader(inputSource);
        schemaReader.setValidation(false);
        // read the schema from the source
        Schema schema = schemaReader.read();
        return schema;
    }

    /**
     * Converts the jdom element into a castor schema.
     *
     * @param element The jdom element to be converted into a castor schema.
     * @return The castor schema corresponding to the element.
     * @throws IOException If the jdom element could not be written out.
     */
    public static Schema convertElementToSchema(Element element) throws IOException {
        // get the string content of the element
        String content = outputString(element);
        // check for null value
        if (content != null) {
            // create a schema from the string content
            return readSchema(new StringReader(content));
        }
        // otherwise return null
        return null;
    }
}