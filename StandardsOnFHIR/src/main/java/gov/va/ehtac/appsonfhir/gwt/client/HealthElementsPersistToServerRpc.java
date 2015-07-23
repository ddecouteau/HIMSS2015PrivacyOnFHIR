package gov.va.ehtac.appsonfhir.gwt.client;

import com.vaadin.shared.communication.ServerRpc;

public interface HealthElementsPersistToServerRpc extends ServerRpc {
    void persistToServer();
}
