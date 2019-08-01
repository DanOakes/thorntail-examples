package service.manager;

import service.api.CataloguesResponse;

import java.util.List;

/**
 * Created by dan on 02/08/19.
 */
public interface CatalogueManager {

    List<CataloguesResponse> getAllCatalogues();
}
