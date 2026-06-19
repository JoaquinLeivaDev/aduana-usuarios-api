package cl.duocuc.aduana_usuarios_api.service;

import cl.duocuc.aduana_usuarios_api.client.AduanaClient;
import cl.duocuc.aduana_usuarios_api.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final AduanaClient aduanaClient;

    // Regla de negocio: username debe tener al menos 4 caracteres y no puede contener espacios
    public void validarFormatoUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El username no puede estar vacío");
        }
        if (username.length() < 4) {
            throw new IllegalArgumentException("El username debe tener al menos 4 caracteres");
        }
        if (username.contains(" ")) {
            throw new IllegalArgumentException("El username no puede contener espacios");
        }
    }

    // Regla de negocio: password debe tener al menos 6 caracteres
    public void validarFormatoPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
    }

    public ApiResponse<List<UsuarioResponseDTO>> obtenerTodos() {
        log.info("Solicitando lista de usuarios al core");
        return aduanaClient.obtenerTodos();
    }

    public ApiResponse<UsuarioResponseDTO> buscarUsuarioPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        log.info("Buscando usuario con id: {}", id);
        return aduanaClient.obtenerUsuarioPorId(id);
    }

    public ApiResponse<UsuarioResponseDTO> crearUsuario(UsuarioRequestDTO dto) {
        log.info("Validando datos antes de crear usuario: {}", dto.getUsername());
        validarFormatoUsername(dto.getUsername());
        validarFormatoPassword(dto.getPassword());
        if (dto.getIdRol() == null || dto.getIdRol() <= 0) {
            throw new IllegalArgumentException("El ID de rol debe ser un número positivo");
        }
        log.info("Datos válidos, enviando al core para crear usuario: {}", dto.getUsername());
        return aduanaClient.crearUsuario(dto);
    }

    public ApiResponse<UsuarioResponseDTO> actualizarUsuario(Long id, UsuarioRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        log.info("Validando datos antes de actualizar usuario id: {}", id);
        validarFormatoUsername(dto.getUsername());
        validarFormatoPassword(dto.getPassword());
        return aduanaClient.actualizarUsuario(id, dto);
    }

    public ApiResponse<Void> eliminarUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        log.info("Eliminando usuario con id: {}", id);
        return aduanaClient.eliminarUsuario(id);
    }

    public ApiResponse<List<ReporteResponseDTO>> obtenerReportesPorUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        log.info("Obteniendo reportes del usuario con id: {}", id);
        return aduanaClient.obtenerReportesPorUsuario(id);
    }
}