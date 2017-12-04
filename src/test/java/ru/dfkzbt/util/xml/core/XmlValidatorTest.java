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

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Generic description
 *
 * @author Fedorov Konstantin <mr.fedorov.konstantin@mail.ru>
 * @version 0.1.0 [MAJOR.MINOR.PATCH]
 * Created on 16.11.2017.
 */
public class XmlValidatorTest {
    @Test
    public void validateValidXmlFile() throws Exception {
        String xmlFile = "target/test-classes/report_correct_1.xml";
        String xsdFile = "target/test-classes/xml_report_schema.xsd";

        assertTrue(XmlValidator.validateXmlFile(xmlFile, xsdFile));
    }

    @Test
    public void validateNotValidXmlFile() throws Exception {
        String xmlFile = "target/test-classes/report_not_correct_1.xml";
        String xsdFile = "target/test-classes/xml_report_schema.xsd";

        assertFalse(XmlValidator.validateXmlFile(xmlFile, xsdFile));
    }

}