package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.ProductList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductMgmtApp {
    public static void main(String[] args) throws JsonProcessingException {
        List<Product> products = Arrays.asList(
                new Product("3128874119", "Banana", LocalDate.of(2023,1, 24), 124, BigDecimal.valueOf(0.55)),
                new Product("2927458265", "Apple", LocalDate.of(2022, 12, 9), 18, BigDecimal.valueOf(1.09)),
                new Product("9189927460", "Carrot", LocalDate.of(2023, 3, 31), 89, BigDecimal.valueOf(2.99))
                );
        Collections.sort(products, Comparator.comparing(Product::getName));
        printProducts(products);
    }

    static void printProducts(List<Product> products) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        System.out.println("-".repeat(30));

        String jsonOutput = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
        System.out.println("Printed in JSON Format:");
        System.out.println(jsonOutput);
        System.out.println("-".repeat(30));

        // Convert and print in XML format
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

        ProductList productList = new ProductList(products);
        String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productList);
        System.out.println("Printed in XML Format:");
        System.out.println(xmlOutput);
        System.out.println("-".repeat(30));

        // Convert and print in CSV format
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JavaTimeModule());
        csvMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        CsvSchema csvSchema = csvMapper.schemaFor(Product.class);
        String csvOutput = csvMapper.writer(csvSchema).writeValueAsString(products);
        System.out.println("Printed in Comma-Seperated Value (CSV) Format:");
        System.out.println(csvOutput);
        System.out.println("-".repeat(30));
    }
}