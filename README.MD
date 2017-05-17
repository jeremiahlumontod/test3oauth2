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

