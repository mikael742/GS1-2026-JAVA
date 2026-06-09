package bluesignal.infrastructure;

import bluesignal.domain.Usuario;
import java.util.ArrayList;
import java.util.Optional;

/** Repositório em memória de Usuários (simula banco de dados com ArrayList)  */
public class UsuarioRepositorio {

    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static int proximoId = 1;

    // Cadastrar usuário
    public static void salvar(Usuario usuario) {
        usuario.setIdUsuario(proximoId++);
        usuarios.add(usuario);
    }

    // Listar todos
    public static ArrayList<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    // Buscar por ID
    public static Optional<Usuario> buscarPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == id)
                .findFirst();
    }

    // Buscar por email
    public static Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Atualizar
    public static boolean atualizar(Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == usuarioAtualizado.getIdUsuario()) {
                usuarios.set(i, usuarioAtualizado);
                return true;
            }
        }
        return false;
    }

    // Remover
    public static boolean remover(int id) {
        return usuarios.removeIf(u -> u.getIdUsuario() == id);
    }

    // Verificar se email já existe
    public static boolean emailExiste(String email) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    // Contar registros
    public static int contar() {
        return usuarios.size();
    }
}
