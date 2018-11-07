package br.ufrn.ct.cronos.service.distribuirturmas;

import java.io.Serializable;

public class AccessToken implements Serializable {

   private static final long serialVersionUID = 1L;

   private String access_token, token_type, expires_in, scope;

   public AccessToken() {
      super();
   }

   public String getAccessToken() {
      return access_token;
   }

   public String getAccess_token() {
      return access_token;
   }

   public void setAccessToken(String access_token) {
      this.access_token = access_token;
   }

   public String getToken_type() {
      return token_type;
   }

   public void setToken_type(String token_type) {
      this.token_type = token_type;
   }

   public String getExpires_in() {
      return expires_in;
   }

   public void setExpires_in(String expires_in) {
      this.expires_in = expires_in;
   }

   public String getScope() {
      return scope;
   }

   public void setScope(String scope) {
      this.scope = scope;
   }

}
