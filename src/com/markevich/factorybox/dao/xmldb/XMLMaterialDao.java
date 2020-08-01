package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.Material;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionFindProductId;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionReadXMLFile;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionSaveStaff;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionWriteXMLFile;
import com.markevich.factorybox.dao.daointerface.Dao;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class XMLMaterialDao implements Dao<Material> {
    private static final List<Material> listMaterial = initXMLDb();

    public XMLMaterialDao() {

    }

    private static List<Material> initXMLDb() {
        List<Material> listMaterial = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/materialList.xml"));
            doc.getDocumentElement().normalize();

            NodeList productNodeList = doc.getElementsByTagName("material");
            for (int i = 0; i < productNodeList.getLength(); i++) {
                Node productNode = productNodeList.item(i);

                if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element productElement = (Element) productNode;
                    String id = productElement.getElementsByTagName("id").item(0).getTextContent();
                    String supplierId = productElement.getElementsByTagName("supplier-id").item(0).getTextContent();
                    String materialName = productElement.getElementsByTagName("material-name").item(0).getTextContent();
                    String price = productElement.getElementsByTagName("price").item(0).getTextContent();
                    String amount = productElement.getElementsByTagName("amount").item(0).getTextContent();
                    String unit = productElement.getElementsByTagName("unit").item(0).getTextContent();
                    String size = productElement.getElementsByTagName("size").item(0).getTextContent();
                    String pathImage = productElement.getElementsByTagName("path-image").item(0).getTextContent();

                    Material material = new Material();
                    material.setId(id);
                    material.setSupplierId(supplierId);
                    material.setMaterialName(materialName);
                    material.setPrice(price);
                    material.setAmount(amount);
                    material.setUnit(unit);
                    material.setSize(size);
                    material.setPathImage(pathImage);
                    listMaterial.add(material);
                }
            }
        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLMaterialDao.java");
        }
        return listMaterial;
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            for (Material material : listMaterial) {
                Element productList = document.createElement("material");
                list.appendChild(productList);
                Element id = document.createElement("id");
                productList.appendChild(id);
                id.appendChild(document.createTextNode(String.valueOf(material.getId())));
                Element supplierId = document.createElement("supplier-id");
                productList.appendChild(supplierId);
                supplierId.appendChild(document.createTextNode(material.getSupplierId()));
                Element materialName = document.createElement("material-name");
                productList.appendChild(materialName);
                materialName.appendChild(document.createTextNode(material.getMaterialName()));
                Element price = document.createElement("price");
                productList.appendChild(price);
                price.appendChild(document.createTextNode(material.getPrice()));
                Element amount = document.createElement("amount");
                productList.appendChild(amount);
                amount.appendChild(document.createTextNode(material.getAmount()));
                Element unit = document.createElement("unit");
                productList.appendChild(unit);
                unit.appendChild(document.createTextNode(material.getUnit()));
                Element size = document.createElement("size");
                productList.appendChild(size);
                size.appendChild(document.createTextNode(material.getMaterialName()));
                Element pathImage = document.createElement("path-image");
                productList.appendChild(pathImage);
                pathImage.appendChild(document.createTextNode(material.getPathImage()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("data/materialList.xml"));
            transformer.transform(source, file);

        } catch (Exception e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    @Override
    public List<Material> loadAll() {
        return listMaterial;
    }

    private BigInteger nextId() {
        BigInteger maxId = new BigInteger("0");
        for (Material material : listMaterial) {
            if (material.getIdBigInteger().compareTo(maxId) > 0) {
                maxId = material.getIdBigInteger();
            }
        }
        return maxId.add(new BigInteger("1"));
    }

    @Override
    public Material loadById(String id) {
        for (Material material : listMaterial) {
            if (material.getId().equals(id)) {
                return material;
            }
        }
        throw new ExceptionFindProductId(getClass().getName());
    }

    @Override
    public void save(Material material) {
        if (material.getId().equals("0")) {
            BigInteger clientId = nextId();
            material.setId(clientId.toString());
            listMaterial.add(material);
            writeXMLDB();
        } else {
            throw new ExceptionSaveStaff(getClass().getName());
        }
    }

    @Override
    public void update(Material material) {
        String id = material.getId();
        if (!(id == null)) {
            delete(id);
            listMaterial.add(material);
            writeXMLDB();
        } else {
            throw new ExceptionFindProductId(getClass().getName());
        }
    }

    @Override
    public void delete(String materialId) {
        for (Material material : listMaterial) {
            if (material.getId().equals(materialId)) {
                listMaterial.remove(material);
                writeXMLDB();
                break;
            }
        }

    }
}
