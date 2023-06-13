import com.jdbc.conexao.SingleConnection;
import com.jdbc.dao.UsuarioDao;
import com.jdbc.model.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TesteJdbc {

    @Test
    public void salvar(){
        SingleConnection.getConnection();

        UsuarioDao objDao = new UsuarioDao();
        Usuario obj = new Usuario();

        obj.setId(6L);
        obj.setNome("Mendes de Paula");
        obj.setEmail("mendes@email.com");
        objDao.salvar(obj);
    }

    @Test
    public void listar(){
        SingleConnection.getConnection();

        UsuarioDao objDao = new UsuarioDao();
        try {
            List<Usuario> list = objDao.listar();
            for (Usuario obj : list){
                System.out.println(obj);
                System.out.println("============================================================");
                System.out.println(obj.getNome());
                System.out.println("============================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listarPorId(){
        SingleConnection.getConnection();

        UsuarioDao objDao = new UsuarioDao();
        try {
            Usuario obj = objDao.listarPorId(1L);
            System.out.println(obj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
