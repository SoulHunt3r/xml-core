/*
 *    Copyright 2017 Konstantin Fedorov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ru.dfkzbt.util.xml.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Generic description
 *
 * @author Fedorov Konstantin <mr.fedorov.konstantin@mail.ru>
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 04.12.2017.
 */
public class XmlValidator {
    private final static Logger logger = LoggerFactory.getLogger(XmlValidator.class);

    public static boolean validateXmlFile(String xmlFilename, String xsdFilename) {
        String methodWeAreIn = new Throwable().getStackTrace()[0].getMethodName();

        // check for null and empty
        if (xmlFilename == null | xsdFilename == null) {
            logger.error("{} filenames cant be NULL. '{}', '{}'", methodWeAreIn, xmlFilename, xsdFilename);
            throw new IllegalArgumentException("filenames cant be NULL");
        }

        if (xmlFilename.isEmpty() | xsdFilename.isEmpty()) {
            logger.error("{} filenames cant be Empty. '{}', '{}'", methodWeAreIn, xmlFilename, xsdFilename);
            throw new IllegalArgumentException("filenames cant be Empty");
        }

        logger.debug("{} Validating XML file '{}' with XSD Schema '{}'", methodWeAreIn, xmlFilename, xsdFilename);

        // check files are exist
        File xmlFile = new File(xmlFilename);
        if (!xmlFile.exists()) {
            logger.error("{} file '{}' is not exist.", methodWeAreIn, xmlFilename);
            throw new IllegalArgumentException("File not exist: " + xmlFilename);
        }

        File xsdFile = new File(xsdFilename);
        if (!xsdFile.exists()) {
            logger.error("{} file '{}' is not exist.", methodWeAreIn, xsdFilename);
            throw new IllegalArgumentException("File not exist: " + xsdFilename);
        }

        // try to validate
        try {
            // get factory
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // load from XSD
            Schema schema = factory.newSchema(xsdFile);
            // create validator
            Validator validator = schema.newValidator();
            // run check - no exception mean all is ok, exception - there are errors in xml
            validator.validate(new StreamSource(xmlFile));
            //
        } catch (SAXException | IOException ex) {
            logger.error("{} Got exception while processing file: {}", methodWeAreIn, ex.toString());
            logger.trace("{} StackTrace: {}", methodWeAreIn, ex);
            return false;
        }

        logger.debug("{} Validate successful.", methodWeAreIn);
        return true;
    }
}
