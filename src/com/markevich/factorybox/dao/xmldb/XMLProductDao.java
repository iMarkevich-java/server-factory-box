package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.Product;
import com.markevich.factorybox.dao.daointerface.Dao;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionFindProductId;
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

public class XMLProductDao implements Dao<Product> {
    private static final List<Product> listProduct = initXMLDb();

    public XMLProductDao() {

    }

    private static List<Product> initXMLDb() {
        List<Product> listProduct = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/productList.xml"));
            doc.getDocumentElement().normalize();

            NodeList productNodeList = doc.getElementsByTagName("product");
            for (int i = 0; i < productNodeList.getLength(); i++) {
                Node productNode = productNodeList.item(i);

                if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element productElement = (Element) productNode;
                    String id = productElement.getElementsByTagName("id").item(0).getTextContent();
                    String color = productElement.getElementsByTagName("color").item(0).getTextContent();
                    String texture = productElement.getElementsByTagName("texture").item(0).getTextContent();
                    String image = productElement.getElementsByTagName("image").item(0).getTextContent();
                    String material = productElement.getElementsByTagName("material").item(0).getTextContent();
                    String sizeBox = productElement.getElementsByTagName("size-box").item(0).getTextContent();
                    String orderId = productElement.getElementsByTagName("order-id").item(0).getTextContent();
                    Product product = new Product();
                    product.setColor(color);
                    product.setId(id);
                    product.setTexture(texture);
                    product.setImage(image);
                    product.setMaterial(material);
                    product.setSizeBox(sizeBox);
                    product.setOrderId(orderId);
                    listProduct.add(product);
                }
            }
        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLProductDao.java");
        }
        return listProduct;
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            for (Product product : listProduct) {
                Element productList = document.createElement("product");
                list.appendChild(productList);
                Element id = document.createElement("id");
                productList.appendChild(id);
                id.appendChild(document.createTextNode(String.valueOf(product.getId())));
                Element material = document.createElement("material");
                productList.appendChild(material);
                material.appendChild(document.createTextNode(product.getMaterial()));
                Element color = document.createElement("color");
                productList.appendChild(color);
                color.appendChild(document.createTextNode(product.getColor()));
                Element texture = document.createElement("texture");
                productList.appendChild(texture);
                texture.appendChild(document.createTextNode(product.getTexture()));
                Element image = document.createElement("image");
                productList.appendChild(image);
                image.appendChild(document.createTextNode(product.getImage()));
                Element sizeBox = document.createElement("size-box");
                productList.appendChild(sizeBox);
                sizeBox.appendChild(document.createTextNode(product.getSizeBox()));
                Element orderId = document.createElement("order-id");
                productList.appendChild(orderId);
                orderId.appendChild(document.createTextNode(product.getOrderId()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("data/productList.xml"));
            transformer.transform(source, file);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    private BigInteger nextId() {
        BigInteger maxId = new BigInteger("0");
        for (Product product : listProduct) {
            if (product.getIdBigInteger().compareTo(maxId) > 0) {
                maxId = product.getIdBigInteger();
            }
        }
        return maxId.add(new BigInteger("1"));
    }

    @Override
    public List<Product> loadAll() {
        return listProduct;
    }

    @Override
    public Product loadById(String id) {
        for (Product product : listProduct) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new ExceptionFindProductId(getClass().getName());
    }

    @Override
    public void save(Product product) {
        if (product.getId().equals("0")) {
            BigInteger clientId = nextId();
            product.setId(clientId.toString());
            listProduct.add(product);
            writeXMLDB();
        } else {
            throw new ExceptionSaveStaff(getClass().getName());
        }
    }

    @Override
    public void update(Product product) {
        String id = product.getId();
        if (!(id == null)) {
            delete(id);
            listProduct.add(product);
            writeXMLDB();
        } else {
            throw new ExceptionFindProductId(getClass().getName());
        }
    }

    @Override
    public void delete(String productId) {
        for (Product product : listProduct) {
            if (product.getId().equals(productId)) {
                listProduct.remove(product);
                writeXMLDB();
                break;
            }
        }

    }
}
