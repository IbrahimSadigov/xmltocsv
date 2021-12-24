package com.aris.xmltocsv;

import com.aris.xmltocsv.daos.DaoXmlToCsv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmltocsvApplication {

    public static void main(String[] args) {
        DaoXmlToCsv daoXmlToCsv = new DaoXmlToCsv();
        daoXmlToCsv.findAllFilesInFolder();
        SpringApplication.run(XmltocsvApplication.class, args);
    }

}
