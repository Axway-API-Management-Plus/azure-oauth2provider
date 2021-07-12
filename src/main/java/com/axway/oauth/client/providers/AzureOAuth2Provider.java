package com.axway.oauth.client.providers;

import com.vordel.oauth.client.OAuthTokenRequest;
import com.vordel.oauth.client.providers.BaseOAuth2Provider;

public class AzureOAuth2Provider extends BaseOAuth2Provider {

	@Override
	public OAuthTokenRequest modifyRequest(OAuthTokenRequest tokenRequest) {
		// Azure is only validating the presence of the Origin header, but not the actual value
		tokenRequest.addHeader("Origin", "Dummy");
		return tokenRequest;
	}
}
