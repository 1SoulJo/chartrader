package provider.dao;

/**
 * Factory class for providers
 */
public class ProviderFactory {
    public Provider create(int type) {
        switch (type) {
            case Provider.PROVIDER_TYPE_DB:
                // ToDo : Implementation
                return null;
            case Provider.PROVIDER_TYPE_FILE:
                // ToDo : Implementation
                return null;
            default:
                return null;
        }
    }
}
