package org.keycloak.social.wechat;

import org.keycloak.broker.oidc.mappers.AbstractJsonUserAttributeMapper;

/**
 * @author chk
 */
public class WechatUserAttributeMapper extends AbstractJsonUserAttributeMapper {

    public static final String PROVIDER_ID = "wechat-user-attribute-mapper";
    private static final String[] cp = new String[] { WechatIdentityProviderFactory.PROVIDER_ID };

    @Override
    public String[] getCompatibleProviders() {
        return new String[0];
    }

    @Override
    public String getId() {
        return null;
    }
}
