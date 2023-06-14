import com.jdbc.conexao.SingleConnection;
import com.jdbc.dao.UsuarioDao;
import com.jdbc.model.BeanUsuarioFone;
import com.jdbc.model.Fone;
import com.jdbc.model.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TesteJdbc {

    @Test
    public void salvar() throws Exception {
        SingleConnection.getConnection();

        UsuarioDao objDao = new UsuarioDao();
        Usuario obj = new Usuario();

        obj.setNome("Mendes de Paula");
        obj.setEmail("mendes@email.com");
        objDao.salvar(obj);
        System.out.println(obj);
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
            Usuario obj = objDao.listarPorId(7L);
            System.out.println(obj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void atualilzar() {
        SingleConnection.getConnection();
        try {
            UsuarioDao objDao = new UsuarioDao();
            Usuario obj = objDao.listarPorId(4L);
            obj.setNome("José atualizado!");
            objDao.atualizar(obj);
            System.out.println(obj.getNome());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void deletar() {
        SingleConnection.getConnection();
        try {
            UsuarioDao objDao = new UsuarioDao();
            objDao.deletar(1L);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void salvarFone() throws Exception {
        SingleConnection.getConnection();
        UsuarioDao objDao = new UsuarioDao();
        Fone obj = new Fone();

        obj.setNumero("(81) 99858-2501");
        obj.setTipo("Residencial");
        obj.setUsuario(8L);

        objDao.salvarFone(obj);
        System.out.println(obj);
    }

    @Test
    public void listarFoneUsuario(){
        SingleConnection.getConnection();

        UsuarioDao objDao = new UsuarioDao();
        try {
            List<BeanUsuarioFone> list = objDao.listarFoneUsuario(9L);
            for (BeanUsuarioFone obj : list){
                System.out.println(obj);
                System.out.println("============================================================");
                System.out.println(obj.getNome());
                System.out.println("============================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





























}
