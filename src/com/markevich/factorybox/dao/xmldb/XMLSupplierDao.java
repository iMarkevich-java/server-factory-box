package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.Supplier;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionFindSupplierId;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionReadXMLFile;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionSaveSupplier;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionWriteXMLFile;
import com.markevich.factorybox.net.serverCommandFactory.exceptions.ExceptionFindSupplier;
import com.markevich.factorybox.dao.daointerface.Dao;
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
import java.util.ArrayList;
import java.util.List;

public class XMLSupplierDao implements Dao<Supplier> {

    private static final List<Supplier> listSupplier = initXMLDb();

    public XMLSupplierDao() {

    }

    private static List<Supplier> initXMLDb() {
        List<Supplier> listSupplier = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/supplierList.xml"));
            doc.getDocumentElement().normalize();

            NodeList supplierNodeList = doc.getElementsByTagName("supplier");
            for (int i = 0; i < supplierNodeList.getLength(); i++) {
                Node supplierNode = supplierNodeList.item(i);

                if (supplierNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element supplierElement = (Element) supplierNode;
                    String companyName = supplierElement.getElementsByTagName("company-name").item(0).getTextContent();
                    String address = supplierElement.getElementsByTagName("address").item(0).getTextContent();
                    String legalData = supplierElement.getElementsByTagName("legal-data").item(0).getTextContent();
                    String id = supplierElement.getElementsByTagName("id").item(0).getTextContent();
                    String manager = supplierElement.getElementsByTagName("manager").item(0).getTextContent();

                    Supplier supplier = new Supplier(id, companyName, address, legalData, manager);
                    listSupplier.add(supplier);
                }
            }
        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLSupplierDao.java");
        }
        return listSupplier;
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            for (Supplier supplier : listSupplier) {
                Element supplierList = document.createElement("supplier");
                list.appendChild(supplierList);
                Element id = document.createElement("id");
                supplierList.appendChild(id);
                id.appendChild(document.createTextNode(String.valueOf(supplier.getId())));
                Element companyName = document.createElement("company-name");
                supplierList.appendChild(companyName);
                companyName.appendChild(document.createTextNode(supplier.getCompanyName()));
                Element address = document.createElement("address");
                supplierList.appendChild(address);
                address.appendChild(document.createTextNode(supplier.getAddress()));
                Element legalData = document.createElement("legal-data");
                supplierList.appendChild(legalData);
                legalData.appendChild(document.createTextNode(supplier.getLegalData()));
                Element manager = document.createElement("manager");
                supplierList.appendChild(manager);
                manager.appendChild(document.createTextNode(supplier.getManager()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("data/supplierList.xml"));
            transformer.transform(source, file);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    private BigInteger nextId() {
        BigInteger maxId = new BigInteger("0");
        for (Supplier supplier : listSupplier) {
            if (supplier.getIdBigInteger().compareTo(maxId) > 0) {
                maxId = supplier.getIdBigInteger();
            }
        }
        return maxId.add(new BigInteger("1"));
    }

    @Override
    public List<Supplier> loadAll() {
        return listSupplier;
    }

    @Override
    public Supplier loadById(String supplierId) {
        for (Supplier supplier : listSupplier) {
            if (supplier.getId().equals(supplierId)) {
                return supplier;
            }
        }
        throw new ExceptionFindSupplier(getClass().getName());
    }

    @Override
    public void save(Supplier supplier) {
        if (supplier.getId().equals("0")) {
            BigInteger clientId = nextId();
            supplier.setId(clientId.toString());
            listSupplier.add(supplier);
            writeXMLDB();
        } else {
            throw new ExceptionSaveSupplier(getClass().getName());
        }
    }

    @Override
    public void update(Supplier supplier) {
        String id = supplier.getId();
        if (!(id == null)) {
            delete(id);
            listSupplier.add(supplier);
            writeXMLDB();
        } else {
            throw new ExceptionFindSupplierId(getClass().getName());
        }
    }

    @Override
    public void delete(String id) {
        for (Supplier supplierTemp : listSupplier) {
            if (supplierTemp.getId().equals(id)) {
                listSupplier.remove(supplierTemp);
                writeXMLDB();
                break;
            }
        }
    }
}
