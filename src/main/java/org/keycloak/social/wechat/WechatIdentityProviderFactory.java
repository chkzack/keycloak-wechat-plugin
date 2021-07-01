package org.keycloak.social.wechat;

import org.keycloak.broker.oidc.OAuth2IdentityProviderConfig;
import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

/**
 * @author chk
 */
public class WechatIdentityProviderFactory extends AbstractIdentityProviderFactory<WechatIdentityProvider> implements SocialIdentityProviderFactory<WechatIdentityProvider> {

    public static final String PROVIDER_ID = "wechat";
    public static final String PROVIDER_NAME = "Wechat";

    @Override
    public String getName() {
        return PROVIDER_NAME;
    }

    @Override
    public WechatIdentityProvider create(KeycloakSession session, IdentityProviderModel model) {
        return new WechatIdentityProvider(session, new OAuth2IdentityProviderConfig(model));
    }

    @Override
    public OAuth2IdentityProviderConfig createConfig() {
        return new OAuth2IdentityProviderConfig();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
