package am.ik.home;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UaaTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UaaUserDetails userDetails = (UaaUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        Map<String, Object> additionalInfo = new LinkedHashMap<>();
        additionalInfo.put("username", user.getUsername());
        additionalInfo.put("user_id", user.getUserId());
        additionalInfo.put("email", user.getEmail());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
