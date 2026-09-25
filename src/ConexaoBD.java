import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexaoBD {
    public static Connection conectar() {
        try {
            Properties propriedades = new Properties();

            FileInputStream arquivo =
                    new FileInputStream("config.properties");

            propriedades.load(arquivo);

            String url = propriedades.getProperty("db.url");
            String usuario = propriedades.getProperty("db.usuario");
            String senha = propriedades.getProperty("db.senha");

            return DriverManager.getConnection(
                    url,
                    usuario,
                    senha
            );

        } catch (IOException e) {
            System.out.println(
                    "Arquivo de configuração não encontrado."
            );
        } catch (Exception e) {
            System.out.println(
                    "Erro ao conectar com o banco de dados."
            );
            e.printStackTrace();
        }

        return null;
    }
}