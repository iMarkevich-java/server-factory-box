package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.Order;
import com.markevich.factorybox.dao.daointerface.Dao;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionFindOrderId;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionReadXMLFile;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionSaveOrder;
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

public class XMLOrderDao implements Dao<Order> {
    private static final List<Order> listOrder = initXMLDb();

    public XMLOrderDao() {
    }

    private static List<Order> initXMLDb() {
        List<Order> listOrder = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/orderList.xml"));
            doc.getDocumentElement().normalize();

            NodeList orderNodeList = doc.getElementsByTagName("order");
            for (int i = 0; i < orderNodeList.getLength(); i++) {
                Node orderNode = orderNodeList.item(i);

                if (orderNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element orderElement = (Element) orderNode;
                    String id = orderElement.getElementsByTagName("id").item(0).getTextContent();
                    String sizeOrder = orderElement.getElementsByTagName("size-order").item(0).getTextContent();
                    String status = orderElement.getElementsByTagName("status").item(0).getTextContent();
                    String stage = orderElement.getElementsByTagName("stage").item(0).getTextContent();
                    String clientId = orderElement.getElementsByTagName("client-id").item(0).getTextContent();
                    String orderName = orderElement.getElementsByTagName("order-name").item(0).getTextContent();
                    String startDate = orderElement.getElementsByTagName("start-date").item(0).getTextContent();
                    String orderTerm = orderElement.getElementsByTagName("order-term").item(0).getTextContent();
                    Order order = new Order();
                    order.setStage(stage);
                    order.setStatus(status);
                    order.setSizeOrder(sizeOrder);
                    order.setId(id);
                    order.setStartDate(startDate);
                    order.setOrderTerm(orderTerm);
                    order.setOrderName(orderName);
                    order.setClientId(clientId);
                    listOrder.add(order);
                }
            }
        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLMaterialDao.java");
        }
        return listOrder;
    }

    private BigInteger nextId() {
        BigInteger maxId = new BigInteger("0");
        for (Order order : listOrder) {
            if (order.getIdBigInteger().compareTo(maxId) > 0) {
                maxId = order.getIdBigInteger();
            }
        }
        return maxId.add(new BigInteger("1"));
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            for (Order order : listOrder) {
                Element listOrder = document.createElement("order");
                list.appendChild(listOrder);
                Element numberOrder = document.createElement("id");
                listOrder.appendChild(numberOrder);
                numberOrder.appendChild(document.createTextNode(String.valueOf(order.getId())));

                Element sizeOrder = document.createElement("size-order");
                listOrder.appendChild(sizeOrder);
                sizeOrder.appendChild(document.createTextNode(String.valueOf(order.getSizeOrder())));

                Element status = document.createElement("status");
                listOrder.appendChild(status);
                status.appendChild(document.createTextNode(order.getStatus()));

                Element stage = document.createElement("stage");
                listOrder.appendChild(stage);
                stage.appendChild(document.createTextNode(order.getStage()));

                Element clientId = document.createElement("client-id");
                listOrder.appendChild(clientId);
                clientId.appendChild(document.createTextNode(order.getClientId()));

                Element orderName = document.createElement("order-name");
                listOrder.appendChild(orderName);
                orderName.appendChild(document.createTextNode(order.getOrderName()));

                Element startDate = document.createElement("start-date");
                listOrder.appendChild(startDate);
                startDate.appendChild(document.createTextNode(order.getStartDate()));

                Element orderTerm = document.createElement("order-term");
                listOrder.appendChild(orderTerm);
                orderTerm.appendChild(document.createTextNode(order.getOrderTerm()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("data/orderList.xml"));
            transformer.transform(source, file);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    @Override
    public List<Order> loadAll() {
        return listOrder;
    }

    @Override
    public Order loadById(String id) {
        for (Order order : listOrder) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        throw new ExceptionFindOrderId(getClass().getName());
    }

    @Override
    public void save(Order order) {
        if (order.getId().equals("0")) {
            BigInteger id = nextId();
            order.setId(id.toString());
            order.setOrderName(order.getOrderName() + " (" + order.getId() + ").");
            listOrder.add(order);
            writeXMLDB();
        } else {
            throw new ExceptionSaveOrder(getClass().getName());
        }
    }

    @Override
    public void update(Order order) {
        String id = order.getId();
        if (!(id == null)) {
            delete(id);
            listOrder.add(order);
            writeXMLDB();
        } else {
            throw new ExceptionFindOrderId(getClass().getName());
        }
    }

    @Override
    public void delete(String orderId) {
        for (Order order : listOrder) {
            if (order.getId().equals(orderId)) {
                listOrder.remove(order);
                writeXMLDB();
                break;
            }
        }
    }
}
