package cl.duocuc.aduana_usuarios_api.service;

import cl.duocuc.aduana_usuarios_api.client.AduanaClient;
import cl.duocuc.aduana_usuarios_api.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    private final AduanaClient aduanaClient;

    public List<UsuarioResponseDTO> obtenerTodos() {
        log.info("Obteniendo todos los usuarios desde Aduana-Api");
        ApiResponse<List<UsuarioResponseDTO>> response = aduanaClient.obtenerTodos();
        log.info("Usuarios obtenidos: {}", response.getData().size());
        return response.getData();
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        log.info("Orquestando búsqueda de usuario con id: {} desde Aduana-Api", id);
        ApiResponse<UsuarioResponseDTO> response = aduanaClient.obtenerUsuarioPorId(id);
        if (response == null || !response.isSuccess() || response.getData() == null) {
            log.error("No se pudo obtener el usuario con id {} desde Aduana-Api", id);
            throw new RuntimeException("Usuario no encontrado o error en servicio remoto");
        }
        return response.getData();
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        log.info("Creando usuario con username: {} en Aduana-Api", dto.getUsername());
        ApiResponse<UsuarioResponseDTO> response = aduanaClient.crearUsuario(dto);
        log.info("Usuario creado con id: {}", response.getData().getId());
        return response.getData();
    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto) {
        log.info("Actualizando usuario con id: {} en Aduana-Api", id);
        ApiResponse<UsuarioResponseDTO> response = aduanaClient.actualizarUsuario(id, dto);
        log.info("Usuario con id: {} actualizado", id);
        return response.getData();
    }

    public void eliminarUsuario(Long id) {
        log.info("Eliminando usuario con id: {} en Aduana-Api", id);
        aduanaClient.eliminarUsuario(id);
        log.info("Usuario con id: {} eliminado", id);
    }

    public List<ReporteResponseDTO> obtenerReportesPorUsuario(Long id) {
        log.info("Obteniendo reportes del usuario con id: {} desde Aduana-Api", id);
        ApiResponse<List<ReporteResponseDTO>> response = aduanaClient.obtenerReportesPorUsuario(id);
        log.info("Reportes obtenidos para usuario {}: {}", id, response.getData().size());
        return response.getData();
    }
}