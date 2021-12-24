package com.aris.xmltocsv.daos;


import com.aris.xmltocsv.entities.Bills;
import com.aris.xmltocsv.entities.Lines;
import com.aris.xmltocsv.entities.LinesBills;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoXmlToCsv {

    @Value("${erp.folder.xml.name}")
    public String xmlFolder;

    @Value("${erp.folder.csv.name}")
    public String csvFolder;

    public String findAllFilesInFolder() {
        File folder = new File(xmlFolder != null ? xmlFolder : "./xmls");
        File[] files = folder.listFiles();
        DaoXmlToCsv daoXmlToCsv = new DaoXmlToCsv();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    List<LinesBills> linesBills = daoXmlToCsv.readFromXML(file.getAbsolutePath());
                    List<Lines> lines = daoXmlToCsv.readFromXML1(file.getAbsolutePath());
                    daoXmlToCsv.writeToCsv(linesBills, file.getName().replaceAll(".xml", "-bills"));
                    daoXmlToCsv.writeToCsv1(lines, file.getName().replaceAll(".xml", "-lines"));
                }
            }
        }
        return "Success";
    }

    public List<LinesBills> readFromXML(String path) {

        try {
            File file = new File(path);
            ArrayList<LinesBills> lineslist = new ArrayList<>();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("line");


            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                Element e = (Element) node;

                LinesBills linesBills = new LinesBills();
                ArrayList<Bills> billsArrayList = new ArrayList<>();

                linesBills.setNominal(e.getElementsByTagName("nominal").item(0).getTextContent());

                int count = 1;
                while (Integer.parseInt(e.getElementsByTagName("qty").item(0).getTextContent()) >= count) {

                    NodeList noTags = e.getElementsByTagName("No" + count);
                    Element noTag = (Element) noTags.item(0);


                    Bills bills = new Bills();
                    bills.setSerialNumber(noTag.getElementsByTagName("SN").item(0).getTextContent());
                    bills.setSuitableForATM(check(Integer.parseInt(noTag.getElementsByTagName("ATM").item(0).getTextContent())));
                    bills.setFit(check(Integer.parseInt(noTag.getElementsByTagName("FIT").item(0).getTextContent())));
                    bills.setUnfit(check(Integer.parseInt(noTag.getElementsByTagName("Unfit").item(0).getTextContent())));
                    bills.setErrorCode(noTag.getElementsByTagName("ERROR").item(0).getTextContent());


                    billsArrayList.add(bills);


                    count++;
                }


                linesBills.setBills(billsArrayList);
                lineslist.add(linesBills);
            }
//            }


            return lineslist;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public List<Lines> readFromXML1(String path) {

        try {

            //creating a constructor of file class and parsing an XML file
            File file = new File(path);
            ArrayList<Lines> lineslist = new ArrayList<>();


            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("line");

            NodeList nodeList1 = doc.getElementsByTagName("SortResult");
            Node node1 = nodeList1.item(0);
            Element element = (Element) node1;

            // nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    Lines lines = new Lines();
                    lines.setUserID(element.getAttributeNode("UserID").getTextContent());
                    lines.setMachineNumber(element.getAttributeNode("machineNumber").getTextContent());
                    lines.setMachineModel(element.getAttributeNode("machine").getTextContent());
                    lines.setPackageNumber(element.getAttributeNode("packageNumber").getTextContent());
                    lines.setDepositNumber(element.getAttributeNode("depositNumber").getTextContent());
                    lines.setStartedAt(element.getAttributeNode("startDateTime").getTextContent());
                    lines.setSendedAt(element.getAttributeNode("sendDateTime").getTextContent());
                    lines.setRejectionExists(element.getAttributeNode("rejectionExists").getTextContent());
                    lines.setEndedAt(element.getAttributeNode("endDateTime").getTextContent());


                    lines.setCurrencyCode(e.getElementsByTagName("currencyCode").item(0).getTextContent());
                    lines.setNominal(Integer.parseInt(e.getElementsByTagName("nominal").item(0).getTextContent()));
                    lines.setQuantity(Integer.parseInt(e.getElementsByTagName("qty").item(0).getTextContent()));
                    lines.setAmount(Integer.parseInt(e.getElementsByTagName("nominal").item(0).getTextContent()) * Integer.parseInt(e.getElementsByTagName("qty").item(0).getTextContent()));
                    lines.setRejected(e.getElementsByTagName("rejected").item(0).getTextContent());

                    lineslist.add(lines);
                    System.out.println(lineslist.toString());
                }
            }
            return lineslist;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeToCsv(List<LinesBills> linesBills, String fileName) {
        try (FileWriter writer = new FileWriter(xmlFolder != null ? xmlFolder : "./csvs/" + fileName + ".csv")) {

            System.out.println("File Created successfully");

            StringBuilder sb = new StringBuilder();



            for (LinesBills linesBill : linesBills) {
                sb.append("Nominal");
                sb.append(',');
                sb.append("SerialNumber");
                sb.append(",");
                sb.append("SuitableForATM");
                sb.append(",");
                sb.append("Fit");
                sb.append(",");
                sb.append("Unfit");
                sb.append(",");
                sb.append("ErrorCode");
                sb.append('\n');
                for (Bills bill : linesBill.getBills()) {
                    sb.append(linesBill.getNominal());
                    sb.append(",");
                    sb.append(bill.getSerialNumber());
                    sb.append(",");
                    sb.append(bill.getSuitableForATM());
                    sb.append(",");
                    sb.append(bill.getFit());
                    sb.append(",");
                    sb.append(bill.getUnfit());
                    sb.append(",");
                    sb.append(bill.getErrorCode());
                    sb.append("\n");
                }
                sb.append("\n");
                System.out.println("CSV file is created...");
            }
            writer.write(sb.toString());
            File csvFile = new File(xmlFolder + "./csvs/" + fileName + "-bills.csv");



        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    public void writeToCsv1(List<Lines> lines, String fileName) {

        try (FileWriter writer = new FileWriter(xmlFolder != null ? xmlFolder : "./csvs/" + fileName + ".csv")) {

            System.out.println("File Created successfully");

            StringBuilder sb = new StringBuilder();

            sb.append("UserID");
            sb.append(',');
            sb.append("MachineNumber");
            sb.append(",");
            sb.append("MachineModel");
            sb.append(",");
            sb.append("DepositNumber");
            sb.append(",");
            sb.append("PackageNumber");
            sb.append(",");
            sb.append("StartedAt");
            sb.append(",");
            sb.append("EndedAt");
            sb.append(",");
            sb.append("SendedAt");
            sb.append(",");
            sb.append("RejectionExists");
            sb.append(",");
            sb.append("CurrencyCode");
            sb.append(",");
            sb.append("Nominal");
            sb.append(",");
            sb.append("Quantity");
            sb.append(",");
            sb.append("Amount()");
            sb.append(",");
            sb.append("Rejected");
            sb.append('\n');

            for (Lines line : lines) {
                sb.append(line.getUserID());
                sb.append(",");
                sb.append(line.getMachineNumber());
                sb.append(",");
                sb.append(line.getMachineModel());
                sb.append(",");
                sb.append(line.getDepositNumber() + "");
                sb.append(",");
                sb.append(line.getPackageNumber() + "");
                sb.append(",");
                sb.append(line.getStartedAt());
                sb.append(",");
                sb.append(line.getEndedAt());
                sb.append(",");
                sb.append(line.getSendedAt());
                sb.append(",");
                sb.append(line.getRejectionExists());
                sb.append(",");
                sb.append(line.getCurrencyCode());
                sb.append(",");
                sb.append((int) line.getNominal());
                sb.append(",");
                sb.append((int) line.getQuantity());
                sb.append(",");
                sb.append((int) line.getAmount());
                sb.append(",");
                sb.append(line.getRejected());
                sb.append("\n");

            }
            System.out.println("CSV file is created...");
            writer.write(sb.toString());
            File csvFile = new File(xmlFolder + "./csvs/" + fileName + "-lines.csv");


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public String check(int i) {
        switch (i){
            case 0:
                return "False";
            case 1:
                return "True";
            default:
                return "";
        }
    }

}
