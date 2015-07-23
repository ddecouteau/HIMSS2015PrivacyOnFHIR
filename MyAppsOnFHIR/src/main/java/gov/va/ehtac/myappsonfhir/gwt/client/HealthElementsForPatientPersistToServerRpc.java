package gov.va.ehtac.myappsonfhir.gwt.client;

import com.vaadin.shared.communication.ServerRpc;

public interface HealthElementsForPatientPersistToServerRpc extends ServerRpc {
    void persistToServer();
}
