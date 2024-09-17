/*
package org.example.tech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.github.underscore.U;
import com.itech.login.model.Person;

public class JsonArrayToXML {

    public static String json="{\n" +
            "    \"person\": [\n" +
            "        {\n" +
            "            \"name\": \"Akash\",\n" +
            "            \"age\": 28\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Rohit\",\n" +
            "            \"age\": 30\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Deepak\",\n" +
            "            \"age\": 32\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Soumya\",\n" +
            "            \"age\": 28\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static void main(String[] args) throws JsonProcessingException {
        String xml = U.jsonToXml(json, U.JsonToXmlMode.REMOVE_ATTRIBUTES);
        System.out.println(xml);

        //
        System.out.println("-----------------------------------");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);

        String xmlString = xmlMapper.writer().withRootName("persons").writeValueAsString(jsonNode);
        System.out.println(xmlString);

        // ----------------------------------------------//
        String strXml = "<person>\n" +
                "    <name>Poppy</name>\n" +
                "    <age>10</age>\n" +
                "</person>";
        Person person = xmlMapper.readValue(strXml, Person.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().withDefaultPrettyPrinter();
        String json = mapper.writeValueAsString(person);
        System.out.println(" <------------- XML TO JSON -------------> " + json);
    }
}
*/
