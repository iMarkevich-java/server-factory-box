package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.Client;
import com.markevich.factorybox.dao.daointerface.Dao;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionFindClientId;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionReadXMLFile;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionSaveClient;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionWriteXMLFile;
import com.markevich.factorybox.net.serverCommandFactory.exceptions.ExceptionLoadClientById;
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

public class XMLClientDao implements Dao<Client> {

    private static final List<Client> listClient = initXMLDb();

    public XMLClientDao() {
    }

    private static List<Client> initXMLDb() {
        List<Client> listClient = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/clientList.xml"));
            doc.getDocumentElement().normalize();

            NodeList clientNodeList = doc.getElementsByTagName("client");
            for (int i = 0; i < clientNodeList.getLength(); i++) {
                Node clientNode = clientNodeList.item(i);

                if (clientNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element clientElement = (Element) clientNode;
                    String id = clientElement.getElementsByTagName("id").item(0).getTextContent();
                    String companyName = clientElement.getElementsByTagName("company-name").item(0).getTextContent();
                    String address = clientElement.getElementsByTagName("address").item(0).getTextContent();
                    String legalData = clientElement.getElementsByTagName("legal-data").item(0).getTextContent();
                    String manager = clientElement.getElementsByTagName("manager").item(0).getTextContent();
                    Client client = new Client();
                    client.setId(id);
                    client.setCompanyName(companyName);
                    client.setLegalData(legalData);
                    client.setAddress(address);
                    client.setManager(manager);
                    listClient.add(client);
                }
            }
        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLClientDao.java");
        }

        return listClient;
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            for (Client client : listClient) {
                Element clientList = document.createElement("client");
                list.appendChild(clientList);
                Element id = document.createElement("id");
                clientList.appendChild(id);
                id.appendChild(document.createTextNode(String.valueOf(client.getId())));
                Element companyName = document.createElement("company-name");
                clientList.appendChild(companyName);
                companyName.appendChild(document.createTextNode(client.getCompanyName()));
                Element address = document.createElement("address");
                clientList.appendChild(address);
                address.appendChild(document.createTextNode(client.getAddress()));
                Element legalData = document.createElement("legal-data");
                clientList.appendChild(legalData);
                legalData.appendChild(document.createTextNode(client.getLegalData()));
                Element manager = document.createElement("manager");
                clientList.appendChild(manager);
                manager.appendChild(document.createTextNode(client.getManager()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("data/clientList.xml"));
            transformer.transform(source, file);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    private BigInteger nextId() {
        BigInteger maxId = new BigInteger("0");
        for (Client client : listClient) {
            if (client.getIdBigInteger().compareTo(maxId) > 0) {
                maxId = client.getIdBigInteger();
            }
        }
        return maxId.add(new BigInteger("1"));
    }

    @Override
    public List<Client> loadAll() {
        return listClient;
    }

    @Override
    public Client loadById(String clientId) {
        for (Client client : listClient) {
            if (client.getId().equals(clientId)) {
                return client;
            }
        }
        throw new ExceptionLoadClientById(getClass().getName());
    }

    @Override
    public void save(Client client) {
        if (client.getId().equals("0")) {
            BigInteger clientId = nextId();
            client.setId(clientId.toString());
            listClient.add(client);
            writeXMLDB();
        } else {
            throw new ExceptionSaveClient(getClass().getName());
        }
    }

    @Override
    public void update(Client client) {
        String id = client.getId();
        if (!(id == null)) {
            delete(id);
            listClient.add(client);
            writeXMLDB();
        } else {
            throw new ExceptionFindClientId(getClass().getName());
        }
    }

    @Override
    public void delete(String clientId) {
        for (Client client : listClient) {
            if (client.getId().equals(clientId)) {
                listClient.remove(client);
                writeXMLDB();
                break;
            }
        }
    }
}
