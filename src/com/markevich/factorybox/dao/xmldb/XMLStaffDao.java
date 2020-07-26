package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.Position;
import businessObjectFactoryBox.Staff;
import com.markevich.factorybox.dao.daointerface.Dao;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionFindStaffId;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionReadXMLFile;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionSaveStaff;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionWriteXMLFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class XMLStaffDao implements Dao<Staff> {
    private static final List<Staff> listStaff = initXMLDb();

    public XMLStaffDao() {
    }


    private static List<Staff> initXMLDb() {
        ArrayList<Staff> list = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/staffList.xml"));
            doc.getDocumentElement().normalize();

            NodeList staffList = doc.getElementsByTagName("staff");
            for (int i = 0; i < staffList.getLength(); i++) {
                Node staffNode = staffList.item(i);

                if (staffNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element staffElement = (Element) staffNode;
                    String id = staffElement.getElementsByTagName("id").item(0).getTextContent();
                    String firstName = staffElement.getElementsByTagName("first-name").item(0).getTextContent();
                    String lastName = staffElement.getElementsByTagName("last-name").item(0).getTextContent();
                    String dateOfBirth = staffElement.getElementsByTagName("date-of-birth").item(0).getTextContent();
                    String salary = staffElement.getElementsByTagName("salary").item(0).getTextContent();
                    String position = staffElement.getElementsByTagName("position").item(0).getTextContent();
                    String address = staffElement.getElementsByTagName("address").item(0).getTextContent();
                    String department = staffElement.getElementsByTagName("department").item(0).getTextContent();
                    String pathPhoto = staffElement.getElementsByTagName("path-photo").item(0).getTextContent();

                    Staff staff = new Staff();
                    staff.setFirstName(firstName);
                    staff.setLastName(lastName);
                    staff.setDateOfBirth(dateOfBirth);
                    staff.setId(id);
                    staff.setSalary(salary);
                    staff.setAddress(address);
                    staff.setDepartment(department);
                    staff.setPathPhoto(pathPhoto);
                    staff.setPosition(position);

                    list.add(staff);
                }
            }
        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLStaffDao.java");
        }
        return list;
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            Element listPersonal = document.createElement("staff-list");
            list.appendChild(listPersonal);
            for (Staff staff : listStaff) {
                Element personal = document.createElement("staff");
                listPersonal.appendChild(personal);
                Element idPersonal = document.createElement("id");
                personal.appendChild(idPersonal);
                idPersonal.appendChild(document.createTextNode(String.valueOf(staff.getId())));
                Element firstNamePersonal = document.createElement("first-name");
                personal.appendChild(firstNamePersonal);
                firstNamePersonal.appendChild(document.createTextNode(staff.getFirstName()));
                Element lastNamePersonal = document.createElement("last-name");
                personal.appendChild(lastNamePersonal);
                lastNamePersonal.appendChild(document.createTextNode(staff.getLastName()));
                Element dateOfBirthPersonal = document.createElement("date-of-birth");
                personal.appendChild(dateOfBirthPersonal);
                dateOfBirthPersonal.appendChild(document.createTextNode(staff.getDateOfBirth()));
                Element salaryPersonal = document.createElement("salary");
                personal.appendChild(salaryPersonal);
                salaryPersonal.appendChild(document.createTextNode(String.valueOf(staff.getSalary())));
                Element positionPersonal = document.createElement("position");
                personal.appendChild(positionPersonal);
                positionPersonal.appendChild(document.createTextNode(staff.getPosition()));
                Element addressPersonal = document.createElement("address");
                personal.appendChild(addressPersonal);
                addressPersonal.appendChild(document.createTextNode(staff.getAddress()));
                Element department = document.createElement("department");
                personal.appendChild(department);
                department.appendChild(document.createTextNode(staff.getDepartment()));
                Element pathPhoto = document.createElement("path-photo");
                personal.appendChild(pathPhoto);
                pathPhoto.appendChild(document.createTextNode(staff.getPathPhoto()));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult file = new StreamResult(new File("data/staffList.xml"));
            transformer.transform(source, file);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    private BigInteger nextId() {
        BigInteger maxId = new BigInteger("0");
        for (Staff staff : listStaff) {
            if (staff.getIdBigInteger().compareTo(maxId) > 0) {
                maxId = staff.getIdBigInteger();
            }
        }
        return maxId.add(new BigInteger("1"));
    }

    @Override
    public List<Staff> loadAll() {
        return listStaff;
    }

    @Override
    public Staff loadById(String staffId) {
        Staff staff = new Staff();
        for (Staff temp : listStaff) {
            if (temp.getId().equals(staffId)) {
                staff = temp;
                break;
            }
        }
        return staff;
    }

    @Override
    public void save(Staff staff) {
        if (staff.getId().equals("0")) {
            BigInteger clientId = nextId();
            staff.setId(clientId.toString());
            listStaff.add(staff);
            writeXMLDB();
        } else {
            throw new ExceptionSaveStaff(getClass().getName());
        }
    }

    @Override
    public void update(Staff staff) {
        String id = staff.getId();
        if (!(id == null)) {
            delete(id);
            listStaff.add(staff);
            writeXMLDB();
        } else {
            throw new ExceptionFindStaffId(getClass().getName());
        }
    }

    @Override
    public void delete(String staffId) {
        for (Staff staff : listStaff) {
            if (staff.getId().equals(staffId)) {
                listStaff.remove(staff);
                break;
            }
        }
        writeXMLDB();
    }
}
