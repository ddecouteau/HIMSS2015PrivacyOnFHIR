package ca.uhn.fhir.api;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;

import java.util.List;

/**
 * Created by mochaholic on 22/01/2015.
 */
public abstract class ExtendedResourceMetadataKeyEnum extends ResourceMetadataKeyEnum {

    public static final ResourceMetadataKeyEnum<List<CodingDt>> SECURITY_LABELS = new ResourceMetadataKeyEnum<List<CodingDt>>("SECURITY_LABELS") {
        @Override
        public List<CodingDt> get(IResource resource) {
            Object obj = resource.getResourceMetadata().get(SECURITY_LABELS);
            if (obj == null) {
                return null;
            }
            else {
                try {
                    List<CodingDt> securityLabels = (List<CodingDt>) obj;
                    if (securityLabels.isEmpty())
                        return null;
                    else
                        return securityLabels;
                }
                catch (ClassCastException e)
                {
                    throw new InternalErrorException("Found an object of type '" + obj.getClass().getCanonicalName() + "' in resource metadata for key SECURITY_LABELS - Expected "
                            + CodingDt.class.getCanonicalName());

                }

            }
        }

        @Override
        public void put(IResource iResource, List<CodingDt> labels) {
            iResource.getResourceMetadata().put(SECURITY_LABELS, labels);
        }

    };

    public ExtendedResourceMetadataKeyEnum(String theValue) {
        super(theValue);
    }
}
