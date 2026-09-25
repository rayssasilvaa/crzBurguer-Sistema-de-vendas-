import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexaoBD {
    public static Connection conectar() {
        Properties propriedades = new Properties();

        try (InputStream arquivo = ConexaoBD.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (arquivo == null) {
                System.out.println("Arquivo de configuração não encontrado no classpath.");
                return null;
            }

            propriedades.load(arquivo);

            String url = propriedades.getProperty("db.url");
            String usuario = propriedades.getProperty("db.usuario");
            String senha = propriedades.getProperty("db.senha");

            return DriverManager.getConnection(url, usuario, senha);

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de configuração.");
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados.");
            e.printStackTrace();
        }

        return null;
    }
}