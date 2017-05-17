test3oaut

spring boot 1.5.3, oauth2, mysql for users



- get new token
http://localhost:8080/oauth/token?grant_type=password&username=user&password=user
  result:
{
  "access_token": "92c57523-f51b-4d0f-8ffc-0d140ac3b44d",
  "token_type": "bearer",
  "refresh_token": "b164efc6-f730-4796-ad70-6daa5d48b6a4",
  "expires_in": 4919,
  "scope": "read write trust"
}

- access resource
http://localhost:8080/private?access_token=92c57523-f51b-4d0f-8ffc-0d140ac3b44d


- refresh token
http://localhost:8080/oauth/token?grant_type=refresh_token&client_id=my-trusted-client&refresh_token=b164efc6-f730-4796-ad70-6daa5d48b6a4&client_secret=secret



How to using postman:


- get new token (mysql)
select "Basic Auth"
fill in client id: jml-client-id
fill in secret   : 12345
select POST request and type this in url: http://localhost:8080/oauth/token?grant_type=password&username=user&password=user

result:
{
  "access_token": "92c57523-f51b-4d0f-8ffc-0d140ac3b44d",
  "token_type": "bearer",
  "refresh_token": "b164efc6-f730-4796-ad70-6daa5d48b6a4",
  "expires_in": 4919,
  "scope": "read write trust"
}


- access resource
select GET request and type this in url:  http://localhost:8080/private?access_token=92c57523-f51b-4d0f-8ffc-0d140ac3b44d


- refresh token (mysql)
select "Basic Auth"
fill in client id: jml-client-id
fill in secret   : 12345
select POST request and type this in url: http://localhost:8080/oauth/token?grant_type=refresh_token&client_id=jml-client-id&refresh_token=b164efc6-f730-4796-ad70-6daa5d48b6a4&secret=12345




oauth2 schema for mysql:

use test3oauth2;

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

-- INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
-- VALUES ('rajith-client-id', 'rest_api', '12345', 'trust,read,write', 'password,authorization_code,refresh_token,implicit', 'ROLE_USER', '30', '1000');

INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES ('jml-client-id', 'oauth2-resource', '12345', 'trust,read,write', 'password,authorization_code,refresh_token,implicit', 'ROLE_USER', '30', '1000');

INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES ('jml-client-id', 'rest_api', '12345', 'trust,read,write', 'password,authorization_code,refresh_token,implicit', 'ROLE_USER', '30', '1000');


create table oauth_client_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);


create table oauth_access_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);


create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);


create table oauth_code (
  code VARCHAR(256), authentication LONG VARBINARY
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);



