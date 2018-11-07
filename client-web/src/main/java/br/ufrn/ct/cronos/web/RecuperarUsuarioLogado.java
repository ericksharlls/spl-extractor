package br.ufrn.ct.cronos.web;

import java.io.Serializable;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

public class RecuperarUsuarioLogado implements Serializable {

   private static final long serialVersionUID = 1L;
   private String usuario;

   public RecuperarUsuarioLogado() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      setUsuario(authentication.getName());
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public String getUsuario() {
      return this.usuario;
   }

}
