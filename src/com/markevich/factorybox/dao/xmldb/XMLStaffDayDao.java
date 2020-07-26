package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.Day;
import businessObjectFactoryBox.StaffDays;
import com.markevich.factorybox.dao.daointerface.Dao;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionReadXMLFile;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionWriteXMLFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class XMLStaffDayDao implements Dao<StaffDays> {

    private static final List<StaffDays> staffDayList = initXMLDb();

    public XMLStaffDayDao() {
    }

    private static List<StaffDays> initXMLDb() {
        List<StaffDays> staffDayList = new LinkedList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/dayList.xml"));
            doc.getDocumentElement().normalize();
            NodeList staffNodeList = doc.getElementsByTagName("staff-day");
            for (int k = 0; k < staffNodeList.getLength(); k++) {
                List<Day> listDay = new LinkedList<>();
                StaffDays staffDay = new StaffDays();
                Node staffNode = staffNodeList.item(k);
                if (staffNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element staffElement = (Element) staffNode;
                    BigInteger staffId = new BigInteger(staffElement.getElementsByTagName("staff-id").item(0).getTextContent());
                    staffDay.setStaffId(staffId);
                    NodeList daysNodeList = staffElement.getElementsByTagName("list-day");

                    for (int j = 0; j < daysNodeList.getLength(); j++) {
                        Day day = new Day();
                        Node dayNode = daysNodeList.item(j);
                        if (dayNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element dayElement = (Element) dayNode;
                            LocalDate date = LocalDate.parse(dayElement.getElementsByTagName("day").item(0).getTextContent());
                            String orderName = dayElement.getElementsByTagName("order-name").item(0).getTextContent();
                            BigInteger productivity = new BigInteger(dayElement.getElementsByTagName("productivity").item(0).getTextContent());
                            BigInteger staffIdDay = new BigInteger(dayElement.getElementsByTagName("staff-id-day").item(0).getTextContent());
                            day.setDay(date);
                            day.setOrderName(orderName);
                            day.setProductivity(productivity);
                            day.setStaffId(staffIdDay);
                            listDay.add(day);
                        }
                    }
                }
                staffDay.setListDay(listDay);
                staffDayList.add(staffDay);
            }

        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLUserDao.java");
        }
        return staffDayList;
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            for (StaffDays staffDay : staffDayList) {
                Element staffDayList = document.createElement("staff-day");
                list.appendChild(staffDayList);
                Element staffId = document.createElement("staff-id");
                staffDayList.appendChild(staffId);
                staffId.appendChild(document.createTextNode(staffDay.getStaffId().toString()));
                for (int i = 0; i < staffDay.getListDay().size(); i++) {
                    Element listDay = document.createElement("list-day");
                    staffDayList.appendChild(listDay);
                    Element day = document.createElement("day");
                    listDay.appendChild(day);
                    day.appendChild(document.createTextNode(staffDay.getListDay().get(i).getDay().toString()));
                    Element orderName = document.createElement("order-name");
                    listDay.appendChild(orderName);
                    orderName.appendChild(document.createTextNode(staffDay.getListDay().get(i).getOrderName()));
                    Element productivity = document.createElement("productivity");
                    listDay.appendChild(productivity);
                    productivity.appendChild(document.createTextNode(staffDay.getListDay().get(i).getProductivity().toString()));
                    Element staffIdDay = document.createElement("staff-id-day");
                    listDay.appendChild(staffIdDay);
                    staffIdDay.appendChild(document.createTextNode(staffDay.getStaffId().toString()));
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("data/dayList.xml"));
            transformer.transform(source, file);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    @Override
    public List<StaffDays> loadAll() {
        return staffDayList;
    }

    @Override
    public StaffDays loadById(String staffId) {
        StaffDays staffDay = null;
        for (StaffDays staffDayTemp : staffDayList) {
            if (staffDayTemp.getStaffId().toString().equals(staffId)) {
                staffDay = staffDayTemp;
                break;
            }
        }
        return staffDay;
    }

    @Override
    public void save(StaffDays staffDay) {
        staffDayList.add(staffDay);
        writeXMLDB();
    }

    @Override
    public void update(StaffDays staffDay) {
        StaffDays day = new StaffDays();
        for (StaffDays staffDayTemp : staffDayList) {
            if (staffDayTemp.getStaffId().toString().equals(staffDay.getStaffId().toString())) {
                day = staffDayTemp;
                break;
            }
        }
        staffDayList.remove(day);
        staffDayList.add(staffDay);
        writeXMLDB();
    }

    @Override
    public void delete(String staffId) {
        StaffDays staffDay = null;
        for (StaffDays staffDayTemp : staffDayList) {
            if (staffDayTemp.getStaffId().toString().equals(staffId)) {
                staffDay = staffDayTemp;
                break;
            }
        }
        staffDayList.remove(staffDay);
        writeXMLDB();
    }

    public void deleteDay(Day day) {
        StaffDays staffDays = loadById(day.getStaffId().toString());
        staffDays.removeDate(day);
        update(staffDays);
    }
}
