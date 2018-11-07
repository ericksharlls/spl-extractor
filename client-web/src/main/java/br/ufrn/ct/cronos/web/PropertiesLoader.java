package br.ufrn.ct.cronos.web;
 
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
 
public class PropertiesLoader {
 
   // Criando uma instancia da classe properties
    private static Properties prop = new Properties();
     
   // Criando um método estático que pode ser acessado por outras classes da aplicação sem a necessidade de instanciar
    public static Properties propertiesLoader() {
         
      // Informando o caminho onde se encontra meu arquivo properties de forma dinâmica
      // Poderia fazer isso de forma estática passando o diretório completo mas obviamente isso não é muito interessante.
      String atualDir =
         ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getServletContext()
                  .getRealPath("/WEB-INF/parametrosAmbienteExecucao.properties");
      try { // Tentando recuperar as informações do arquivo de propriedades
         // Apenas para testar imprimo o diretório atual do meu arquivo properties
         System.out.println(atualDir);
         // Criando uma instância de File passando o meu arquivo .properties via construtor
            File file = new File(atualDir);  
         // Criando uma instância de FileInputStream passando via construtor o objeto file instanciado acima
            FileInputStream fileInputStream = new FileInputStream(file);
         // Lendo o fileInputStream recuperando assim o mapa contendo chaves e valores
            prop.load(fileInputStream);
         // Fechando o fileInputStream
            fileInputStream.close();           
        } catch (Exception e) {
         // Tratando possíveis exceções
        }
        return prop;
      // Retornando um objeto prop com o mapa correspondente ao meu arquivo properties
    }
     
}
