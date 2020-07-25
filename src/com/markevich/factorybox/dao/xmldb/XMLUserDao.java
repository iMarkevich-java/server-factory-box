package com.markevich.factorybox.dao.xmldb;

import businessObjectFactoryBox.User;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionReadXMLFile;
import com.markevich.factorybox.dao.xmldb.exception.ExceptionWriteXMLFile;
import com.markevich.factorybox.service.exceptions.ExceptionGeneratePassword;
import com.markevich.factorybox.service.exceptions.ExceptionSecretKeyGetInstance;
import com.markevich.factorybox.service.user.UserDb;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class XMLUserDao {

    private static final List<UserDb> listUser = initXMLDb();

    public XMLUserDao() {
    }

    private static List<UserDb> initXMLDb() {
        List<UserDb> listUser = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("data/userList.xml"));
            doc.getDocumentElement().normalize();

            NodeList userNodeList = doc.getElementsByTagName("user");
            for (int i = 0; i < userNodeList.getLength(); i++) {
                Node userNode = userNodeList.item(i);

                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;
                    String id = userElement.getElementsByTagName("user-id").item(0).getTextContent();
                    String name = userElement.getElementsByTagName("user-name").item(0).getTextContent();
                    String password = userElement.getElementsByTagName("user-password").item(0).getTextContent();
                    String salt = userElement.getElementsByTagName("salt").item(0).getTextContent();
                    UserDb userDb = new UserDb();
                    userDb.setId(new BigInteger(id));
                    userDb.setName(name);
                    userDb.setPasswordDB(password);
                    userDb.setSaltUser(salt);

                    listUser.add(userDb);
                }
            }
        } catch (Exception e) {
            throw new ExceptionReadXMLFile("src/com/markevich/factorybox/dao/xmldb/XMLUserDao.java");
        }

        return listUser;
    }

    private void writeXMLDB() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element list = document.createElement("list");
            document.appendChild(list);
            for (UserDb userDb : listUser) {
                Element userList = document.createElement("user");
                list.appendChild(userList);
                Element id = document.createElement("user-id");
                userList.appendChild(id);
                id.appendChild(document.createTextNode(userDb.getId().toString()));
                Element name = document.createElement("user-name");
                userList.appendChild(name);
                name.appendChild(document.createTextNode(userDb.getName()));
                Element password = document.createElement("user-password");
                userList.appendChild(password);
                password.appendChild(document.createTextNode(userDb.getPasswordDB()));
                Element salt = document.createElement("salt");
                userList.appendChild(salt);
                salt.appendChild(document.createTextNode(String.valueOf(userDb.getSaltUser())));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("data/userList.xml"));
            transformer.transform(source, file);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new ExceptionWriteXMLFile(getClass().getName());
        }
    }

    private BigInteger nextId() {
        BigInteger maxId = new BigInteger("0");
        for (UserDb userDb : listUser) {
            if (userDb.getId().compareTo(maxId) > 0) {
                maxId = userDb.getId();
            }
        }
        return maxId.add(new BigInteger("1"));
    }

    public List<UserDb> loadAll() {
        return listUser;
    }

    public void save(User user) {
        for (UserDb userTemp : listUser) {
            if (userTemp.getName().equals(user.getName())) {
                return;
            }
        }
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        PBEKeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
        byte[] hashPassword;
        hashPassword = genHashPassword(spec);
        String saltStr = Base64.getEncoder().encodeToString(salt);
        String passwordStr = Base64.getEncoder().encodeToString(hashPassword);
        UserDb userDb = new UserDb();
        userDb.setId(nextId());
        userDb.setName(user.getName());
        userDb.setSaltUser(saltStr);
        userDb.setPasswordDB(passwordStr);
        listUser.add(userDb);
        writeXMLDB();
    }

    public void delete(String userName) {
        for (UserDb userDb : listUser) {
            if (userDb.getName().equals(userName)) {
                listUser.remove(userDb);
                writeXMLDB();
                break;
            }
        }
    }

    public Boolean verification(User user) {
        UserDb userDb;
        for (UserDb userTemp : listUser) {
            if (userTemp.getName().equals(user.getName())) {
                userDb = userTemp;
                byte[] salt = Base64.getDecoder().decode(userDb.getSaltUser());
                PBEKeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
                byte[] hashPassword;
                hashPassword = genHashPassword(spec);
                String passwordStr = Base64.getEncoder().encodeToString(hashPassword);
                return passwordStr.equals(userDb.getPasswordDB());
            }
        }
        return false;
    }

    private byte[] genHashPassword(PBEKeySpec spec) {
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new ExceptionSecretKeyGetInstance(getClass().getName());
        }
        byte[] hashPassword;
        try {
            hashPassword = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new ExceptionGeneratePassword(getClass().getName());
        }
        return hashPassword;
    }
}
