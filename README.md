# Azure OAuth2Provider
 
If you use the API gateway as an OAuth2 client with Azure, it may be necessary to register a special OAuth2 provider. 
Especially if you use the PKCE flow. The reason is that during the token exchange request Azure requires an Origin header and acknowledges this with the following error message:  
   
`
"error":"invalid_request","error_description":"AADSTS9002327: Tokens issued for the 'Single-Page Application' client-type may only be redeemed via cross-origin requests.\r\nTrace ID: 0e8f9824-95f5-481a-8deb-b035b1dd8303\r\nCorrelation ID: 2e1e8f7d-a08a-420e-8908-074c2a9add4d\r\nTimestamp: 2021-07-12 13:45:27Z","error_codes":[9002327],"timestamp":"2021-07-12 13:45:27Z","trace_id":"0e8f9824-95f5-481a-8deb-b035b1dd8303","correlation_id":"2e1e8f7d-a08a-420e-8908-074c2a9add4d"}
`  


The Azure OAuth2Provider will add the required `Origin` header in the Token-Exchange request.

![Dummy Origin header](https://github.com/Axway-API-Management-Plus/azure-oauth2provider/blob/main/lib/images/token_exchange_request_headers.png)

# Installation & Setup

- Download the release package
- Copy the JAR-File into your API-Gateway(s) `ext/lib` folder and __restart the API-Gateway(s)__
- If you don't have already, setup your OAuth-Client-Provider using Policy-Studio
- Close the Policy-Studio project
- Open Policy-Studio project file: `ExtConnsStore-xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx.xml`
- Locate your configured Azure OAuthProviderProfile and changhe the `class` to `com.axway.oauth.client.providers.AzureOAuth2Provider` as in the example below
- Re-Open the project in Policy-Studio and deploy the configuration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<entity xmlns="http://www.vordel.com/2005/06/24/entityStore" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" type="OAuthProviderProfile" entityPK="6017837698390746166" parentPK="5256084280368879969">
   <fval name="authzUrl">
      <value>https://login.microsoftonline.com/........./oauth2/v2.0/authorize</value>
   </fval>
   <fval name="cacheToUse">
      <value contentType="reference">
         <key type="CacheManager">
            <id field="name" value="Cache Manager" />
            <key type="Cache">
               <id field="name" value="OAuth Client State Cache" />
            </key>
         </key>
      </value>
   </fval>
   <fval name="class">
      <value>com.axway.oauth.client.providers.AzureOAuth2Provider</value>
   </fval>
   <fval name="name">
      <value>Azure AD</value>
   </fval>
   <fval name="tokenStore">
      <value contentType="reference">
         <key type="OAuth2StoresGroup">
            <id field="name" value="OAuth2 Stores" />
            <key type="ClientAccessTokenStoreGroup">
               <id field="name" value="Client Access Token Stores" />
               <key type="ClientAccessTokenPersist">
                  <id field="name" value="OAuth Client Access Token Store" />
               </key>
            </key>
         </key>
      </value>
   </fval>
   <fval name="tokenUrl">
      <value>https://login.microsoftonline.com/........./oauth2/v2.0/token</value>
   </fval>
</entity>
```

## API Management Version Compatibilty

This artefact has been tested with API-Management Versions

| Version            | Comment         |
| :---               | :---            |
| 7.7-20210530       |                 |
| 7.7-20210330       |                 |
| 7.7-20200930       |                 |

Please let us know, if you encounter any [issues](https://github.com/Axway-API-Management-Plus/azure-oauth2provider/issues) with your API-Manager version.  

## Contributing

Please read [Contributing.md](https://github.com/Axway-API-Management-Plus/Common/blob/master/Contributing.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Team

![alt text][Axwaylogo] Axway Team

[Axwaylogo]: https://github.com/Axway-API-Management/Common/blob/master/img/AxwayLogoSmall.png  "Axway logo"


## License
[Apache License 2.0](/LICENSE)
