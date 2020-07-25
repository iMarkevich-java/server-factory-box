package com.markevich.factorybox.service.client;

import businessObjectFactoryBox.Client;
import com.markevich.factorybox.service.interfaces.Service;

import java.util.List;

public class ClientService implements Service<Client> {

    @Override
    public List<Client> loadAll() {
        LoadAllClient loadAllClient = new LoadAllClient();
        return loadAllClient.loadAllClient();
    }

    @Override
    public Client loadById(String id) {
        LoadClientByID loadClientByID = new LoadClientByID();
        return loadClientByID.loadClientById(id);
    }

    @Override
    public void save(Client client) {
        SaveClient saveClient = new SaveClient();
        saveClient.saveClient(client);
    }

    @Override
    public void update(Client client) {
        UpdateClient updateClient = new UpdateClient();
        updateClient.updateClient(client);
    }

    @Override
    public void delete(String id) {
        DeleteClient deleteClient = new DeleteClient();
        deleteClient.deleteClient(id);
    }
}
